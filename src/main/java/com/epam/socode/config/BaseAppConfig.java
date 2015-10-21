package com.epam.socode.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.ogm.cfg.OgmConfiguration;
import org.hibernate.ogm.cfg.OgmProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.Project;
import com.epam.socode.domain.VerificationToken;

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
    public SessionFactory sessionFactory() {
        Configuration configuration = new OgmConfiguration();
        configuration.setProperty(OgmProperties.DATASTORE_PROVIDER, provider);
        configuration.setProperty(OgmProperties.HOST, host);
        configuration.setProperty(OgmProperties.DATABASE, database);
        configuration.setProperty(OgmProperties.USERNAME, username);
        configuration.setProperty(OgmProperties.PASSWORD, password);
        configuration.setProperty(OgmProperties.CREATE_DATABASE, createDatabase);

        configuration.addAnnotatedClass(Profile.class).addAnnotatedClass(Project.class)
                .addAnnotatedClass(VerificationToken.class);

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
