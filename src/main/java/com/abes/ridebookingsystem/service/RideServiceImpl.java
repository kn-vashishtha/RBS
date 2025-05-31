package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.*;
import com.abes.ridebookingsystem.dto.Customer;
import com.abes.ridebookingsystem.dto.Ride;
import com.abes.ridebookingsystem.dto.RideStatus;
import com.abes.ridebookingsystem.exception.RideException;
import com.abes.ridebookingsystem.util.DataCollection;

import java.util.*;

/**
 * Implementation of RideService to manage booking, ride statuses, and driver availability.
 */
public class RideServiceImpl {
    private final RideDao rideDao = new RideDaoImpl();
    private final CustomerDao customerDao = new CustomerDaoImpl();
    private int rideCounter = 1;

    /**
     * Books a new ride for the given customer. Ensures no other active ride exists.
     */
    public Ride bookRide(String customerId, String source, String destination, double fare) throws RideException {
        if (customerId == null || customerId.isEmpty()) {
            throw new RideException("Customer ID cannot be empty");
        }

        Customer customer = customerDao.getCustomerById(customerId);
        if (customer == null) {
            throw new RideException("Customer not found");
        }

        // Prevent booking if an active ride already exists
        for (Ride existingRide : rideDao.getAllRides().values()) {
            if (existingRide.getCustomerId().equals(customerId) &&
                (existingRide.getStatus() == RideStatus.REQUESTED || existingRide.getStatus() == RideStatus.ACCEPTED)) {
                throw new RideException("You already have an active ride (ID: " + existingRide.getRideId()
                        + ", Status: " + existingRide.getStatus() + ")");
            }
        }

        String rideId = "RIDE" + rideCounter++;
        Ride ride = new Ride(rideId, customerId, source, destination, rideId, fare);
        ride.setStatus(RideStatus.REQUESTED);

        rideDao.addRide(ride);
        return ride;
    }

    /**
     * Retrieves a list of rides that are currently available for a driver to accept
     */
    public List<Ride> getVisibleRidesForDriver(String driverId) {
        List<Ride> availableRides = new ArrayList<>();

        if (DataCollection.driverAvailability.containsKey(driverId)) {
            if (!isDriverAvailable(driverId)) {
                return availableRides; // driver is busy
            }
        }

        for (Ride ride : rideDao.getAllRides().values()) {
            if (ride.getStatus() == RideStatus.REQUESTED && !ride.hasDriverRejected(driverId)) {
                availableRides.add(ride);
            }
        }

        return availableRides;
    }

    /**
     * Allows a driver to accept a requested ride.
     */
    public boolean acceptRide(String driverId, String rideId) throws RideException {
        Ride ride = rideDao.getRideById(rideId);
        if (ride == null) {
            throw new RideException("Ride is not available");
        }

        if (ride.getStatus() != RideStatus.REQUESTED) {
            throw new RideException("Ride is not requested at the moment");
        }

        if (ride.hasDriverRejected(driverId)) {
            throw new RideException("Driver has already rejected this ride");
        }

        ride.setDriverId(driverId);
        ride.setStatus(RideStatus.ACCEPTED);
        setDriverAvailability(driverId, false); // mark driver as busy
        rideDao.updateRide(ride);

        return true;
    }

    /**
     * Allows a driver to reject a ride.
     */
    public boolean rejectRide(String driverId, String rideId) throws RideException {
        Ride ride = rideDao.getRideById(rideId);
        if (ride == null) {
            throw new RideException("Ride not found");
        }

        if (ride.getStatus() != RideStatus.REQUESTED) {
            throw new RideException("Cannot reject a ride that's not in requested state");
        }

        ride.addRejectedDriver(driverId);
        rideDao.updateRide(ride);
        return true;
    }

    /**
     * Marks a ride as completed and frees the driver.
     */
    public void completeRide(String rideId) throws RideException {
        Ride ride = rideDao.getRideById(rideId);
        if (ride == null) {
            throw new RideException("Ride not found");
        }

        ride.setStatus(RideStatus.COMPLETED);

        if (ride.getDriverId() != null) {
            setDriverAvailability(ride.getDriverId(), true); // mark driver available again
        }

        rideDao.updateRide(ride);
    }

    /**
     * Cancels a ride and frees the driver if one was assigned.
     */
    public void cancelRide(String rideId) throws RideException {
        Ride ride = rideDao.getRideById(rideId);
        if (ride == null) {
            throw new RideException("Ride not found");
        }

        ride.setStatus(RideStatus.CANCELLED);

        if (ride.getDriverId() != null) {
            setDriverAvailability(ride.getDriverId(), true);
        }

        rideDao.updateRide(ride);
    }

    /**
     * Sets the availability status of a driver.
     */
    public void setDriverAvailability(String driverId, boolean available) {
        DataCollection.driverAvailability.put(driverId, available);
    }

    /**
     * Checks if a driver is available.
     */
    public boolean isDriverAvailable(String driverId) {
        return DataCollection.driverAvailability.getOrDefault(driverId, true);
    }

    /**
     * Retrieves a ride by its ID.
     */
    public Ride getRideById(String rideId) {
        return rideDao.getRideById(rideId);
    }

    /**
     * Retrieves all rides from the data source.
     */
    public Map<String, Ride> getAllRides() {
        return rideDao.getAllRides();
    }
}
