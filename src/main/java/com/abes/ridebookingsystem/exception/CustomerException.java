package com.abes.ridebookingsystem.exception;

// This class is used to handle custom exceptions related to customers
public class CustomerException extends Exception {

	 // Default constructor
	public CustomerException() {
		super();
	}

	// Constructor that accepts a custom error message
	public CustomerException(String message) {
		super(message);
	}

	// Constructor that accepts a custom message
	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}
}

