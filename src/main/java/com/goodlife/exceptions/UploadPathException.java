package com.goodlife.exceptions;

public class UploadPathException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UploadPathException () {

	}

	public UploadPathException (String message) {
	   super (message);
	}

	public UploadPathException (Throwable cause) {
	   super (cause);
	}

	public UploadPathException (String message, Throwable cause) {
	   super (message, cause);
	}

}
