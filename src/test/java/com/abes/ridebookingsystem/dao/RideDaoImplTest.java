package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Ride;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RideDaoImplTest {

    private RideDao rideDao;

    @BeforeEach
    void setUp() {
        rideDao = new RideDaoImpl();
        DataCollection.rideMap.clear(); // Clean map before each test
    }

    @Test
    void testAddRide() {
        Ride ride = new Ride("R001", "Aman", null, "Delhi", "Agra", 0.0);
        rideDao.addRide(ride);

        Ride result = rideDao.getRideById("R001");
        assertNotNull(result);
        assertEquals("Aman", result.getCustomerId());
    }

    @Test
    void testGetRideById() {
        Ride ride = new Ride("R002", "Riya", null, "Lucknow", "Kanpur", 0.0);
        DataCollection.rideMap.put("R002", ride);

        Ride result = rideDao.getRideById("R002");
        assertNotNull(result);
        assertEquals("Riya", result.getCustomerId());
    }

    @Test
    void testUpdateRide() {
        Ride ride = new Ride("R003", "Mohit", null, "Gurgaon", "Noida", 0.0);
        rideDao.addRide(ride);

        Ride updatedRide = new Ride("R003", "Mohit", null, "Faridabad", "Ghaziabad", 0.0);
        rideDao.updateRide(updatedRide);

        Ride result = rideDao.getRideById("R003");
        assertEquals("Faridabad", result.getSource());
    }

    @Test
    void testDeleteRide() {
        Ride ride = new Ride("R004", "Neha", null, "Mumbai", "Pune", 0.0);
        rideDao.addRide(ride);

        rideDao.deleteRide("R004");
        assertNull(rideDao.getRideById("R004"));
    }

    @Test
    void testGetAllRides() {
        Ride ride1 = new Ride("R005", "Siddharth", null, "Chennai", "Bangalore", 0.0);
        Ride ride2 = new Ride("R006", "Pooja", null, "Kolkata", "Ranchi", 0.0);

        rideDao.addRide(ride1);
        rideDao.addRide(ride2);

        Map<String, Ride> allRides = rideDao.getAllRides();
        assertEquals(2, allRides.size());
        assertTrue(allRides.containsKey("R005"));
        assertTrue(allRides.containsKey("R006"));
    }
}
