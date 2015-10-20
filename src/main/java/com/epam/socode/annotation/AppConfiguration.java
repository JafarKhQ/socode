package com.epam.socode.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.socode.util.Constants;

/**
 * An annotation that contain all App Config annotations
 *
 * @author jafar_qaddoumi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ComponentScan(basePackages = { Constants.PACKAGE_SERVICE, Constants.PACKAGE_REPOSITORY, Constants.PACKAGE_LISTENER })
public @interface AppConfiguration {
}
