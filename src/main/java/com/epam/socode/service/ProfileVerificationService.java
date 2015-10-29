package com.epam.socode.service;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import com.epam.socode.request.Verify;

/**
 * @author jafar_qaddoumi
 */
public interface ProfileVerificationService {
    VerificationKey createVerificationKey(Profile profile);

    VerificationKey findVerificationKeyByKey(String key);

    Profile verifyProfile(Verify verify);

    VerificationKey findVerificationTokenByProfileId(String profileId);
}
