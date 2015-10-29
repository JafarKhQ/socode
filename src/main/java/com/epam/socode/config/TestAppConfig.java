package com.epam.socode.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.epam.socode.annotation.AppConfiguration;
import com.epam.socode.domain.Profile;
import com.epam.socode.domain.Project;
import com.epam.socode.domain.VerificationToken;
import com.epam.socode.util.Constants;

/**
 * App configuration for development environment (Profile)
 *
 * @author jafar_qaddoumi
 */
@AppConfiguration
@PropertySource(Constants.PROPERTY_SOURCE_TEST)
public class TestAppConfig extends BaseAppConfig {
    @Override
    public SessionFactory sessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Profile.class).addAnnotatedClass(Project.class)
                .addAnnotatedClass(VerificationToken.class)
                .setProperty("hibernate.dialect", getDialect())
                .setProperty("hibernate.connection.url", getHost())
                .setProperty("hibernate.current_session_context_class", getProvider())
                .setProperty("hibernate.show_sql", getShowSql())
                .setProperty("hibernate.hbm2ddl.auto", getHbm2ddlAuto());

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);
        mailSender.setProtocol("smtp");
        mailSender.setUsername("useful.mailer@gmail.com");
        mailSender.setPassword("usefulMailer123#@!");

        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "false");
        javaMailProperties.setProperty("mail.smtp.quitwait", "false");
        javaMailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.setProperty("mail.smtp.socketFactory.fallback", "false");
        javaMailProperties.setProperty("mail.debug", "true");
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }
}
