package com.abes.ridebookingsystem.exception;

//Custom exception class for handling user-related errors

public class UserException extends Exception {
	
	//Constructs a new UserException with the specified detail message
	
	public UserException(String message) {
		super(message);
	}
}

