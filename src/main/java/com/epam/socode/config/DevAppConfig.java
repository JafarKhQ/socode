package com.epam.socode.config;

import com.epam.socode.annotation.AppConfiguration;
import com.epam.socode.util.Constants;
import org.springframework.context.annotation.PropertySource;

/**
 * App configuration for development environment (Profile)
 *
 * @author jafar_qaddoumi
 */
@AppConfiguration
@PropertySource(Constants.PROPERTY_SOURCE_DEV)
public class DevAppConfig extends BaseAppConfig {
}
