package com.abes.ridebookingsystem.util;

import com.abes.ridebookingsystem.dto.*;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

public class DataCollection {

	public static final Map<String, User> userMap = new HashMap<>();
	public static final Map<String, VehicleType> vehicleTypeMap = new HashMap<>();
	public static Map<String, Ride> rideMap = new HashMap<>();
}
