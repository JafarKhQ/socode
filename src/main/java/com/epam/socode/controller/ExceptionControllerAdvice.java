package com.epam.socode.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.epam.socode.excepion.EmailVerificationException;
import com.epam.socode.response.ErrorCodes;
import com.epam.socode.response.Response;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EmailVerificationException.class)
    public Response handleEmailVerfication() {
        return Response.newErrorResponse(ErrorCodes.EMAIL_VERIFICATION_ERROR);
    }
}
