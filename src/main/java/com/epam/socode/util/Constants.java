package com.epam.socode.util;

/**
 * @author jafar_qaddoumi
 */
public final class Constants {

    private static final String PACKAGE_BASE = "com.epam.socode";
    public static final String PACKAGE_SERVICE = PACKAGE_BASE + ".service";
    public static final String PACKAGE_CONTROLLER = PACKAGE_BASE + ".controller";
    public static final String PACKAGE_REPOSITORY = PACKAGE_BASE + ".repository";
    public static final String PACKAGE_LISTENER = PACKAGE_BASE + ".listener";

    private static final String PROPERTY_SOURCE_BASE = "classpath:properties/";
    public static final String PROPERTY_SOURCE_DEV = PROPERTY_SOURCE_BASE + "development.properties";
    public static final String PROPERTY_SOURCE_TEST = PROPERTY_SOURCE_BASE + "test.properties";
    public static final String PROPERTY_SOURCE_PRO = PROPERTY_SOURCE_BASE + "production.properties";

    private Constants() {
        throw new IllegalAccessError();
    }
}
