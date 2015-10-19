package com.epam.socode.config;

import org.hibernate.ogm.cfg.OgmProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

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
        final Map<String, String> props = new HashMap<>(7);

        props.put(OgmProperties.DATASTORE_PROVIDER, provider);
        props.put(OgmProperties.HOST, host);
        props.put(OgmProperties.DATABASE, database);
        props.put(OgmProperties.USERNAME, username);
        props.put(OgmProperties.PASSWORD, password);
        props.put(OgmProperties.CREATE_DATABASE, createDatabase);

        return Persistence.createEntityManagerFactory(persistenceUnit, props);
    }

}
