package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Ride;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RideDaoImplTest {

    private RideDao rideDao;
    void setUp() {
        // Initialize a new RideDaoImpl instance before each test
        rideDao = new RideDaoImpl();
        // Clear the ride map to ensure tests are independent
        DataCollection.rideMap.clear();
    }

    //Test
    void testAddRide() {
        // Create a new ride object
        Ride ride = new Ride("R001", "Aman", null, "Delhi", "Agra", 0.0);
        // Add ride to the DAO
        rideDao.addRide(ride);

        // Retrieve ride by ID and verify it's added
        Ride result = rideDao.getRideById("R001");
        assertNotNull(result);
        assertEquals("Aman", result.getCustomerId());
    }

    //Test
    void testGetRideById() {
        // Manually add a ride to the ride map
        Ride ride = new Ride("R002", "Riya", null, "Lucknow", "Kanpur", 0.0);
        DataCollection.rideMap.put("R002", ride);

        // Fetch the ride and verify details
        Ride result = rideDao.getRideById("R002");
        assertNotNull(result);
        assertEquals("Riya", result.getCustomerId());
    }

    //Test
    void testUpdateRide() {
        // Add an initial ride
        Ride ride = new Ride("R003", "Mohit", null, "Gurgaon", "Noida", 0.0);
        rideDao.addRide(ride);

        // Create an updated ride object with the same ID
        Ride updatedRide = new Ride("R003", "Mohit", null, "Faridabad", "Ghaziabad", 0.0);
        rideDao.updateRide(updatedRide);

        // Retrieve and verify that the ride details are updated
        Ride result = rideDao.getRideById("R003");
        assertEquals("Faridabad", result.getSource());
    }

    //Test
    void testDeleteRide() {
        // Add a ride
        Ride ride = new Ride("R004", "Neha", null, "Mumbai", "Pune", 0.0);
        rideDao.addRide(ride);

        // Delete the ride
        rideDao.deleteRide("R004");

        // Verify the ride is removed
        assertNull(rideDao.getRideById("R004"));
    }

    //Test
    void testGetAllRides() {
        // Add multiple rides
        Ride ride1 = new Ride("R005", "Siddharth", null, "Chennai", "Bangalore", 0.0);
        Ride ride2 = new Ride("R006", "Pooja", null, "Kolkata", "Ranchi", 0.0);
        rideDao.addRide(ride1);
        rideDao.addRide(ride2);

        // Retrieve all rides and verify their presence
        Map<String, Ride> allRides = rideDao.getAllRides();
        assertEquals(2, allRides.size());
        assertTrue(allRides.containsKey("R005"));
        assertTrue(allRides.containsKey("R006"));
    }
}
