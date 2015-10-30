package com.epam.socode.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.socode.domain.Group;
import com.epam.socode.domain.Profile;
import com.epam.socode.exception.NotAllowedOperationException;
import com.epam.socode.exception.ProfileExistException;
import com.epam.socode.exception.ProfileNotFoundException;
import com.epam.socode.repository.ProfileRepository;
import com.epam.socode.request.ProfileUpdate;
import com.epam.socode.request.Signup;
import com.epam.socode.service.AuthenticationService;
import com.epam.socode.service.GroupService;
import com.epam.socode.service.ProfileService;

/**
 * @author jafar_qaddoumi
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Profile addProfileFromSignup(Signup signup) {
        try {
            Profile p = findProfileByEmail(signup.getLogin());
            if (null != p) {
                throw new ProfileExistException();
            }
        } catch (ProfileNotFoundException e) {
            // ignore error
        }

        Profile profile = new Profile(signup);
        profile.setJoinDate(String.valueOf(System.currentTimeMillis()));
        if (Strings.isNotEmpty(signup.getGroup())) {
            Group group = groupService.findGroupById(signup.getGroup());
            profile.addParticipatedGroup(group);
        }

        return profileRepository.addProfile(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return profileRepository.updateProfile(profile);
    }

    @Override
    public Profile updateProfile(ProfileUpdate profileUpdate) {
        String profileId = authenticationService.findProfileIdByToken(profileUpdate.getToken());
        if (!profileId.equals(profileUpdate.getProfileId())) {
            throw new NotAllowedOperationException();
        }

        Profile profile = profileRepository.findProfileById(profileId);
        profile.setName(profileUpdate.getName());
        profile.setSurname(profileUpdate.getSurname());
        profile.setMainLanguage(profileUpdate.getMainLanguage());
        if (Strings.isNotEmpty(profileUpdate.getEmail())) {
            profile.setEmail(profileUpdate.getEmail());
        }

        return updateProfile(profile);
    }

    @Override
    public Profile enableProfile(Profile profile) {
        profile.setEnabled(true);
        return updateProfile(profile);
    }

    @Override
    public Profile findProfileByEmail(String email) {
        return profileRepository.findProfileByEmail(email);
    }

    @Override
    public Profile findProfileById(String profileId) {
        return profileRepository.findProfileById(profileId);
    }
}
