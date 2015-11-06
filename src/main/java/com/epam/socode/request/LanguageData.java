package com.epam.socode.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageData {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
