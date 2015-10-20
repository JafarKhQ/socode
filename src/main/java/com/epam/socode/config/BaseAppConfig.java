package com.epam.socode.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.ogm.cfg.OgmConfiguration;
import org.hibernate.ogm.cfg.OgmProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolverHolder;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Common App Configuration for All environments (Profiles)
 *
 * @author jafar_qaddoumi
 */
class BaseAppConfig {

    @Value("${database.config}")
    private String persistenceUnit;

    @Value("${database.provider}")
    private String provider;

    @Value("${database.create_database}")
    private String createDatabase;

    @Value("${database.host}")
    private String host;

    @Value("${database.database}")
    private String database;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        final Properties props = new Properties();
        props.setProperty(OgmProperties.DATASTORE_PROVIDER, provider);
        props.setProperty(OgmProperties.HOST, host);
        props.setProperty(OgmProperties.DATABASE, database);
        props.setProperty(OgmProperties.USERNAME, username);
        props.setProperty(OgmProperties.PASSWORD, password);
        props.setProperty(OgmProperties.CREATE_DATABASE, createDatabase);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.epam.socode.domain");
        factoryBean.setJpaProperties(props);
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mongodb.Mongo");
        dataSource.setUrl(database);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
