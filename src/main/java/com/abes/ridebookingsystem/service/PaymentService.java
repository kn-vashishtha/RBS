package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Payment;

import java.util.Map;

public interface PaymentService {
	// PaymentService interface defines the contract for managing payment operations.

	void addPayment(Payment payment); //abstract mwthod to add payment

	Payment getPaymentById(String paymentId); // abstract method to get payment using id

	void updatePayment(Payment payment); // abstract method to update payment

	void deletePayment(String paymentId); // abstract method to delete payment

	Map<String, Payment> getAllPayments(); // abstract method to return all the payment 
}
