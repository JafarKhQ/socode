package com.epam.socode.exception;

public class InvalidTokenException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -3657730792448940039L;

    public InvalidTokenException(String message){
        super(message);
    }
}
