package com.abes.ridebookingsystem.dao;
import com.abes.ridebookingsystem.dto.Driver;

import com.abes.ridebookingsystem.util.DataCollection;
import java.util.Map;

//this class is implementing DriverDao interface 
public class DriverDaoImpl implements DriverDao {

	// add driver into driverMap using userId as key
    @Override
    public void addDriver(Driver driver) {
        DataCollection.driverMap.put(driver.getUserId(), driver);
    }

 // get driver from driverMap using userId
    @Override
    public Driver getDriverById(String userId) {
        return DataCollection.driverMap.get(userId);
    }

 // update driver information 
    @Override
    public void updateDriver(Driver driver) {
        DataCollection.driverMap.put(driver.getUserId(), driver);
    }

 // delete driver from map using userId
    @Override
    public void deleteDriver(String userId) {
        DataCollection.driverMap.remove(userId);
    }
    
 // return the full map of all drivers (key is userId and value is Driver object)
    @Override
    public Map<String, Driver> getAllDrivers() {
        return DataCollection.driverMap;
    }
}
