package com.epam.socode.repository.impl;

import com.epam.socode.domain.ProfileToken;
import com.epam.socode.repository.AuthenticationRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private Map<String, ProfileToken> inMemoryTokenRepository = new ConcurrentHashMap<>();

    @Override
    public ProfileToken createToken(String profileId, String token) {
        ProfileToken userToken = new ProfileToken(profileId, token);
        inMemoryTokenRepository.put(token, userToken);
        return userToken;
    }

    @Override
    public ProfileToken findToken(String token) {
        return inMemoryTokenRepository.get(token);
    }

    @Override
    public void removeToken(String token) {
        inMemoryTokenRepository.remove(token);
    }
}
