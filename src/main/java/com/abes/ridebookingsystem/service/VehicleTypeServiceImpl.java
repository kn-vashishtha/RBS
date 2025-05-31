package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.VehicleTypeDao;
import com.abes.ridebookingsystem.dao.VehicleTypeDaoImpl;
import com.abes.ridebookingsystem.dto.VehicleType;
import com.abes.ridebookingsystem.exception.VehicleTypeException;

import java.util.Map;
// implementation of the vehicle type interface
public class VehicleTypeServiceImpl implements VehicleTypeService {
//	DAO layer object to access crud operations
    private final VehicleTypeDao vehicleTypeDao = new VehicleTypeDaoImpl();

    @Override
    public void addVehicleType(String id, VehicleType type) throws VehicleTypeException {
        vehicleTypeDao.addType(id, type);
    }

    @Override
    public VehicleType getVehicleTypeById(String id) throws VehicleTypeException {
        return vehicleTypeDao.getTypeById(id);
    }

    @Override
    public Map<String, VehicleType> getAllVehicleTypes() {
        return vehicleTypeDao.getAllTypes();
    }
}
