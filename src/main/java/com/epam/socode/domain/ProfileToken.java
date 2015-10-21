package com.epam.socode.domain;

public class ProfileToken {

    private String token;
    private long lastUsedTime;

    public ProfileToken(String token) {
        super();
        this.token = token;
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
}