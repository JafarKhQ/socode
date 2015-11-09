package com.epam.socode.repository;

import com.epam.socode.domain.Profile;

/**
 * @author jafar_qaddoumi
 */
public interface ProfileRepository {

    Profile addProfile(Profile profile);

    Profile updateProfile(Profile profile);

    Profile mergeProfile(Profile profile);

    Profile findProfileByEmail(String email);

    Profile findProfileById(String profileId);
}
