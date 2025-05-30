package com.abes.ridebookingsystem.dto;

import java.util.HashSet;
import java.util.Set;

public class Ride {
	private String rideId;
	private String customerId;
	private String driverId;
	private String source;
	private String destination;
	private double fare;
	private boolean isCancelled; // added cancellation flag
	private RideStatus status; // Add ride status
	private Set<String> rejectedDriverIds; // Track drivers who rejected

	public Ride(String rideId, String customerId, String driverId, String source, String destination, double fare) {
		this.rideId = rideId;
		this.customerId = customerId;
		this.driverId = null;
		this.source = source;
		this.destination = destination;
		this.fare = fare;
		this.isCancelled = false; // default not cancelled
		this.status = RideStatus.REQUESTED; // Default to REQUESTED
		this.rejectedDriverIds = new HashSet<>();
	}

	// Getters and setters for new fields
	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus status) {
		this.status = status;
	}

	public Set<String> getRejectedDriverIds() {
		return rejectedDriverIds;
	}

	// Add driver to rejected set
	public void addRejectedDriver(String driverId) {
		rejectedDriverIds.add(driverId);
	}

	public boolean hasDriverRejected(String driverId) {
		return rejectedDriverIds.contains(driverId);
	}

	// Existing getters/setters omitted for brevity
	public String getRideId() {
		return rideId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getDriverId() {
		return driverId;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void cancelRide() {
		this.isCancelled = true;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "Ride{" + "rideId='" + rideId + '\'' + ", customerId='" + customerId + '\'' + ", driverId='" + driverId
				+ '\'' + ", source='" + source + '\'' + ", destination='" + destination + '\'' + ", fare=" + fare
				+ ", status=" + status + ", rejectedDriverIds=" + rejectedDriverIds + '}';
	}
}
