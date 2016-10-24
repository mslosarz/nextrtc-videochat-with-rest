package org.nextrtc.examples.videochat_with_rest.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("NextRTCDataSource")
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/",
                        "/login",
                        "/login**",
                        "/register.html",
                        "/action/register",
                        "/action/verify/*",
                        "/js/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/conversation.html")
                .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout.html")
                .logoutSuccessUrl("/login.html")
                .permitAll()
                .and()
                .addFilterBefore(ssoFilters(), BasicAuthenticationFilter.class)
                .addFilterAfter(oAuth2ClientContextFilter, SecurityContextPersistenceFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, confirmed as enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private OAuth2ClientContextFilter oAuth2ClientContextFilter;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public Filter ssoFilters() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = Lists.newArrayList();
        filters.add(ssoFilter(facebook(), "/login/facebook", "Facebook"));
        filters.add(ssoFilter(github(), "/login/github", "GitHub"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ClientResources client, String path, String provider) {
        OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(
                path);
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
        CustomTokenService tokenServices = new CustomTokenService(client.getResource().getUserInfoUri(),
                client.getClient().getClientId(), provider);
        tokenServices.setRestTemplate(oAuth2RestTemplate);
        oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);
        oAuth2ClientAuthenticationFilter.setApplicationEventPublisher(applicationEventPublisher);
        return oAuth2ClientAuthenticationFilter;
    }

    class ClientResources {

        @NestedConfigurationProperty
        private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

        @NestedConfigurationProperty
        private ResourceServerProperties resource = new ResourceServerProperties();

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }
    }

    class CustomTokenService extends UserInfoTokenServices {

        private String provider;

        public CustomTokenService(String userInfoEndpointUrl, String clientId, String provider) {
            super(userInfoEndpointUrl, clientId);
            this.provider = provider;
        }

        @Override
        protected Object getPrincipal(Map<String, Object> map) {
            return provider + "_" + map.getOrDefault("id", "unknown");
        }
    }
}
