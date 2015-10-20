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
	@JsonProperty("verifykey")
	private String verifykey;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerifykey() {
		return verifykey;
	}

	public void setVerifykey(String verifykey) {
		this.verifykey = verifykey;
	}

}
