package com.goodlife.exceptions;

public class StateNotFoundException extends Exception  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StateNotFoundException () {

    }

    public StateNotFoundException (String message) {
        super (message);
    }

    public StateNotFoundException (Throwable cause) {
        super (cause);
    }

    public StateNotFoundException (String message, Throwable cause) {
        super (message, cause);
    }
}
