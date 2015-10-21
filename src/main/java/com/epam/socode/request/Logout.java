package com.epam.socode.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jafar_qaddoumi on 10/21/15.
 */
public class Logout {
    @JsonProperty("profile_id")
    private String profileId;
    @JsonProperty("token")
    private String token;

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
