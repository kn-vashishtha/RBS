package com.abes.ridebookingsystem.dto;

//Driver class extends User class because a driver is also a user of the system
public class Driver extends User {
	
	private String licenseNumber;    //driver's license number
	private boolean isAvailable;     //check whether driver is free or not to take ride

	// Constructor to create a new Driver object
	public Driver(String userId, String name, String email, String password, String licenseNumber,
			boolean isAvailable) {
		
		// Calling User class constructor to set userId, name, email, password
		super(userId, name, email, password);
		
		this.licenseNumber = licenseNumber;   // setting license number for the driver
		this.isAvailable = isAvailable;      // setting availability status of driver
	}
	
	// Getter method to get the license number of the driver
	public String getLicenseNumber() {
		return licenseNumber;
	}
	// Setter method to update the license number 
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	// This method tells if the driver is available or not
	public boolean isAvailable() {
		return isAvailable;
	}
	// This method changes the availability status of the driver
	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	 // This method returns  driver details for printing the information
	@Override
	public String toString() {
		return "Driver{" + "userId='" + userId + '\'' + ", name='" + name + '\'' + ", email='" + email + '\''
				+ ", licenseNumber='" + licenseNumber + '\'' + ", available=" + isAvailable + '}';
	}
}