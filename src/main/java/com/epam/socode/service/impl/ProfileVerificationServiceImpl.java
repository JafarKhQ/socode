package com.epam.socode.service.impl;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationKey;
import com.epam.socode.exception.EmailVerificationException;
import com.epam.socode.repository.VerificationKeyRepository;
import com.epam.socode.request.Verify;
import com.epam.socode.service.ProfileService;
import com.epam.socode.service.ProfileVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jafar_qaddoumi
 */
@Service
public class ProfileVerificationServiceImpl implements ProfileVerificationService {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private VerificationKeyRepository verificationKeyRepository;

    @Override
    public VerificationKey findVerificationKeyByKey(String key) {
        return verificationKeyRepository.findVerificationKeyByKey(key);
    }

    @Override
    public VerificationKey createVerificationKey(Profile profile) {
        final String key = UUID.randomUUID().toString();
        return verificationKeyRepository.addVerificationKey(
                new VerificationKey(key, profile));
    }

    @Override
    public Profile verifyProfile(Verify verify) {
        VerificationKey verificationKey = findVerificationKeyByKey(verify.getVerifyKey());
        if (verificationKey == null) {
            throw new EmailVerificationException();
        }

        Profile profile = verificationKey.getProfile();
        if (!profile.getEmail().equalsIgnoreCase(verify.getEmail())) {
            throw new EmailVerificationException();
        }

        return profileService.enableProfile(profile);
    }

    @Override
    public VerificationKey findVerificationTokenByProfileId(String profileId) {
        return verificationKeyRepository.findVerificationKeyByProfileId(profileId);
    }
}
