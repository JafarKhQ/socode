package com.epam.socode.service;

import com.epam.socode.request.Login;
import com.epam.socode.request.Logout;

public interface AuthenticationService {

    String login(Login login);

    void validateToken(String token);

    void logout(Logout logout);

    String findProfileIdByToken(String token);
    
    public int getExpirationTimeMin();
    
    void setExpirationTimeMin(int expirationTimeMin);
}
