package com.epam.socode.config;

import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Common App Configuration for All environments (Profiles)
 *
 * @author jafar_qaddoumi
 */
class BaseAppConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("socodePu");
    }

}
