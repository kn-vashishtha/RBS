package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.CustomerDaoImpl;
import com.abes.ridebookingsystem.dao.RideDaoImpl;
import com.abes.ridebookingsystem.dto.Customer;
import com.abes.ridebookingsystem.dto.Ride;
import com.abes.ridebookingsystem.dto.RideStatus;
import com.abes.ridebookingsystem.exception.RideException;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RideServiceImplTest {

    private RideServiceImpl rideService;
    void setUp() {
        // Initialize RideServiceImpl before each test
        rideService = new RideServiceImpl();

        // Clear in-memory storage to ensure test isolation
        DataCollection.rideMap.clear();
        DataCollection.customerMap.clear();
        DataCollection.driverAvailability.clear();

        // Add two customers for testing
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        customerDao.addCustomer(new Customer("cust1", "Rahul", "rahul.sharma@example.com", "pass123", "9876543210"));
        customerDao.addCustomer(new Customer("cust2", "Priya", "priya.mehta@example.com", "pass456", "9123456780"));
    }

    //Test
    void testBookRideSuccess() throws RideException {
        // Test booking a ride successfully for a valid customer
        Ride ride = rideService.bookRide("cust1", "A", "B", 100.0);
        assertNotNull(ride);
        assertEquals("cust1", ride.getCustomerId());
        assertEquals(RideStatus.REQUESTED, ride.getStatus());
    }

    //Test
    void testBookRideWithInvalidCustomer() {
        // Try booking a ride with an invalid customer ID
        RideException ex = assertThrows(RideException.class,
                () -> rideService.bookRide("invalid", "X", "Y", 100.0));
        assertEquals("Customer not found", ex.getMessage());
    }

    //Test
    void testBookRideWithExistingActiveRide() throws RideException {
        // Book a ride first
        rideService.bookRide("cust1", "A", "B", 100.0);
        // Try booking another ride for the same customer before completing the first
        RideException ex = assertThrows(RideException.class,
                () -> rideService.bookRide("cust1", "C", "D", 120.0));
        assertTrue(ex.getMessage().contains("You already have an active ride"));
    }

    //Test
    void testGetVisibleRidesForDriver() throws RideException {
        // Book a ride and make a driver available
        Ride ride1 = rideService.bookRide("cust1", "A", "B", 90.0);
        DataCollection.driverAvailability.put("driver1", true);

        // Get rides visible to the driver
        List<Ride> rides = rideService.getVisibleRidesForDriver("driver1");
        assertEquals(1, rides.size());
        assertEquals(ride1.getRideId(), rides.get(0).getRideId());
    }

    //Test
    void testAcceptRideSuccess() throws RideException {
        // Book a ride
        Ride ride = rideService.bookRide("cust1", "A", "B", 150.0);
        // Set driver availability
        DataCollection.driverAvailability.put("driver1", true);

        // Attempt to accept the ride
        boolean result = rideService.acceptRide("driver1", ride.getRideId());
        assertTrue(result);

        // Validate ride status and driver assignment
        Ride updated = rideService.getRideById(ride.getRideId());
        assertEquals(RideStatus.ACCEPTED, updated.getStatus());
        assertEquals("driver1", updated.getDriverId());
        assertFalse(DataCollection.driverAvailability.get("driver1"));
    }

    //Test
    void testRejectRide() throws RideException {
        // Book a ride
        Ride ride = rideService.bookRide("cust2", "C", "D", 110.0);

        // Driver rejects the ride
        boolean rejected = rideService.rejectRide("driver1", ride.getRideId());
        assertTrue(rejected);
        assertTrue(ride.getRejectedDriverIds().contains("driver1"));
    }

    //Test
    void testCompleteRide() throws RideException {
        // Book and manually update ride as accepted
        Ride ride = rideService.bookRide("cust1", "A", "B", 100);
        ride.setDriverId("driver1");
        ride.setStatus(RideStatus.ACCEPTED);
        new RideDaoImpl().updateRide(ride);

        // Set driver as unavailable before completion
        DataCollection.driverAvailability.put("driver1", false);

        // Complete the ride
        rideService.completeRide(ride.getRideId());

        // Verify ride status and driver availability
        Ride updated = rideService.getRideById(ride.getRideId());
        assertEquals(RideStatus.COMPLETED, updated.getStatus());
        assertTrue(DataCollection.driverAvailability.get("driver1"));
    }

    //Test
    void testCancelRide() throws RideException {
        // Book and manually update ride as accepted
        Ride ride = rideService.bookRide("cust1", "X", "Y", 100);
        ride.setDriverId("driver2");
        ride.setStatus(RideStatus.ACCEPTED);
        new RideDaoImpl().updateRide(ride);

        // Set driver as unavailable before cancellation
        DataCollection.driverAvailability.put("driver2", false);

        // Cancel the ride
        rideService.cancelRide(ride.getRideId());

        // Validate ride status and driver availability
        Ride updated = rideService.getRideById(ride.getRideId());
        assertEquals(RideStatus.CANCELLED, updated.getStatus());
        assertTrue(DataCollection.driverAvailability.get("driver2"));
    }

    //Test
    void testGetRideByIdReturnsCorrectRide() throws RideException {
        // Book a ride
        Ride ride = rideService.bookRide("cust1", "P", "Q", 70.0);

        // Fetch ride by ID and check details
        Ride fetched = rideService.getRideById(ride.getRideId());
        assertEquals(ride.getRideId(), fetched.getRideId());
    }

    //Test
    void testGetAllRides() throws RideException {
        // Book two rides
        rideService.bookRide("cust1", "A", "B", 100);
        rideService.bookRide("cust2", "C", "D", 120);

        // Retrieve all rides and verify count
        Map<String, Ride> allRides = rideService.getAllRides();
        assertEquals(2, allRides.size());
    }
}
