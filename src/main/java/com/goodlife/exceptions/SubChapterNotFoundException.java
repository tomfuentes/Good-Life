package com.goodlife.exceptions;

public class SubChapterNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public SubChapterNotFoundException () {

	}

	public SubChapterNotFoundException (String message) {
	   super (message);
	}

	public SubChapterNotFoundException (Throwable cause) {
	   super (cause);
	}

	public SubChapterNotFoundException (String message, Throwable cause) {
	   super (message, cause);
	}

}
