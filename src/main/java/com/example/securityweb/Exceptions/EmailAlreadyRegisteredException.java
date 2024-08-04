package com.example.securityweb.Exceptions;

public class EmailAlreadyRegisteredException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
