package com.abes.ridebookingsystem.exception;

public class RideException extends Exception {
	public RideException() {
		super();
	}

	public RideException(String message) {
		super(message);
	}

	public RideException(String message, Throwable cause) {
		super(message, cause);
	}
}
