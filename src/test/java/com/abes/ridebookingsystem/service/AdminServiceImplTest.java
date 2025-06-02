package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.AdminDao;
import com.abes.ridebookingsystem.dao.AdminDaoImpl;
import com.abes.ridebookingsystem.dto.Admin;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceImplTest {

    private AdminService adminService;

    @BeforeEach
    void setUp() {
        adminService = new AdminServiceImpl();
        Map<String, Admin> allAdmins = adminService.getAllAdmins();
        allAdmins.clear();                                         
    }

    @Test
    void testAddAndGetAdmin() {                                    // Verifies retrieval of an Admin object by its user ID
        Admin admin = new Admin("admin1", "Keshav", "keshav@example.com", "password123", "Super Admin");

        adminService.addAdmin(admin);
        Admin fetchedAdmin = adminService.getAdminById("admin1");

        assertNotNull(fetchedAdmin);
        assertEquals("Keshav", fetchedAdmin.getName());
        assertEquals("Super Admin", fetchedAdmin.getRole());
    }

    @Test
    void testUpdateAdmin() {                                      //Confirms that updating an Admin's details
        Admin admin = new Admin("admin2", "Rohan", "rohan@example.com", "pass123", "Admin");
        adminService.addAdmin(admin);

        admin.setName("Rohan Updated");
        admin.setRole("Super Admin");
        adminService.updateAdmin(admin);

        Admin updatedAdmin = adminService.getAdminById("admin2");
        assertEquals("Rohan Updated", updatedAdmin.getName());
        assertEquals("Super Admin", updatedAdmin.getRole());
    }

    @Test
    void testDeleteAdmin() {                                         // Validates that an Admin can be correctly removed from the map
        Admin admin = new Admin("admin3", "Sita", "sita@example.com", "pass456", "Admin");
        adminService.addAdmin(admin);

        adminService.deleteAdmin("admin3");
        Admin deletedAdmin = adminService.getAdminById("admin3");
        assertNull(deletedAdmin);
    }

    @Test
    void testGetAllAdmins() {                                                       // Checks that all stored Admin objects
        Admin admin1 = new Admin("admin4", "Rahul", "rahul@example.com", "pass789", "Admin");
        Admin admin2 = new Admin("admin5", "Neha", "neha@example.com", "pass101", "Super Admin");

        adminService.addAdmin(admin1);
        adminService.addAdmin(admin2);

        Map<String, Admin> admins = adminService.getAllAdmins();
        assertEquals(2, admins.size());
        assertTrue(admins.containsKey("admin4"));
        assertTrue(admins.containsKey("admin5"));
    }
}

