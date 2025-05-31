package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.DriverDao;
import com.abes.ridebookingsystem.dao.DriverDaoImpl;
import com.abes.ridebookingsystem.dto.Driver;

import java.util.Map;

public class DriverServiceImpl implements DriverService {
	 // Create an object of DriverDaoImpl to use DAO methods
    private final DriverDao driverDao = new DriverDaoImpl();

    // Add a new driver by calling the DAO method
    @Override
    public void addDriver(Driver driver) {
        driverDao.addDriver(driver);
    }

    // Get a driver using their ID by calling the DAO method
    @Override
    public Driver getDriverById(String userId) {
        return driverDao.getDriverById(userId);
    }

 // Update driver details by calling the DAO method
    @Override
    public void updateDriver(Driver driver) {
        driverDao.updateDriver(driver);
    }
    
 // Delete a driver using their ID by calling the DAO method
    @Override
    public void deleteDriver(String userId) {
        driverDao.deleteDriver(userId);
    }

 // Get all drivers from DAO and return them
    @Override
    public Map<String, Driver> getAllDrivers() {
        return driverDao.getAllDrivers();
    }
}
