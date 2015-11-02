package com.epam.socode.config;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import com.epam.socode.domain.WorkGroup;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.ogm.cfg.OgmConfiguration;
import org.hibernate.ogm.cfg.OgmProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Common App Configuration for All environments (Profiles)
 *
 * @author jafar_qaddoumi
 */
class BaseAppConfig {

    @Value("${database.provider}")
    String databaseProvider;
    @Value("${database.create_database}")
    String createDatabase;
    @Value("${database.host}")
    String databaseHost;
    @Value("${database.database}")
    String database;
    @Value("${database.username}")
    String databaseUsername;
    @Value("${database.password}")
    String databasePassword;
    @Value("${database.dialect}")
    String dialect;
    @Value("${database.show_sql}")
    String showSql;
    @Value("${database.hbm2ddl.auto}")
    String hbm2ddlAuto;

    @Value("${email.port}")
    int emailPort;
    @Value("${email.host}")
    String emailHost;
    @Value("${email.protocol}")
    String emailProtocol;
    @Value("${email.username}")
    String emailUsername;
    @Value("${email.password}")
    String emailPassword;
    @Value("${email.debug}")
    String emailDebug;

    @Bean
    public SessionFactory sessionFactory() {
        final Configuration configuration = getSessionFactoryConfiguration();
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailHost);
        mailSender.setPort(emailPort);
        mailSender.setProtocol(emailProtocol);
        mailSender.setUsername(emailUsername);
        mailSender.setPassword(emailPassword);
        mailSender.setJavaMailProperties(getJavaMailSenderProperties());

        return mailSender;
    }

    /**
     * Create a Hibernate Configuration for SessionFactory,
     * override this method to chane the configurations for each profile
     * or use java properties files if possible
     *
     * @return Hibernate Configuration for SessionFactory
     */
    Configuration getSessionFactoryConfiguration() {
        Configuration configuration = new OgmConfiguration();

        return addAnnotatedClasses(configuration)
                .setProperty(OgmProperties.DATASTORE_PROVIDER, databaseProvider)
                .setProperty(OgmProperties.HOST, databaseHost)
                .setProperty(OgmProperties.DATABASE, database)
                .setProperty(OgmProperties.USERNAME, databaseUsername)
                .setProperty(OgmProperties.PASSWORD, databasePassword)
                .setProperty(OgmProperties.CREATE_DATABASE, createDatabase);
    }

    /**
     * Add Hibernate Annotated Classes to the Hibernate Configuration
     *
     * @param configuration Hibernate Configuration
     * @return Hibernate Configuration
     */
    final Configuration addAnnotatedClasses(Configuration configuration) {
        return configuration
                .addAnnotatedClass(Profile.class)
                .addAnnotatedClass(WorkGroup.class)
                .addAnnotatedClass(VerificationKey.class);
    }

    /**
     * Returns the properties for Java Mail
     *
     * @return Java Mail Properties object
     */
    final Properties getJavaMailSenderProperties() {
        Properties javaMailProperties = new Properties();

        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        javaMailProperties.setProperty("mail.debug", emailDebug);

        return javaMailProperties;
    }
}
