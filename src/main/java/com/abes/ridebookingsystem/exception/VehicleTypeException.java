package com.abes.ridebookingsystem.exception;

public class VehicleTypeException extends Exception {
	public VehicleTypeException() {
		super();
	}

	public VehicleTypeException(String message) {
		super(message);
	}

	public VehicleTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
