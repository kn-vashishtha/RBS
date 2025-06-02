package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Admin;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AdminDaoImplTest {

    private AdminDaoImpl adminDao;
    private Admin admin1;
    private Admin admin2;

    @BeforeEach
    void setUp() {
        adminDao = new AdminDaoImpl();
        
        // Clear the static map before each test
        DataCollection.adminMap.clear();
        
        // Use the correct constructor with all five parameters
        admin1 = new Admin("admin1", "Ravi ", "ravi@example.com", "password123", "superadmin");
        admin2 = new Admin("admin2", "Sunita Gupta", "sunita@example.com", "pass456", "admin");
    }

    @Test
    void testAddAdmin() {                                               // Ensures that an Admin can be successfully added to the map.
        adminDao.addAdmin(admin1);
        assertEquals(1, DataCollection.adminMap.size());
        assertTrue(DataCollection.adminMap.containsKey(admin1.getUserId()));
        assertEquals(admin1, DataCollection.adminMap.get(admin1.getUserId()));
    }

    @Test
    void testGetAdminById() {                                         // Verifies retrieval of an Admin object by its user ID.
        DataCollection.adminMap.put(admin1.getUserId(), admin1);
        Admin fetchedAdmin = adminDao.getAdminById(admin1.getUserId());
        assertNotNull(fetchedAdmin);
        assertEquals(admin1.getUserId(), fetchedAdmin.getUserId());
        assertEquals(admin1.getName(), fetchedAdmin.getName());
    }

    @Test
    void testUpdateAdmin() {                                          //Confirms that updating an Admin's details
        adminDao.addAdmin(admin1);
        
        // Modify admin1's role and update
        admin1.setRole("manager");
        adminDao.updateAdmin(admin1);
        
        Admin updatedAdmin = DataCollection.adminMap.get(admin1.getUserId());
        assertEquals("manager", updatedAdmin.getRole());
    }

    @Test
    void testDeleteAdmin() {                                      // Validates that an Admin can be correctly removed from the map
        adminDao.addAdmin(admin1);
        assertTrue(DataCollection.adminMap.containsKey(admin1.getUserId()));

        adminDao.deleteAdmin(admin1.getUserId());
        assertFalse(DataCollection.adminMap.containsKey(admin1.getUserId()));
    }

    @Test
    void testGetAllAdmins() {                                  // Checks that all stored Admin objects can be retrieved and counted
        adminDao.addAdmin(admin1);
        adminDao.addAdmin(admin2);

        Map<String, Admin> allAdmins = adminDao.getAllAdmins();
        assertEquals(2, allAdmins.size());
        assertTrue(allAdmins.containsKey(admin1.getUserId()));
        assertTrue(allAdmins.containsKey(admin2.getUserId()));
    }
}
