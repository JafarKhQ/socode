package com.epam.socode.service;

import com.epam.socode.request.Login;
import com.epam.socode.request.Logout;

public interface AuthenticationService {

    String login(Login login);

    void vaildateProfileToken(String profileId, String token);

    void logout(Logout logout);
}
