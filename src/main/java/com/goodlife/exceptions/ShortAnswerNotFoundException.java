package com.goodlife.exceptions;

public class ShortAnswerNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ShortAnswerNotFoundException () {

	}

	public ShortAnswerNotFoundException (String message) {
	   super (message);
	}

	public ShortAnswerNotFoundException (Throwable cause) {
	   super (cause);
	}

	public ShortAnswerNotFoundException (String message, Throwable cause) {
	   super (message, cause);
	}

}
