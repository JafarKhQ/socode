package com.epam.socode.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.Project;
import com.epam.socode.repository.ProfileRepository;
import com.epam.socode.request.Signup;
import com.epam.socode.service.ProfileService;
import com.epam.socode.service.ProjectService;

/**
 * @author jafar_qaddoumi
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public Profile addProfileFromSignup(Signup signup) {
		Profile profile = new Profile(signup);
		if (Strings.isNotEmpty(signup.getProject())) {
			Project project = projectService.findProjectById(signup.getProject());
			profile.addParticipatedProject(project);
		}

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
