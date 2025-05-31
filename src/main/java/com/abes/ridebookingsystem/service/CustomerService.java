package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Customer;
import java.util.Map;

// This interface defines what operations can be done for a customer
public interface CustomerService {

    // Add a new customer to the system
    void addCustomer(Customer customer);

    // Get a customer's details using their user ID
    Customer getCustomerById(String userId);

    // Update the details of an existing customer
    void updateCustomer(Customer customer);

    // Remove a customer from the system using their user ID
    void deleteCustomer(String userId);

    // Get a list of all customers
    Map<String, Customer> getAllCustomers();
}
