package com.epam.socode.exception;

public class ExpiredTokenException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -4173075906789195935L;

    public ExpiredTokenException(String message){
        super(message);
    }
}
