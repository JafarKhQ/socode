package com.epam.socode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationToken;
import com.epam.socode.repository.ProfileRepository;
import com.epam.socode.repository.TokenRepository;
import com.epam.socode.service.ProfileService;

/**
 * @author jafar_qaddoumi
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public Profile addProfile(Profile profile) {
		return profileRepository.addProfile(profile);
	}

	@Override
	public VerificationToken getVerificationToken(String verifykey) {
		return tokenRepository.getByVerificationKey(verifykey);
	}

	@Override
	public void createVerificationToken(Profile profile, String token) {
		VerificationToken myToken = new VerificationToken(token, profile);
		tokenRepository.save(myToken);
	}

	@Override
	public void saveRegisteredProfile(Profile profile) {
		profileRepository.save(profile);
	}
}
