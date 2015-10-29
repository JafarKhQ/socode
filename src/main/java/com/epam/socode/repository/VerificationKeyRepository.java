package com.epam.socode.repository;

import com.epam.socode.domain.VerificationKey;

/**
 * @author Krystian_Balwierz
 */
public interface VerificationKeyRepository {
    VerificationKey addVerificationKey(VerificationKey verificationKey);

    VerificationKey findVerificationKeyByKey(String key);

    VerificationKey findVerificationKeyByProfileId(String profileId);
}
