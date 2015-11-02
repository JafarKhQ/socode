package com.epam.socode.config;

import com.epam.socode.annotation.WebConfiguration;
import com.epam.socode.util.Utils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Servlet configuration class
 *
 * @author jafar_qaddoumi
 */
@WebConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        // Jackson message converter
        converters.add(
                new MappingJackson2HttpMessageConverter(
                        Utils.appObjectMapper()
                ));
    }
}
