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

	static {
		// Initialize with hardcoded values
		initializeDefaultUsers();
		initializeVehicleTypes();
	}

	private static void initializeDefaultUsers() {
		// Hardcoded Admin
		Admin admin = new Admin("admin1", "Keshav", "keshav@ridebookingsystem.com", "Keshav@123", "Super Admin");
		adminMap.put(admin.getUserId(), admin);
		userMap.put(admin.getUserId(), admin);

		// Hardcoded Drivers
		Driver driver1 = new Driver("driver1", "Saloni", "saloni@ridebookingsystem.com", "Saloni@123", "DL1234", true);

		Driver driver2 = new Driver("driver2", "Shreya", "shreya@ridebookingsystem.com", "Shreya@123", "DL5678", true);

		Driver driver3 = new Driver("driver3", "Rachit", "rachit@ridebookingsystem.com", "Rachit@123", "DL9101112",
				true);

		driverMap.put(driver1.getUserId(), driver1);
		driverMap.put(driver2.getUserId(), driver2);
		userMap.put(driver1.getUserId(), driver1);
		userMap.put(driver2.getUserId(), driver2);
		driverMap.put(driver3.getUserId(), driver3);
		userMap.put(driver3.getUserId(), driver3);

		// Hardcoded Customers
		Customer customer1 = new Customer("cust1", "Ishita", "ishita@gmail.com", "Ishita@123", "+919876543210");

		Customer customer2 = new Customer("cust2", "Anamika", "anamika@gmail.com", "Anamika@123", "+918765432109");

		Customer customer3 = new Customer("cust3", "Vidushi", "vidushi@gmail.com", "Vidushi@123", "+917654321098");

		customerMap.put(customer1.getUserId(), customer1);
		customerMap.put(customer2.getUserId(), customer2);
		userMap.put(customer1.getUserId(), customer1);
		userMap.put(customer2.getUserId(), customer2);
		customerMap.put(customer3.getUserId(), customer3);
		userMap.put(customer3.getUserId(), customer3);
	}

	private static void initializeVehicleTypes() {
		// Add all enum values to the vehicleTypeMap
		for (VehicleType type : VehicleType.values()) {
			vehicleTypeMap.put(type.name(), type);
		}
	}

	// Helper methods remain the same
	public static boolean isDefaultUser(String userId) {
		return adminMap.containsKey(userId) || driverMap.containsKey(userId) || customerMap.containsKey(userId);
	}

	public static Admin getDefaultAdmin() {
		return adminMap.get("admin1");
	}

	public static Driver getDefaultDriver(String driverId) {
		return driverMap.get(driverId);
	}

}
