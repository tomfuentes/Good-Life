package com.goodlife.exceptions;

public class ChapterPageNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ChapterPageNotFoundException () {

	}

	public ChapterPageNotFoundException (String message) {
	   super (message);
	}

	public ChapterPageNotFoundException (Throwable cause) {
	   super (cause);
	}

	public ChapterPageNotFoundException (String message, Throwable cause) {
	   super (message, cause);
	}

}
