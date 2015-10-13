package com.epam.socode.annotation;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * @author jafar_qaddoumi
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface AppConfiguration {
}
