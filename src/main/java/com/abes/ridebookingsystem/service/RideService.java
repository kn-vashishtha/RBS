package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Ride;
import com.abes.ridebookingsystem.exception.RideException;

import java.util.List;
import java.util.Map;

/**
 * RideService defines the operations for managing ride bookings,
 * including booking, accepting, rejecting, and completing rides,
 * as well as managing driver availability.
 */
public interface RideService {

    /**
     * Books a new ride for the given customer with specified source, destination, and fare.
     */
    Ride bookRide(String customerId, String source, String destination, double fare) throws RideException;

    /**
     * Returns a list of rides that are visible and available for the specified driver.
     */
    List<Ride> getVisibleRidesForDriver(String driverId);

    /**
     * Allows a driver to accept a specific ride.
     */
    boolean acceptRide(String driverId, String rideId) throws RideException;

    /**
     * Allows a driver to reject a specific ride.
     */
    boolean rejectRide(String driverId, String rideId) throws RideException;

    /**
     * Marks a ride as completed.
     */
    void completeRide(String rideId) throws RideException;

    /**
     * Cancels a ride.
     */
    void cancelRide(String rideId) throws RideException;

    /**
     * Sets the availability status of a driver.
     */
    void setDriverAvailability(String driverId, boolean available);

    /**
     * Checks whether a driver is currently available.
     */
    boolean isDriverAvailable(String driverId);

    /**
     * Retrieves the ride object by its ID
     */
    Ride getRideById(String rideId);

    /**
     * Retrieves a map of all rides, where the key is the ride ID.
     */
    Map<String, Ride> getAllRides();
}
