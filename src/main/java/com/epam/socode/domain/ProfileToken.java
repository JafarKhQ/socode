package com.epam.socode.domain;

public class ProfileToken {

    private String token;
    private String profileId;
    private long lastUsedTime;

    public ProfileToken(String profileId, String token) {
        super();
        this.token = token;
        this.profileId = profileId;
        this.lastUsedTime = System.currentTimeMillis();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getLastUsedTime() {
        return lastUsedTime;
    }

    public void setLastUsedTime(long lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}