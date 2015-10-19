package com.epam.socode.config;

import com.epam.socode.util.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * App configuration for development environment (Profile)
 *
 * @author jafar_qaddoumi
 */
@Configuration
@ComponentScan(basePackages = {
        Constants.PACKAGE_SERVICE,
        Constants.PACKAGE_REPOSITORY})
public class DevAppConfig extends BaseAppConfig {
}
