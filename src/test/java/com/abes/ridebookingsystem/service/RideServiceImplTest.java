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

    @BeforeEach
    void setUp() {
        rideService = new RideServiceImpl();

        // Clear shared in-memory data
        DataCollection.rideMap.clear();
        DataCollection.customerMap.clear();
        DataCollection.driverAvailability.clear();

        // Add test customers with Indian names
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        customerDao.addCustomer(new Customer("cust1", "Rahul", "rahul.sharma@example.com", "pass123", "9876543210"));
        customerDao.addCustomer(new Customer("cust2", "Priya", "priya.mehta@example.com", "pass456", "9123456780"));
    }

    @Test
    void testBookRideSuccess() throws RideException {
        Ride ride = rideService.bookRide("cust1", "A", "B", 100.0);
        assertNotNull(ride);
        assertEquals("cust1", ride.getCustomerId());
        assertEquals(RideStatus.REQUESTED, ride.getStatus());
    }

    @Test
    void testBookRideWithInvalidCustomer() {
        RideException ex = assertThrows(RideException.class,
                () -> rideService.bookRide("invalid", "X", "Y", 100.0));
        assertEquals("Customer not found", ex.getMessage());
    }

    @Test
    void testBookRideWithExistingActiveRide() throws RideException {
        rideService.bookRide("cust1", "A", "B", 100.0);
        RideException ex = assertThrows(RideException.class,
                () -> rideService.bookRide("cust1", "C", "D", 120.0));
        assertTrue(ex.getMessage().contains("You already have an active ride"));
    }

    @Test
    void testGetVisibleRidesForDriver() throws RideException {
        Ride ride1 = rideService.bookRide("cust1", "A", "B", 90.0);
        DataCollection.driverAvailability.put("driver1", true);

        List<Ride> rides = rideService.getVisibleRidesForDriver("driver1");
        assertEquals(1, rides.size());
        assertEquals(ride1.getRideId(), rides.get(0).getRideId());
    }

    @Test
    void testAcceptRideSuccess() throws RideException {
        Ride ride = rideService.bookRide("cust1", "A", "B", 150.0);
        DataCollection.driverAvailability.put("driver1", true);

        boolean result = rideService.acceptRide("driver1", ride.getRideId());
        assertTrue(result);

        Ride updated = rideService.getRideById(ride.getRideId());
        assertEquals(RideStatus.ACCEPTED, updated.getStatus());
        assertEquals("driver1", updated.getDriverId());
        assertFalse(DataCollection.driverAvailability.get("driver1"));
    }

    @Test
    void testRejectRide() throws RideException {
        Ride ride = rideService.bookRide("cust2", "C", "D", 110.0);
        boolean rejected = rideService.rejectRide("driver1", ride.getRideId());
        assertTrue(rejected);
        assertTrue(ride.getRejectedDriverIds().contains("driver1"));
    }

    @Test
    void testCompleteRide() throws RideException {
        Ride ride = rideService.bookRide("cust1", "A", "B", 100);
        ride.setDriverId("driver1");
        ride.setStatus(RideStatus.ACCEPTED);
        new RideDaoImpl().updateRide(ride);

        DataCollection.driverAvailability.put("driver1", false);
        rideService.completeRide(ride.getRideId());

        Ride updated = rideService.getRideById(ride.getRideId());
        assertEquals(RideStatus.COMPLETED, updated.getStatus());
        assertTrue(DataCollection.driverAvailability.get("driver1"));
    }

    @Test
    void testCancelRide() throws RideException {
        Ride ride = rideService.bookRide("cust1", "X", "Y", 100);
        ride.setDriverId("driver2");
        ride.setStatus(RideStatus.ACCEPTED);
        new RideDaoImpl().updateRide(ride);

        DataCollection.driverAvailability.put("driver2", false);
        rideService.cancelRide(ride.getRideId());

        Ride updated = rideService.getRideById(ride.getRideId());
        assertEquals(RideStatus.CANCELLED, updated.getStatus());
        assertTrue(DataCollection.driverAvailability.get("driver2"));
    }

    @Test
    void testGetRideByIdReturnsCorrectRide() throws RideException {
        Ride ride = rideService.bookRide("cust1", "P", "Q", 70.0);
        Ride fetched = rideService.getRideById(ride.getRideId());
        assertEquals(ride.getRideId(), fetched.getRideId());
    }

    @Test
    void testGetAllRides() throws RideException {
        rideService.bookRide("cust1", "A", "B", 100);
        rideService.bookRide("cust2", "C", "D", 120);

        Map<String, Ride> allRides = rideService.getAllRides();
        assertEquals(2, allRides.size());
    }
}