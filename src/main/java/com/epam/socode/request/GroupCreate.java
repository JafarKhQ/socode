package com.epam.socode.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupCreate {
	
	@JsonProperty("group_name")
    private String groupName;
	
	@JsonProperty("token")
    private String token;

    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
