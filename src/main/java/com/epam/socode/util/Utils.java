package com.epam.socode.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jafar_qaddoumi
 */
public class Utils {

    public static ObjectMapper appObjectMapper() {
        final ObjectMapper om = new ObjectMapper();

        // Ignore null objects (null objects will not presented in the JSON String)
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Ignore all Class properties (setters, getters, isGetters, methods and fields)
        // from serialisation and deserialization
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);

        // include all fields in serialisation and deserialization
        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        return om;
    }

    private Utils() {
        throw new IllegalAccessError();
    }
}
