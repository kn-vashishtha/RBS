package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Driver;

import java.util.Map;

public interface DriverService {
	
// Add a new driver to the system
    void addDriver(Driver driver);
    
// Get driver details by their user ID
    Driver getDriverById(String userId);
    
// Update existing driver information
    void updateDriver(Driver driver);
    
// Remove a driver from the system using their user ID
    void deleteDriver(String userId);
    
// Get a list of all drivers in the system
    Map<String, Driver> getAllDrivers();
}

