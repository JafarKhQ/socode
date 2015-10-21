package com.epam.socode.repository.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.epam.socode.domain.ProfileToken;
import com.epam.socode.repository.AuthenticationRepository;

@Repository
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private Map<String, ProfileToken> inMemoryTokenRepository = new ConcurrentHashMap<>();

    @Override
    public ProfileToken createToken(String profileId, String token) {
        ProfileToken userToken = new ProfileToken(token);
        inMemoryTokenRepository.put(profileId, userToken);
        return userToken;
    }

    @Override
    public ProfileToken findTokenByProfileId(String profileId) {
        return inMemoryTokenRepository.get(profileId);
    }

    @Override
    public void removeToken(String profileId) {
        inMemoryTokenRepository.remove(profileId);
    }
}
