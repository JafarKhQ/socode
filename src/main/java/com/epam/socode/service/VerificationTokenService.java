package com.epam.socode.service;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationToken;
import com.epam.socode.request.Verify;

/**
 * @author jafar_qaddoumi
 */
public interface VerificationTokenService {
    VerificationToken createVerificationToken(Profile profile, String token);

    VerificationToken findVerificationTokenByKey(String verifykey);

    Profile verifyProfile(Verify verify);

    VerificationToken findVerificationTokenByProfileId(String profileId);
}
