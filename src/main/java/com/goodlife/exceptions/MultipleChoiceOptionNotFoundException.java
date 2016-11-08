package com.goodlife.exceptions;

public class MultipleChoiceOptionNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MultipleChoiceOptionNotFoundException () {

	}

	public MultipleChoiceOptionNotFoundException (String message) {
	   super (message);
	}

	public MultipleChoiceOptionNotFoundException (Throwable cause) {
	   super (cause);
	}

	public MultipleChoiceOptionNotFoundException (String message, Throwable cause) {
	   super (message, cause);
	}

}
