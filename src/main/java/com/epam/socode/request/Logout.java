package com.epam.socode.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jafar_qaddoumi on 10/21/15.
 */
public class Logout {
    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
