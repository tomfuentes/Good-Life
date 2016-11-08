package com.goodlife.exceptions;

public class InvalidEmailToken extends Exception  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEmailToken () {

    }

    public InvalidEmailToken (String message) {
        super (message);
    }

    public InvalidEmailToken (Throwable cause) {
        super (cause);
    }

    public InvalidEmailToken (String message, Throwable cause) {
        super (message, cause);
    }
}
