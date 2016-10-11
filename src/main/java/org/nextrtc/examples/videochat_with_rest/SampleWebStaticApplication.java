package org.nextrtc.examples.videochat_with_rest;

import org.nextrtc.examples.videochat_with_rest.config.DBConfig;
import org.nextrtc.examples.videochat_with_rest.config.MvcConfig;
import org.nextrtc.examples.videochat_with_rest.config.NextRTCEndpointConfig;
import org.nextrtc.examples.videochat_with_rest.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})
@Import({NextRTCEndpointConfig.class,
        AppConfig.class,
        DBConfig.class,
        MvcConfig.class,
        WebSecurityConfig.class,
})
public class SampleWebStaticApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleWebStaticApplication.class, args);
    }
}


