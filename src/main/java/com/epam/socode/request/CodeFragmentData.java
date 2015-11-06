package com.epam.socode.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeFragmentData {

    @JsonProperty("token")
    private String token;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("body")
    private String body;
    
    @JsonProperty("languages")
    private List<LanguageData> languages;

    @JsonProperty("tags")
    private List<TagData> tags;
    
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public List<LanguageData> getLanguages() {
        return languages;
    }
    public void setLanguages(List<LanguageData> languages) {
        this.languages = languages;
    }
    public List<TagData> getTags() {
        return tags;
    }
    public void setTags(List<TagData> tags) {
        this.tags = tags;
    }

}
