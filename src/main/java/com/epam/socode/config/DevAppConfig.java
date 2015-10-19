package com.epam.socode.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * App configuration for development environment (Profile)
 *
 * @author jafar_qaddoumi
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.socode.service",
        "com.epam.socode.repository"})
public class DevAppConfig extends BaseAppConfig {
}
