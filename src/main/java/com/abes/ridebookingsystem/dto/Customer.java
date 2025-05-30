package com.abes.ridebookingsystem.dto;

//Customer class extends the User to represent a customer in the ride booking system


//Includes additional attributes like phone number and active ride tracking.
 
public class Customer extends User {
	private String phoneNumber;      // Customer's phone number
	private String currentRideId;    // ID of the customer's current active ride 

	
	// Constructor to initialize a Customer object
	 
	
	public Customer(String userId, String name, String email, String password, String phoneNumber) {
		super(userId, name, email, password);  // Call to superclass constructor
		this.phoneNumber = phoneNumber;
		this.currentRideId = null;  // By default no active ride
	}

	
	 // Returns the ID of the customer's current ride.
	 
	public String getCurrentRideId() {
		return currentRideId;
	}
	
	
	 // Sets the current ride ID for the customer.
	 

	public void setCurrentRideId(String rideId) {
		this.currentRideId = rideId;
	}
	
	// Checks whether the customer has an active ride.
	 
	public boolean hasActiveRide() {
		return currentRideId != null;
	}
	
	// Returns the customer's phone number.
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	 //Sets the customer's phone number.
	 
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	 // Returns a string representation of the Customer object.
	 

	@Override
	public String toString() {
		return "Customer{" + "userId='" + userId + '\'' + ", name='" + name + '\'' + ", email='" + email + '\''
				+ ", phoneNumber='" + phoneNumber + '\'' + '}';
	}
}
