package com.epam.socode.service;

import com.epam.socode.domain.Profile;
import com.epam.socode.request.ProfileUpdate;
import com.epam.socode.request.Signup;

/**
 * @author jafar_qaddoumi
 */
public interface ProfileService {
    Profile addProfileFromSignup(Signup signup);

    Profile updateProfile(Profile profile);

    Profile updateProfile(ProfileUpdate profileUpdate);

    Profile enableProfile(Profile profile);

    Profile findProfileByEmail(String email);

    Profile findProfileById(String profileId);
}
