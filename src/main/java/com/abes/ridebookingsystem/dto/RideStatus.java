package com.abes.ridebookingsystem.dto;

public enum RideStatus {
	REQUESTED, // Ride is available for drivers to accept/reject
	ACCEPTED, // Ride accepted by one driver
	CANCELLED, // Ride cancelled by customer
	COMPLETED // Ride completed
}
