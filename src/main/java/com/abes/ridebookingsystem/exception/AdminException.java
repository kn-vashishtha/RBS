package com.abes.ridebookingsystem.exception;

public class AdminException extends Exception {
	public AdminException() {                                    // Default constructor
		super();
	}

	public AdminException(String message) {                 
	                                                             // Constructor with error message      
		super(message);
	}

	public AdminException(String message, Throwable cause) {    // Constructor with error message and cause
		super(message, cause);
	}
}
