package com.epam.socode.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jafar_qaddoumi
 */
public class Response {
    private static final int CODE_SUCCESS = 200;
    private static final String STATUS_FAIL = "FAIL";
    private static final String STATUS_SUCCESS = "OK";

    @JsonProperty
    private String status;
    @JsonProperty
    private int statusCode;
    @JsonProperty
    private Object data;

    private Response() {
    }

    public static Response newSuccessResponse() {
        return newSuccessResponse(null);
    }

    public static Response newSuccessResponse(Object data) {
        Response response = new Response();
        response.data = data;
        response.status = STATUS_SUCCESS;
        response.statusCode = CODE_SUCCESS;

        return response;
    }

    public static Response newErrorResponse(int code) {
        Response response = new Response();
        response.data = null;
        response.status = STATUS_FAIL;
        response.statusCode = code;

        return response;
    }
}
