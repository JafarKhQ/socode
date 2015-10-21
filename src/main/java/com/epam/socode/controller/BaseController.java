package com.epam.socode.controller;

/**
 * @author jafar_qaddoumi
 */
interface BaseController {
    // Mapping
    String MAPPING_API = "/api";
    String MAPPING_API_AUTH = MAPPING_API + "/auth";

    String MAPPING_SIGNUP = "/signup";
    String MAPPING_VERIFY = "/verify";
    String MAPPING_LOGIN = "/login";
    String MAPPING_LOGOUT = "/logout";

    // String PARAM_ = "/signup";
}
