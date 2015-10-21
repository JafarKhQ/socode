package com.epam.socode.repository;

import com.epam.socode.domain.ProfileToken;

public interface AuthenticationRepository {

    ProfileToken createToken(String profileId, String token);

    ProfileToken findTokenByProfileId(String profileId);

    void removeToken(String profileId);
}
