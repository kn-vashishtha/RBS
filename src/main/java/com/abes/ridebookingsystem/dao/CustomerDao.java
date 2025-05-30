package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Customer;

import java.util.Map;


// interface related to customer operations

public interface CustomerDao {
	// add a customer
	void addCustomer(Customer customer);
	
	// retrieve a customer based on their id
	Customer getCustomerById(String userId);
	
	// updates the information of customer
	void updateCustomer(Customer customer);
	
	//deletes a customer
	void deleteCustomer(String userId);
	
	// retrieves all customers
	Map<String, Customer> getAllCustomers();
}
