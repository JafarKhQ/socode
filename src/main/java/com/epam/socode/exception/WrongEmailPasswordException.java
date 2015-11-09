package com.epam.socode.exception;

public class WrongEmailPasswordException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -8709958502945693359L;

    public WrongEmailPasswordException(String message){
        super(message);
    }
}
