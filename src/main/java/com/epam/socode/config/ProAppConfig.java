package com.epam.socode.config;

import com.epam.socode.annotation.AppConfiguration;
import com.epam.socode.util.Constants;
import org.springframework.context.annotation.PropertySource;

/**
 * App configuration for production environment (Profile)
 *
 * @author jafar_qaddoumi
 */
@AppConfiguration
@PropertySource(Constants.PROPERTY_SOURCE_PRO)
public class ProAppConfig extends BaseAppConfig {
}
