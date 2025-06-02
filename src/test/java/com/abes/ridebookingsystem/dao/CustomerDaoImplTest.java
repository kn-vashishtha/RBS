package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Customer;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoImplTest {

    private CustomerDao customerDao;
    
    
 // This method runs before each test method.
    @BeforeEach
    void setUp() {
        customerDao = new CustomerDaoImpl();
        DataCollection.customerMap.clear(); // Reset before each test
    }

 // Test to check if a new customer can be added successfully
    @Test
    void testAddCustomer() {
        Customer customer = new Customer("C001", "Rahul Kumar", "rahul@example.com", "pass123", "9876543210");
        customerDao.addCustomer(customer);

     // Check if the customer was added to the map
        Customer saved = DataCollection.customerMap.get("C001");
        assertNotNull(saved);
        assertEquals("Rahul Kumar", saved.getName());
        assertEquals("9876543210", saved.getPhoneNumber());
    }

 // Test to check if a customer can be fetched by ID
    @Test
    void testGetCustomerById() {
    	// First, add a customer manually
        Customer customer = new Customer("C002", "Sneha", "sneha@example.com", "pwd321", "9123456789");
        DataCollection.customerMap.put("C002", customer);

     // Then fetch using DAO method
        Customer retrieved = customerDao.getCustomerById("C002");
        assertNotNull(retrieved);
        assertEquals("Sneha", retrieved.getName());
        assertEquals("9123456789", retrieved.getPhoneNumber());
    }

 // Test to check if a customer's information can be updated
    @Test
    void testUpdateCustomer() {
    	// Add an original customer
        Customer original = new Customer("C003", "Amit Sharma", "amit@example.com", "abc123", "9000000000");
        DataCollection.customerMap.put("C003", original);

        // Create an updated version of the customer
        Customer updated = new Customer("C003", "Amit Sharma", "amit_new@example.com", "xyz789", "9000001111");
        customerDao.updateCustomer(updated);

     // Get the updated customer and verify changes
        Customer result = DataCollection.customerMap.get("C003");
        assertEquals("Amit Sharma", result.getName());
        assertEquals("amit_new@example.com", result.getEmail());
        assertEquals("9000001111", result.getPhoneNumber());
    }
 // Test to check if a customer can be deleted
    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer("C004", "Rina", "rina@example.com", "pass", "8001112222");
        DataCollection.customerMap.put("C004", customer);
        // Delete the customer
        customerDao.deleteCustomer("C004");
     // Check that the customer is removed
        assertNull(DataCollection.customerMap.get("C004"));
    }
    
 // Test to check if all customers can be retrieved
    @Test
    void testGetAllCustomers() {
    	/// Add two customers
        Customer c1 = new Customer("C005", "Deepak", "deepak@example.com", "p1", "7001234567");
        Customer c2 = new Customer("C006", "Meena", "meena@example.com", "p2", "7007654321");

        DataCollection.customerMap.put("C005", c1);
        DataCollection.customerMap.put("C006", c2);

        // Retrieve all customers
        Map<String, Customer> allCustomers = customerDao.getAllCustomers();
     // Check if the map has both customers
        assertEquals(2, allCustomers.size());
        assertTrue(allCustomers.containsKey("C005"));
        assertTrue(allCustomers.containsKey("C006"));
    }
}