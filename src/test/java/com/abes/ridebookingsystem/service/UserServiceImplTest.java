package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.UserDaoImpl;
import com.abes.ridebookingsystem.dto.Customer;
import com.abes.ridebookingsystem.dto.User;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;

 // Runs before each test case to set up fresh space
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        DataCollection.userMap.clear();
        DataCollection.customerMap.clear();
    }
    
    // Test if a user can be registered as a Customer correctly
    @Test
    void testRegisterUserAsCustomer() {
        Customer customer = new Customer("cust1", "Amit Verma", "amit@example.com", "pass123", "9876543210");
        userService.registerUser(customer);

        User stored = userService.getUserById("cust1");
        assertNotNull(stored);
        assertTrue(stored instanceof Customer);
        assertEquals("Amit Verma", stored.getName());

        // Also check Customer-specific map
        assertNotNull(DataCollection.customerMap.get("cust1"));
    }

    // Test successful login with valid user ID and password
    @Test
    void testLoginUserSuccess() {
        Customer customer = new Customer("cust2", "Neha Jain", "neha@example.com", "nehaPass", "9999988888");
        userService.registerUser(customer);

        User loggedIn = userService.loginUser("cust2", "nehaPass");
        assertNotNull(loggedIn);
        assertEquals("Neha Jain", loggedIn.getName());
    }
    
    // Test login failure when user ID is invalid (user not found)
    @Test
    void testLoginUserInvalidId() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.loginUser("invalidUser", "somePass"));
        assertEquals("User not found", ex.getMessage()); // Removed the period
    }

    // Test login failure when password is incorrect
    @Test
    void testLoginUserInvalidPassword() {
        Customer customer = new Customer("cust3", "Ravi Kumar", "ravi@example.com", "ravi123", "8888777766");
        userService.registerUser(customer);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.loginUser("cust3", "wrongPass"));
        assertEquals("Invalid password.", ex.getMessage());
    }

 // Test for getting a user by their ID
    @Test
    void testGetUserById() {
        Customer customer = new Customer("cust4", "Sneha Kapoor", "sneha@example.com", "sneha456", "7777666655");
        userService.registerUser(customer);

        User found = userService.getUserById("cust4");
        assertNotNull(found);
        assertEquals("Sneha Kapoor", found.getName());
    }

    // Test fetching all registered users
    @Test
    void testGetAllUsers() {
        userService.registerUser(new Customer("cust1", "Arjun", "arjun@example.com", "arjun123", "9111222233"));
        userService.registerUser(new Customer("cust2", "Simran", "simran@example.com", "simran123", "9111222244"));

        Map<String, User> allUsers = userService.getAllUsers();
        assertEquals(2, allUsers.size());
    }
}