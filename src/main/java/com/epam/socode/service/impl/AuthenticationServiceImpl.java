package com.epam.socode.service.impl;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.ProfileToken;
import com.epam.socode.excepion.ExpiredTokenException;
import com.epam.socode.excepion.InvalidTokenException;
import com.epam.socode.excepion.NotAuthenticatedProfileException;
import com.epam.socode.excepion.WrongEmailPasswordException;
import com.epam.socode.repository.AuthenticationRepository;
import com.epam.socode.request.Login;
import com.epam.socode.request.Logout;
import com.epam.socode.service.AuthenticationService;
import com.epam.socode.service.ProfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private ProfileService profileService;

    @Value("${token.expiration.time}")
    private int expirationTimeMin;

    @Override
    public String login(Login login) {
        final Profile profile = profileService.findProfileByEmail(login.getLogin());
        if (null == profile) {
            throw new WrongEmailPasswordException();
        }

        if (!profile.getPassword().equals(login.getPassword())) {
            throw new WrongEmailPasswordException();
        }

        final String token = generateToken(profile);
        ProfileToken userToken = authenticationRepository.createToken(profile.getProfileId(), token);
        return userToken.getToken();
    }

    @Override
    public void vaildateProfileToken(String profileId, String token) {
        ProfileToken profileToken = authenticationRepository.findTokenByProfileId(profileId);
        if (null == profileToken) {
            throw new NotAuthenticatedProfileException();
        }

        if (!profileToken.getToken().equals(token)) {
            throw new InvalidTokenException();
        }

        long currentTime = System.currentTimeMillis();
        long expireAt = profileToken.getLastUsedTime() + TimeUnit.MINUTES.toMillis(expirationTimeMin);
        if (currentTime > expireAt) {
            throw new ExpiredTokenException();
        } else {
            profileToken.setLastUsedTime(currentTime);
        }
    }

    @Override
    public void logout(Logout logout) {
        vaildateProfileToken(logout.getProfileId(), logout.getToken());
        authenticationRepository.removeToken(logout.getProfileId());
    }

    private String generateToken(Profile profile) {
        String token = profile.getProfileId() + ":" + profile.getEmail();
        try {
            token = encryptSHA256(token);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return token;
    }

    private String encryptSHA256(String source) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(source.getBytes());
        byte[] data = md.digest();
        return String.format("%0" + (data.length * 2) + 'x', new BigInteger(1, data));
    }

}
