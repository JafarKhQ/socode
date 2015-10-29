package com.epam.socode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.VerificationToken;
import com.epam.socode.exception.EmailVerificationException;
import com.epam.socode.repository.VerificationTokenRepository;
import com.epam.socode.request.Verify;
import com.epam.socode.service.ProfileService;
import com.epam.socode.service.VerificationTokenService;

/**
 * @author jafar_qaddoumi
 */
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private ProfileService profileService;

    @Override
    public VerificationToken findVerificationTokenByKey(String verifykey) {
        return tokenRepository.findVerificationTokenByKey(verifykey);
    }

    @Override
    public VerificationToken createVerificationToken(Profile profile, String token) {
        VerificationToken verificationToken = new VerificationToken(token, profile);
        tokenRepository.addVerificationToken(verificationToken);
        return verificationToken;
    }

    @Override
    public Profile verifyProfile(Verify verify) {
        VerificationToken verificationToken = findVerificationTokenByKey(verify.getVerifykey());
        if (verificationToken == null) {
            throw new EmailVerificationException();
        }

        Profile profile = verificationToken.getProfile();
        if (!profile.getEmail().equalsIgnoreCase(verify.getEmail())) {
            throw new EmailVerificationException();
        }

        return profileService.enableProfile(profile);
    }

    @Override
    public VerificationToken findVerificationTokenByProfileId(String profileId) {
        return tokenRepository.findVerificationTokenByProfileId(profileId);
    }
}
