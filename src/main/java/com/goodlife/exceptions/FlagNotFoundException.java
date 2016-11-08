package com.goodlife.exceptions;

public class FlagNotFoundException extends Exception  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlagNotFoundException () {

    }

    public FlagNotFoundException (String message) {
        super (message);
    }

    public FlagNotFoundException (Throwable cause) {
        super (cause);
    }

    public FlagNotFoundException (String message, Throwable cause) {
        super (message, cause);
    }
}
