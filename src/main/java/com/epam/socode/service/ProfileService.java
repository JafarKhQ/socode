package com.epam.socode.service;

import com.epam.socode.domain.Profile;
import com.epam.socode.request.Signup;

/**
 * @author jafar_qaddoumi
 */
public interface ProfileService {
	Profile addProfileFromSignup(Signup signup);
	void createVerificationToken(Profile profile, String token);

	VerificationToken getVerificationToken(String verifykey);

	void saveRegisteredProfile(Profile profile);
}
