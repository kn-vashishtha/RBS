package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Ride;
import java.util.Map;

/**
 * @param RideDao interface defines the contract for CRUD operations
 * related to Ride objects in the Ride Booking System.
 */
public interface RideDao {

    /**
     * Adds a new Ride to the system.
     */
    void addRide(Ride ride);

    /**
     * Retrieves a Ride by its unique ID.
     */
    Ride getRideById(String rideId);
    void updateRide(Ride ride);
    void deleteRide(String rideId);

    /**
     * Retrieves all Rides in the system.
     *
     * @return a Map containing all Ride objects with their IDs as keys
     */
    Map<String, Ride> getAllRides();
}
