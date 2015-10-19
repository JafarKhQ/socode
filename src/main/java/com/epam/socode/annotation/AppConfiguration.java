package com.epam.socode.annotation;

import com.epam.socode.util.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * An annotation that contain all App Config annotations
 *
 * @author jafar_qaddoumi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ComponentScan(basePackages = {
        Constants.PACKAGE_SERVICE,
        Constants.PACKAGE_REPOSITORY})
public @interface AppConfiguration {
}
