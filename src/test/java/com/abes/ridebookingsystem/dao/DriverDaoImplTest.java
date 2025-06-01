package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Driver;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DriverDaoImplTest {

    private DriverDaoImpl driverDao;
    private Driver sampleDriver;

    @BeforeEach
    public void setUp() {
    	// This will run before each test method

        // Clear the driver map to start 
      
        DataCollection.driverMap.clear();

        // Create a new DriverDaoImpl object
        driverDao = new DriverDaoImpl();

     // Create a sample driver object for testing
        sampleDriver = new Driver(
                "D001", "Ravi Kumar", "ravi@example.com", "pass123",
                "UP65AB1234", true
        );
    }

    @Test
    public void testAddDriver() {
    	
    	 // Add the sample driver
        driverDao.addDriver(sampleDriver);
        
     // Check if the driver was added successfully
        assertEquals(1, DataCollection.driverMap.size());
        assertTrue(DataCollection.driverMap.containsKey("D001"));
    }

    @Test
    public void testGetDriverById() {
    	
    	 // Add the sample driver
        driverDao.addDriver(sampleDriver);
        
     // Try to get the driver by ID
        Driver retrieved = driverDao.getDriverById("D001");
        // Check if the driver is found and has correct data
        assertNotNull(retrieved);
        assertEquals("Ravi Kumar", retrieved.getName());
    }

    @Test
    public void testUpdateDriver() {
    	
    	// Add the sample driver
        driverDao.addDriver(sampleDriver);

        // Change availability to false
        sampleDriver.setAvailable(false);
        
        // Update the driver
        driverDao.updateDriver(sampleDriver);

     // Get the updated driver and check if the change was saved
        Driver updated = driverDao.getDriverById("D001");
        assertFalse(updated.isAvailable());
    }

    @Test
    public void testDeleteDriver() {
        // Add the sample driver
        driverDao.addDriver(sampleDriver);
        // Delete the driver by ID
        driverDao.deleteDriver("D001");
     // Try to get the deleted driver (should be null)
        assertNull(driverDao.getDriverById("D001"));
    }

    @Test
    public void testGetAllDrivers() {
    	// Add the sample driver
        driverDao.addDriver(sampleDriver);
     // Get all drivers

        Map<String, Driver> allDrivers = driverDao.getAllDrivers();
        // Check if the driver is present in the list
        assertEquals(1, allDrivers.size());
        assertTrue(allDrivers.containsKey("D001"));
    }
}
