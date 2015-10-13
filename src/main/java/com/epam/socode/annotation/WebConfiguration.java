package com.epam.socode.annotation;

import com.epam.socode.util.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.annotation.*;

/**
 * @author jafar_qaddoumi
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@EnableWebMvc
@ComponentScan(Constants.PACKAGE_CONTROLLER)
public @interface WebConfiguration {
}
