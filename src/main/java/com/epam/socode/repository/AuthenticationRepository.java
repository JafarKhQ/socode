package com.epam.socode.repository;

import com.epam.socode.domain.ProfileToken;

public interface AuthenticationRepository {

    ProfileToken createToken(String profileId, String token);

    ProfileToken findToken(String token);

    void removeToken(String token);
}
