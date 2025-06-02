package com.abes.ridebookingsystem.util;

import com.abes.ridebookingsystem.dto.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DataCollectionTest {

    @BeforeEach
    void clearMaps() {
        // Clear all maps before each test to start again
        DataCollection.userMap.clear();
        DataCollection.adminMap.clear();
        DataCollection.driverMap.clear();
        DataCollection.customerMap.clear();
        DataCollection.vehicleTypeMap.clear();

        // Call the private methods to add default users and vehicle types again
        try {
            var adminMethod = DataCollection.class.getDeclaredMethod("initializeDefaultUsers");
            adminMethod.setAccessible(true);
            adminMethod.invoke(null);

            var vehicleMethod = DataCollection.class.getDeclaredMethod("initializeVehicleTypes");
            vehicleMethod.setAccessible(true);
            vehicleMethod.invoke(null);
        } catch (Exception e) {
            fail("Failed to call init methods: " + e.getMessage());
        }
    }

    @Test
    void testDefaultAdminPresent() {
        // Check if the default admin is added properly
        Admin admin = DataCollection.getDefaultAdmin();
        assertNotNull(admin);
        assertEquals("admin1", admin.getUserId());
        assertEquals("Keshav", admin.getName());
    }

    @Test
    void testDefaultDriversPresent() {
        // Check if default drivers are added correctly
        Driver driver1 = DataCollection.getDefaultDriver("driver1");
        assertNotNull(driver1);
        assertEquals("Saloni", driver1.getName());

        Driver driver2 = DataCollection.getDefaultDriver("driver2");
        assertNotNull(driver2);
        assertEquals("Shreya", driver2.getName());

        Driver driver3 = DataCollection.getDefaultDriver("driver3");
        assertNotNull(driver3);
        assertEquals("Rachit", driver3.getName());
    }

    @Test
    void testDefaultCustomersPresent() {
        // Check if default customers are present in the map
        assertTrue(DataCollection.customerMap.containsKey("cust1"));
        assertTrue(DataCollection.customerMap.containsKey("cust2"));
        assertTrue(DataCollection.customerMap.containsKey("cust3"));

        Customer customer1 = DataCollection.customerMap.get("cust1");
        assertEquals("Ishita", customer1.getName());

        Customer customer3 = DataCollection.customerMap.get("cust3");
        assertEquals("Vidushi", customer3.getName());
    }

    @Test
    void testVehicleTypesInitialized() {
        // Check if all vehicle types from enum are added to the map
        assertEquals(VehicleType.values().length, DataCollection.vehicleTypeMap.size());

        for (VehicleType type : VehicleType.values()) {
            assertTrue(DataCollection.vehicleTypeMap.containsKey(type.name()));
            assertEquals(type, DataCollection.vehicleTypeMap.get(type.name()));
        }
    }

    @Test
    void testIsDefaultUser() {
        // Test if method correctly finds default users
        assertTrue(DataCollection.isDefaultUser("admin1"));
        assertTrue(DataCollection.isDefaultUser("driver1"));
        assertTrue(DataCollection.isDefaultUser("cust1"));

        // Test for user that does not exist
        assertFalse(DataCollection.isDefaultUser("unknownUser"));
    }
}
