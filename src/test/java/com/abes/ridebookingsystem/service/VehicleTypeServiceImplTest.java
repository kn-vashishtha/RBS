package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.VehicleType;
import com.abes.ridebookingsystem.dto.WheelerType;
import com.abes.ridebookingsystem.exception.VehicleTypeException;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTypeServiceImplTest {

    private VehicleTypeServiceImpl vehicleTypeService;
//  Sets up the service and clears the in-memory vehicle type map before each test
    @BeforeEach
    void setUp() {
        vehicleTypeService = new VehicleTypeServiceImpl();
        DataCollection.vehicleTypeMap.clear(); // Reset in-memory store
    }
//  Successfully add a vehicle type and retrieve it.
    @Test
    void testAddVehicleTypeSuccess() throws VehicleTypeException {
        VehicleType type = VehicleType.SEDAN;  // Use enum constant directly
        vehicleTypeService.addVehicleType("SEDAN", type);

        VehicleType retrieved = vehicleTypeService.getVehicleTypeById("SEDAN");
        assertNotNull(retrieved);
        assertEquals(WheelerType.FOUR_WHEELER, retrieved.getWheelerType());
        assertEquals(50, retrieved.getBaseFare());
        assertEquals(10, retrieved.getPerKmRate());
    }
//  Attempt to retrieve a non-existing vehicle type should throw an exception.
    @Test
    void testGetVehicleTypeById_NotFound() {
        VehicleTypeException ex = assertThrows(VehicleTypeException.class, () ->
                vehicleTypeService.getVehicleTypeById("INVALID"));
        assertTrue(ex.getMessage().toLowerCase().contains("not found"));
    }

//  Add multiple vehicle types and verify that all of them can be retrieved.
    @Test
    void testGetAllVehicleTypes() throws VehicleTypeException {
        vehicleTypeService.addVehicleType("PREMIUM", VehicleType.PREMIUM);
        vehicleTypeService.addVehicleType("BIKE", VehicleType.BIKE);

        Map<String, VehicleType> allTypes = vehicleTypeService.getAllVehicleTypes();
        assertEquals(2, allTypes.size());
        assertTrue(allTypes.containsKey("PREMIUM"));
        assertTrue(allTypes.containsKey("BIKE"));
    }

}