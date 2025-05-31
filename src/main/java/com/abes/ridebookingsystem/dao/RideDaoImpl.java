package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Ride;
import com.abes.ridebookingsystem.util.DataCollection;

import java.util.HashMap;
import java.util.Map;

public class RideDaoImpl implements RideDao {

	static {
		if (DataCollection.rideMap == null) {
			DataCollection.rideMap = new HashMap<>();
		}
	}

	@Override
	public void addRide(Ride ride) {
		DataCollection.rideMap.put(ride.getRideId(), ride);
	}

	@Override
	public Ride getRideById(String rideId) {
		return DataCollection.rideMap.get(rideId);
	}

	@Override
	public void updateRide(Ride ride) {
		DataCollection.rideMap.put(ride.getRideId(), ride);
	}

	@Override
	public void deleteRide(String rideId) {
		DataCollection.rideMap.remove(rideId);
	}

	@Override
	public Map<String, Ride> getAllRides() {
		return DataCollection.rideMap;
	}
}

