package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Payment;

import java.util.Map;

public interface PaymentDao {
	// Dao interface defines operations that can be performed on Payment
	void addPayment(Payment payment); // adds payment

	Payment getPaymentById(String paymentId); // return the payment by its id

	void updatePayment(Payment payment); // update the existing payment

	void deletePayment(String paymentId); // delete the existing payment

	Map<String, Payment> getAllPayments(); // return stored payment
}
