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
    public Profile updateProfile(Profile profile) {
        return profileRepository.updateProfile(profile);
    }

    @Override
    public Profile enableProfile(Profile profile) {
        profile.setEnabled(true);
        return updateProfile(profile);
    }
}
