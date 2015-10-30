package com.epam.socode.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jafar_qaddoumi
 */
public class Signup {
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;
    @JsonProperty("group_id")
    private String groupId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return groupId;
    }

    public void setGroup(String groupId) {
        this.groupId = groupId;
    }
}
