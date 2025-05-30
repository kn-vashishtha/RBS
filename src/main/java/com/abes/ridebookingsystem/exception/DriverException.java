package com.abes.ridebookingsystem.exception;

//This class is used to create custom exceptions related to Driver
public class DriverException extends Exception {
	 // Default constructor, when we just want to throw an exception without any message
	public DriverException() {
		super();
	}
	 // Constructor to throw an exception with a message we want to show
	public DriverException(String message) {
		super(message);
	}

    // Constructor to throw an exception with a message and also include the reason 
	public DriverException(String message, Throwable cause) {
		super(message, cause);
	}
}
