package com.goodlife.exceptions;

public class MultipleChoiceNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MultipleChoiceNotFoundException () {

	}

	public MultipleChoiceNotFoundException (String message) {
	   super (message);
	}

	public MultipleChoiceNotFoundException (Throwable cause) {
	   super (cause);
	}

	public MultipleChoiceNotFoundException (String message, Throwable cause) {
	   super (message, cause);
	}

}
