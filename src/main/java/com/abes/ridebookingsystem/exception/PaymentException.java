package com.abes.ridebookingsystem.exception;

// This is a custom exception for payment errors in the app.
// Whenever something goes wrong with payment stuff, we throw this.

public class PaymentException extends Exception {

	// No message, just a plain exception.
	public PaymentException() {
		super();
	}

	// Create an exception with a message explaining what went wrong.
	public PaymentException(String message) {
		super(message);
	}

	// Create an exception with a message AND the original error that caused it.
	// This helps keep track of what really caused the problem.
	public PaymentException(String message, Throwable cause) {
		super(message, cause);
	}
}
