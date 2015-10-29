package com.epam.socode.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Krystian_Balwierz
 *
 */
public class Verify {
    @JsonProperty("email")
    private String email;
    @JsonProperty("verify_key")
    private String verifyKey;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }
}
