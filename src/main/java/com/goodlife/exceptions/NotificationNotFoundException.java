package com.goodlife.exceptions;

public class NotificationNotFoundException extends Exception  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotificationNotFoundException () {

    }

    public NotificationNotFoundException (String message) {
        super (message);
    }

    public NotificationNotFoundException (Throwable cause) {
        super (cause);
    }

    public NotificationNotFoundException (String message, Throwable cause) {
        super (message, cause);
    }
}
