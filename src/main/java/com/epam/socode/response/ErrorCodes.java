package com.epam.socode.response;

/**
 * 
 * @author Krystian_Balwierz
 *
 */
public final class ErrorCodes {

    public static final int WRONG_EMAIL_PASSWORD_ERROR = 400;
    public static final int EMAIL_VERIFICATION_ERROR = 401;
    public static final int NOT_AUTHENTICATED_PROFILE_ERROR = 402;
    public static final int INVALID_TOKEN_ERROR = 403;
    public static final int EXPIRED_TOKEN_ERROR = 404;
    public static final int PROFILE_EXIST_ERROR = 405;

    private ErrorCodes() {
        throw new IllegalAccessError();
    }
}
