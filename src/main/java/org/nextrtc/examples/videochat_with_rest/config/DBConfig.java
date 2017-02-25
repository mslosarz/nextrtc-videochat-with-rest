package org.nextrtc.examples.videochat_with_rest.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.nextrtc.examples.videochat_with_rest.domain.Member;
import org.nextrtc.examples.videochat_with_rest.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackageClasses = MemberRepository.class, transactionManagerRef = "NextRTCTransactionManager", entityManagerFactoryRef = "NextRTCEntityMangerFactory")
@EnableTransactionManagement
public class DBConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "NextRTCDataSource", destroyMethod = "close")
    public ComboPooledDataSource datasource() throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setMinPoolSize(1);
        ds.setMaxPoolSize(10);
        ds.setCheckoutTimeout(30 * 60 * 100);
        ds.setMaxIdleTime(30 * 60); // 30 minutes
        ds.setMaxStatements(10);
        ds.setMaxStatementsPerConnection(10);
        ds.setAutoCommitOnClose(true);

        ds.setDriverClass(environment.getRequiredProperty("nextrtc.db.driverClassName"));
        ds.setJdbcUrl(environment.getRequiredProperty("nextrtc.db.url"));
        ds.setUser(environment.getRequiredProperty("nextrtc.db.username"));
        ds.setPassword(environment.getRequiredProperty("nextrtc.db.password"));
        return ds;
    }

    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", environment.getRequiredProperty("nextrtc.db.dialect"));
        props.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("nextrtc.db.hbm2ddl", "validate"));
        props.setProperty("hibernate.show_sql", "false");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.DefaultNamingStrategy");
        props.setProperty("hibernate.cache.use_second_level_cache", "false");
        props.setProperty("jadira.usertype.autoRegisterUserTypes", "true");
        return props;
    }

    @Bean(name = "NextRTCEntityMangerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setDataSource(datasource());
        factoryBean.setPackagesToScan(Member.class.getPackage().getName());
        factoryBean.setPersistenceUnitName("NextRTCPersistenceUnit");
        return factoryBean;
    }

    @Bean(name = "NextRTCTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("NextRTCEntityMangerFactory") EntityManagerFactory entityManagerFactory, @Qualifier("NextRTCDataSource") DataSource dataSource) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
