package com.epam.socode.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.socode.exception.EmailVerificationException;
import com.epam.socode.exception.ExpiredTokenException;
import com.epam.socode.exception.InvalidTokenException;
import com.epam.socode.exception.ProfileExistException;
import com.epam.socode.exception.WrongEmailPasswordException;
import com.epam.socode.response.ErrorCodes;
import com.epam.socode.response.Response;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(EmailVerificationException.class)
    @ResponseBody
    public Response handleEmailVerificationException(Exception e) {
        return Response.newErrorResponse(ErrorCodes.EMAIL_VERIFICATION_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(WrongEmailPasswordException.class)
    @ResponseBody
    public Response handleWrongEmailPasswordException(Exception e) {
        return Response.newErrorResponse(ErrorCodes.WRONG_EMAIL_PASSWORD_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(InvalidTokenException.class)
    @ResponseBody
    public Response handleInvalidTokenException(Exception e) {
        return Response.newErrorResponse(ErrorCodes.INVALID_TOKEN_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ExpiredTokenException.class)
    @ResponseBody
    public Response handleExpiredTokenException(Exception e) {
        return Response.newErrorResponse(ErrorCodes.EXPIRED_TOKEN_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ProfileExistException.class)
    @ResponseBody
    public Response handleProfileExistException(Exception e) {
        return Response.newErrorResponse(ErrorCodes.PROFILE_EXIST_ERROR);
    }
}
