package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.VehicleType;
import com.abes.ridebookingsystem.exception.VehicleTypeException;

import java.util.Map;
// providing abstract methods for adding and getting vehicle types
public interface VehicleTypeService {
    void addVehicleType(String id, VehicleType type) throws VehicleTypeException;
    VehicleType getVehicleTypeById(String id) throws VehicleTypeException;
    Map<String, VehicleType> getAllVehicleTypes();
}

