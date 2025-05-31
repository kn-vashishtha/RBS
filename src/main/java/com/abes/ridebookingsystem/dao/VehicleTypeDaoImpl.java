package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.VehicleType;
import com.abes.ridebookingsystem.exception.VehicleTypeException;
import com.abes.ridebookingsystem.util.DataCollection;

import java.util.Map;
// implementation of vehicle type dao interface
public class VehicleTypeDaoImpl implements VehicleTypeDao {
	// Reference to DAO layer for vehicle type operations
	private final Map<String, VehicleType> types = DataCollection.vehicleTypeMap;

	// adding type of vehicle types
	@Override
	public void addType(String id, VehicleType type) throws VehicleTypeException {
		if (types.containsKey(id)) {
			throw new VehicleTypeException("Vehicle type already exists.");
		}
		types.put(id, type);
	}
	// retrieve vehicle type by its id
	@Override
	public VehicleType getTypeById(String id) throws VehicleTypeException {
		if (!types.containsKey(id)) {
			throw new VehicleTypeException("Vehicle type not found.");
		}
		return types.get(id);
	}
	//	get all types of vehicles
	@Override
	public Map<String, VehicleType> getAllTypes() {
		return types;
	}
}
