package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceImplTest {

    private DriverService driverService;

    @BeforeEach
    void setUp() {
    	// Create a new object of DriverServiceImpl before each test
        driverService = new DriverServiceImpl();
        // Clear all previous drivers to avoid test conflicts
        Map<String, Driver> allDrivers = driverService.getAllDrivers();
        allDrivers.clear();
    }

    @Test
    void testAddAndGetDriver() {
    	
    	 // Create and add a new driver
        Driver driver = new Driver("d1", "Arun", "arun@example.com", "Arun@123", "UP32 AB1234", true);
        driverService.addDriver(driver);

        // Fetch the driver by ID
        Driver result = driverService.getDriverById("d1");

        // Check if the driver is added correctly
        assertNotNull(result);
        assertEquals("Arun", result.getName());
        assertEquals("UP32 AB1234", result.getLicenseNumber());
        assertTrue(result.isAvailable());
    }

    @Test
    void testUpdateDriver() {
    	
    	// Add a driver
        Driver driver = new Driver("d2", "Ravi", "ravi@example.com", "Ravi@123", "UP32 XY9876", true);
        driverService.addDriver(driver);

        // Update some details of the driver
        driver.setName("Ravi Kumar");
        driver.setLicenseNumber("UP32 NEW1234");
        driver.setAvailable(false);
        
        // Save the updated driver
        driverService.updateDriver(driver);

        // Fetch the updated driver and check changes
        Driver updated = driverService.getDriverById("d2");

        assertEquals("Ravi Kumar", updated.getName());
        assertEquals("UP32 NEW1234", updated.getLicenseNumber());
        assertFalse(updated.isAvailable());
    }

    @Test
    void testDeleteDriver() {
    	 // Add a driver
        Driver driver = new Driver("d3", "Suresh", "suresh@example.com", "Suresh@123", "UP32 ZY4567", true);
        driverService.addDriver(driver);

        // Delete the driver
        driverService.deleteDriver("d3");

     // Check if the driver was removed
        assertNull(driverService.getDriverById("d3"));
    }

    @Test
    void testGetAllDrivers() {
    	
    	// Add two drivers
        driverService.addDriver(new Driver("d4", "Ramesh", "ram@example.com", "Ram@123", "UP32 XX1111", true));
        driverService.addDriver(new Driver("d5", "Mahesh", "mah@example.com", "Mah@123", "UP32 YY2222", false));

        // Get all drivers
        Map<String, Driver> allDrivers = driverService.getAllDrivers();

        // Check if both drivers are present
        assertEquals(2, allDrivers.size());
        assertTrue(allDrivers.containsKey("d4"));
        assertTrue(allDrivers.containsKey("d5"));
    }
}
