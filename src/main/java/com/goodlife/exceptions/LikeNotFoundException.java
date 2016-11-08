package com.goodlife.exceptions;

public class LikeNotFoundException extends Exception  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LikeNotFoundException () {

    }

    public LikeNotFoundException (String message) {
        super (message);
    }

    public LikeNotFoundException (Throwable cause) {
        super (cause);
    }

    public LikeNotFoundException (String message, Throwable cause) {
        super (message, cause);
    }
}
