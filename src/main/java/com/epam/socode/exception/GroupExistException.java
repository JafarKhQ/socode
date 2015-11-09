package com.epam.socode.exception;

public class GroupExistException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7696823417569918513L;
	
	public GroupExistException(String message) {
		super(message);
	}
}
