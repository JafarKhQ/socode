package com.epam.socode.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.epam.socode.annotation.WebConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet configuration class
 *
 * @author jafar_qaddoumi
 */
@WebConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        // Jackson message converter
        converters.add(
                new MappingJackson2HttpMessageConverter(objectMapper));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        // use the default locale param name (locale)
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
}
