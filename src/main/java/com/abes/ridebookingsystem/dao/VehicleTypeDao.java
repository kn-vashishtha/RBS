package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.VehicleType;
import com.abes.ridebookingsystem.exception.VehicleTypeException;

import java.util.Map;

public interface VehicleTypeDao {
	void addType(String id, VehicleType type) throws VehicleTypeException;

	VehicleType getTypeById(String id) throws VehicleTypeException;

	Map<String, VehicleType> getAllTypes();
}
