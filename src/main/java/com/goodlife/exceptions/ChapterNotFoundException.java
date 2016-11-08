package com.goodlife.exceptions;

public class ChapterNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ChapterNotFoundException () {

	}

	public ChapterNotFoundException (String message) {
	   super (message);
	}

	public ChapterNotFoundException (Throwable cause) {
	   super (cause);
	}

	public ChapterNotFoundException (String message, Throwable cause) {
	   super (message, cause);
	}

}
