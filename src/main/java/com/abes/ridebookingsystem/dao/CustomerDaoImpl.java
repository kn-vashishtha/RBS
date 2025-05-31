package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Customer;
import com.abes.ridebookingsystem.util.DataCollection;

import java.util.Map;


// this class implements the CustomerDao interface
public class CustomerDaoImpl implements CustomerDao {

	// add a new customer
	@Override
	public void addCustomer(Customer customer) {
		DataCollection.customerMap.put(customer.getUserId(), customer);
	}

	// gets a customer by their user id
	@Override
	public Customer getCustomerById(String userId) {
		return DataCollection.customerMap.get(userId);
	}

	
// updates customer details
	@Override
	public void updateCustomer(Customer customer) {
		DataCollection.customerMap.put(customer.getUserId(), customer);
	}

	// removes a customer from the CustomerMap 
	@Override
	public void deleteCustomer(String userId) {
		DataCollection.customerMap.remove(userId);
	}
	
	//returns all customer stored in the customerMap
	@Override
	public Map<String, Customer> getAllCustomers() {
		return DataCollection.customerMap;
	}
}
