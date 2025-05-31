package com.abes.ridebookingsystem.util;

import com.abes.ridebookingsystem.dto.*;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

public class DataCollection {

	// Just a simple in-memory map to store Payment objects.
	// Mainly used for testing or when we don't have a real database set up.

	public static final Map<String, User> userMap = new HashMap<>();
	public static final Map<String, VehicleType> vehicleTypeMap = new HashMap<>();
	public static Map<String, Ride> rideMap = new HashMap<>();
	public static final Map<String, Payment> paymentMap = new HashMap<>();
	// We're using the paymentId as the key to quickly find payments.

}
