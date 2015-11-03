package com.epam.socode.config;

import com.epam.socode.annotation.AppConfiguration;
import com.epam.socode.util.Constants;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * App configuration for Unit Testing
 *
 * @author jafar_qaddoumi
 */
@AppConfiguration
@PropertySource(Constants.PROPERTY_SOURCE_TEST)
public class TestAppConfig extends BaseAppConfig {

    @Override
    Configuration getSessionFactoryConfiguration() {
        Configuration configuration = new Configuration();
        addAnnotatedClasses(configuration)
                .setProperty("hibernate.dialect", dialect)
                .setProperty("hibernate.connection.url", databaseHost)
                .setProperty("hibernate.current_session_context_class", databaseProvider)
                .setProperty("hibernate.show_sql", showSql)
                .setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);

        return configuration;
    }
}
