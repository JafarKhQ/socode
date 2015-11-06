package com.epam.socode.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.socode.domain.Profile;
import com.epam.socode.domain.ProfileToken;
import com.epam.socode.exception.ExpiredTokenException;
import com.epam.socode.exception.InvalidTokenException;
import com.epam.socode.exception.ProfileNotFoundException;
import com.epam.socode.exception.WrongEmailPasswordException;
import com.epam.socode.repository.AuthenticationRepository;
import com.epam.socode.request.Login;
import com.epam.socode.request.Logout;
import com.epam.socode.service.AuthenticationService;
import com.epam.socode.service.ProfileService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private ProfileService profileService;

    @Value("${token.expiration.time}")
    private int expirationTimeMin;

    @Override
    public String login(Login login) {
        Profile profile = null;
        try {
            profile = profileService.findProfileByEmail(login.getLogin());
        } catch (ProfileNotFoundException e) {
            throw new WrongEmailPasswordException("Login or password incorrect");
        }

        if (!profile.getPassword().equals(login.getPassword())) {
            throw new WrongEmailPasswordException("Login or password incorrect");
        }

        final String token = generateToken(profile);
        ProfileToken userToken = authenticationRepository.createToken(profile.getProfileId(), token);
        return userToken.getToken();
    }

    @Override
    public void validateToken(String token) {
        ProfileToken profileToken = authenticationRepository.findToken(token);
        if (null == profileToken) {
            throw new InvalidTokenException("Token or profile ID incorrect");
        }

        long currentTime = System.currentTimeMillis();
        long expireAt = profileToken.getLastUsedTime() + TimeUnit.MINUTES.toMillis(expirationTimeMin);
        if (currentTime > expireAt) {
            throw new ExpiredTokenException("Token or profile ID incorrect");
        } else {
            profileToken.setLastUsedTime(currentTime);
        }
    }

    @Override
    public void logout(Logout logout) {
        validateToken(logout.getToken());
        authenticationRepository.removeToken(logout.getToken());
    }

    @Override
    public String findProfileIdByToken(String token) {
        ProfileToken profileToken = authenticationRepository.findToken(token);
        return profileToken.getProfileId();
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

    public int getExpirationTimeMin() {
        return expirationTimeMin;
    }

    @Override
    public void setExpirationTimeMin(int expirationTimeMin) {
        this.expirationTimeMin = expirationTimeMin;
    }
}
