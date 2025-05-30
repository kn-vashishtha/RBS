package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Driver;

import java.util.Map;

//This is an interface for handling all driver-related database work
public interface DriverDao {
	
	// This method will add a new driver to the system
	void addDriver(Driver driver);

	//Search and get driver details using their userId
	Driver getDriverById(String userId);

	//Update existing driver details
	void updateDriver(Driver driver);

	//	Remove a driver from the system
	void deleteDriver(String userId);

	//	Get a list of all drivers in a Map where  key is a userId(String) and value is Driver object.
	Map<String, Driver> getAllDrivers();
	
}
