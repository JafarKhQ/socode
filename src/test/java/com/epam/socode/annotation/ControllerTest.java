package com.epam.socode.annotation;


import com.epam.socode.config.TestAppConfig;
import com.epam.socode.config.WebConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.*;

/**
 * @author jafar_qaddoumi
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, TestAppConfig.class})
public @interface ControllerTest {
}
