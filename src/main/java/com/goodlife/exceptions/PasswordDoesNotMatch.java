package com.goodlife.exceptions;

public class PasswordDoesNotMatch extends Exception  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordDoesNotMatch () {

    }

    public PasswordDoesNotMatch (String message) {
        super (message);
    }

    public PasswordDoesNotMatch (Throwable cause) {
        super (cause);
    }

    public PasswordDoesNotMatch (String message, Throwable cause) {
        super (message, cause);
    }
}
