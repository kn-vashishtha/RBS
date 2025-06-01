package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.VehicleType;
import com.abes.ridebookingsystem.exception.VehicleTypeException;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTypeDaoImplTest {

    private VehicleTypeDao vehicleTypeDao;
// initializes the DAO implementation and clears in memory map before each test case
    @BeforeEach
    void setUp() {
        vehicleTypeDao = new VehicleTypeDaoImpl();
        DataCollection.vehicleTypeMap.clear(); // Clear before each test
    }
//  Successfully add a vehicle type and verify it exists in the map.
    @Test
    void testAddType() throws VehicleTypeException {
        vehicleTypeDao.addType("SEDAN", VehicleType.SEDAN);
        assertEquals(VehicleType.SEDAN, DataCollection.vehicleTypeMap.get("SEDAN"));
    }
//  Attempting to add a duplicate vehicle type should throw an exception. 
    @Test
    void testAddDuplicateTypeThrowsException() throws VehicleTypeException {
        vehicleTypeDao.addType("BIKE", VehicleType.BIKE);

        VehicleTypeException exception = assertThrows(
            VehicleTypeException.class,
            () -> vehicleTypeDao.addType("BIKE", VehicleType.BIKE)
        );

        assertEquals("Vehicle type already exists.", exception.getMessage());
    }
//  Retrieve a vehicle type by its ID (key) after adding it.
    @Test
    void testGetTypeById() throws VehicleTypeException {
        vehicleTypeDao.addType("PREMIUM", VehicleType.PREMIUM);
        VehicleType type = vehicleTypeDao.getTypeById("PREMIUM");
        assertEquals(VehicleType.PREMIUM, type);
    }
//  Attempt to retrieve a vehicle type with an unknown ID should throw an exception
    @Test
    void testGetTypeByIdThrowsException() {
        VehicleTypeException exception = assertThrows(
            VehicleTypeException.class,
            () -> vehicleTypeDao.getTypeById("UNKNOWN")
        );

        assertEquals("Vehicle type not found.", exception.getMessage());
    }
//  Add multiple vehicle types and verify that they all exist in the returned map.
    @Test
    void testGetAllTypes() throws VehicleTypeException {
        vehicleTypeDao.addType("AUTO", VehicleType.AUTO);
        vehicleTypeDao.addType("CABXL", VehicleType.CABXL);

        Map<String, VehicleType> allTypes = vehicleTypeDao.getAllTypes();
        assertEquals(2, allTypes.size());
        assertTrue(allTypes.containsKey("AUTO"));
        assertTrue(allTypes.containsKey("CABXL"));
    }
}
