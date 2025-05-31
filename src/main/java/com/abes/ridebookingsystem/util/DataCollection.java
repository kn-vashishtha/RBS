package com.abes.ridebookingsystem.util;

import com.abes.ridebookingsystem.dto.*;

import java.util.HashMap;
import java.util.Map;

public class DataCollection {

	// Just a simple in-memory map to store Payment objects.
	// Mainly used for testing or when we don't have a real database set up.

	public static final Map<String, User> userMap = new HashMap<>();
	// Stores all users using their userId as the key
	
	public static final Map<String, Admin> adminMap = new HashMap<>();
	// Stores admin data with userId as the key and admin object as the value
	
	public static final Map<String, Customer> customerMap = new HashMap<>();
	// Stores all customers using their customerId as the key
	
	public static final Map<String, VehicleType> vehicleTypeMap = new HashMap<>();
	// Stores all types of vehicles (like car, bike, etc.)

	public static Map<String, Ride> rideMap = new HashMap<>();
	// Stores all rides using their rideId as the key
	
	public static final Map<String, Payment> paymentMap = new HashMap<>();
	// We're using the paymentId as the key to quickly find payments.
	
	public static final Map<String, Driver> driverMap = new HashMap<>();
	// Stores all drivers using their driverId as the key
	
	public static final Map<String, Boolean> driverAvailability = new HashMap<>();
	// Keeps track of whether a driver is available or not

}
