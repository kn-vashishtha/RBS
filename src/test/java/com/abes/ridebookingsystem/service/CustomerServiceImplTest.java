package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Customer;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl();

        // Clear existing customers (if allowed by your DAO map structure)
        Map<String, Customer> allCustomers = customerService.getAllCustomers();
        allCustomers.clear(); // WARNING: Only if your map is mutable!
    }

    @Test
    void testAddAndGetCustomer() {
        Customer customer = new Customer("cust1", "Vidushi", "vidushi@example.com", "Vidushi@123", "+911234567890");

        customerService.addCustomer(customer);
        Customer fetchedCustomer = customerService.getCustomerById("cust1");

        assertNotNull(fetchedCustomer);
        assertEquals("Vidushi", fetchedCustomer.getName());
        assertEquals("+911234567890", fetchedCustomer.getPhoneNumber());
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer("cust2", "Anamika", "ana@example.com", "Ana@123", "+919876543210");
        customerService.addCustomer(customer);

        // Update fields
        customer.setName("Anamika Updated");
        customer.setPhoneNumber("+911111111111");
        customerService.updateCustomer(customer);

        Customer updated = customerService.getCustomerById("cust2");
        assertEquals("Anamika Updated", updated.getName());
        assertEquals("+911111111111", updated.getPhoneNumber());
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer("cust3", "Ishita", "ishi@example.com", "Ishi@123", "+918888888888");
        customerService.addCustomer(customer);

        customerService.deleteCustomer("cust3");
        Customer deleted = customerService.getCustomerById("cust3");
        assertNull(deleted);
    }

    @Test
    void testGetAllCustomers() {
        Customer customer1 = new Customer("cust4", "Riya", "riya@example.com", "Riya@123", "+911234567891");
        Customer customer2 = new Customer("cust5", "Tina", "tina@example.com", "Tina@123", "+911234567892");

        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        Map<String, Customer> all = customerService.getAllCustomers();
        assertEquals(2, all.size());
        assertTrue(all.containsKey("cust4"));
        assertTrue(all.containsKey("cust5"));
    }
}
