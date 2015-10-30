package com.epam.socode.config;

import com.epam.socode.domain.Group;
import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
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
    String provider;
    @Value("${database.create_database}")
    String createDatabase;
    @Value("${database.host}")
    String host;
    @Value("${database.database}")
    String database;
    @Value("${database.username}")
    String username;
    @Value("${database.password}")
    String password;
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
        Configuration configuration = new OgmConfiguration();
        addAnnotatedClasses(configuration)
                .setProperty(OgmProperties.DATASTORE_PROVIDER, provider)
                .setProperty(OgmProperties.HOST, host)
                .setProperty(OgmProperties.DATABASE, database)
                .setProperty(OgmProperties.USERNAME, username)
                .setProperty(OgmProperties.PASSWORD, password)
                .setProperty(OgmProperties.CREATE_DATABASE, createDatabase);

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailHost);
        mailSender.setPort(emailPort);
        mailSender.setProtocol(emailProtocol);
        mailSender.setUsername(emailUsername);
        mailSender.setPassword(emailPassword);
        mailSender.setJavaMailProperties(getJavaMailSenderProperties());

        return mailSender;
    }

    Properties getJavaMailSenderProperties() {
        Properties javaMailProperties = new Properties();

        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "false");
        javaMailProperties.setProperty("mail.smtp.quitwait", "false");
        javaMailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.setProperty("mail.smtp.socketFactory.fallback", "false");
        javaMailProperties.setProperty("mail.debug", emailDebug);

        return javaMailProperties;
    }

    final Configuration addAnnotatedClasses(Configuration configuration) {
        return configuration.addAnnotatedClass(Profile.class).addAnnotatedClass(Group.class)
                .addAnnotatedClass(VerificationKey.class);
    }
}
