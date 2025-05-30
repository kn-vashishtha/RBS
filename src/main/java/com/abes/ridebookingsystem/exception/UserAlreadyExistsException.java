package com.abes.ridebookingsystem.exception;

// Exception thrown when attempting to add a user that already exists in the system.

public class UserAlreadyExistsException extends RuntimeException {
	
	// Constructs a new UserAlreadyExistsException with the specified detail message.
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
