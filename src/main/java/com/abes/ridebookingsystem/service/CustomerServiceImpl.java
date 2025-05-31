package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.CustomerDao;
import com.abes.ridebookingsystem.dao.CustomerDaoImpl;
import com.abes.ridebookingsystem.dto.Customer;

import java.util.Map;

// This class provides the actual implementation for customer-related operations
public class CustomerServiceImpl implements CustomerService {

    // Create an object of CustomerDao to handle data storage and retrieval
    private final CustomerDao customerDao = new CustomerDaoImpl();

    // Add a new customer using the DAO layer
    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    // Get customer details by user ID using the DAO layer
    @Override
    public Customer getCustomerById(String userId) {
        return customerDao.getCustomerById(userId);
    }

     // Update customer information using the DAO layer
    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    // Delete a customer by user ID using the DAO layer
    @Override
    public void deleteCustomer(String userId) {
        customerDao.deleteCustomer(userId);
    }

     // Get a list of all customers from the DAO layer
    @Override
    public Map<String, Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }
}
