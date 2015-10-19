package com.epam.socode.util;

/**
 * @author jafar_qaddoumi
 */
public final class Constants {

    private static final String PACKAGE_BASE = "com.epam.socode";
    public static final String PACKAGE_SERVICE = PACKAGE_BASE + ".service";
    public static final String PACKAGE_CONTROLLER = PACKAGE_BASE + ".controller";
    public static final String PACKAGE_REPOSITORY = PACKAGE_BASE + ".repository";

    private Constants() {
        throw new IllegalAccessError();
    }
}
