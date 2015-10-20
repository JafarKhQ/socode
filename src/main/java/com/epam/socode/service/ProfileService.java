package com.epam.socode.service;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationToken;

/**
 * @author jafar_qaddoumi
 */
public interface ProfileService {
	Profile addProfile(Profile profile);

	void createVerificationToken(Profile profile, String token);

	VerificationToken getVerificationToken(String verifykey);

	void saveRegisteredProfile(Profile profile);
}
