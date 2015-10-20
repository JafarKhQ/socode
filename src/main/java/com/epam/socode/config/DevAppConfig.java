package com.epam.socode.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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
