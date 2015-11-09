package com.epam.socode.config;

import java.util.Properties;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.ogm.cfg.OgmConfiguration;
import org.hibernate.ogm.cfg.OgmProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import com.epam.socode.domain.WorkGroup;
import com.epam.socode.util.Constants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Common App Configuration for All environments (Profiles)
 *
 * @author jafar_qaddoumi
 */
class BaseAppConfig {

    // database
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

    // email
    @Value("${email.port}")
    String emailPort;
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

    /**
     * A bean for hibernate
     *
     * @return hibernate SessionFactory
     */
    @Bean
    public SessionFactory sessionFactory() {
        final Configuration configuration = getSessionFactoryConfiguration();
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * A bean for property sources
     *
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * A bean for sending emails
     *
     * @return JavaMailSender object or null if email not configured in properties
     */
    @Bean
    public JavaMailSender javaMailSender() {
        if (Strings.isEmpty(emailHost)) {
            return null;
        }

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailHost);
        mailSender.setPort(Integer.valueOf(emailPort));
        mailSender.setProtocol(emailProtocol);
        mailSender.setUsername(emailUsername);
        mailSender.setPassword(emailPassword);
        mailSender.setJavaMailProperties(getJavaMailSenderProperties());

        return mailSender;
    }

    /**
     * A bean for mapping json
     *
     * @return Jackson ObjectMapper object
     */
    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper om = new ObjectMapper();

        // Ignore null objects (null objects will not presented in the JSON String)
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Ignore all Class properties (setters, getters, isGetters, methods and fields)
        // from serialisation and deserialization
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);

        // include all fields in serialisation and deserialization
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        return om;
    }

    /**
     * A bean for our MessageSource which define the message source
     * property name for internalization and localization
     *
     * @return MessageSource object
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(Constants.MESSAGE_SOURCE_EMAILS);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * A bean for LocaleResolver
     *
     * @return LocaleResolver object
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    /**
     * A bean for ApplicationEventMulticaster to run our events on async way
     *
     * @return ApplicationEventMulticaster object with AsyncTaskExecutor
     */
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster saem = new SimpleApplicationEventMulticaster();
        saem.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return saem;
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
