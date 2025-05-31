package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.PaymentDao;
import com.abes.ridebookingsystem.dao.PaymentDaoImpl;
import com.abes.ridebookingsystem.dto.Payment;

import java.util.Map;
// Implementation of the PaymentService interface.
// This class handles the business logic related to payment operations.

public class PaymentServiceImpl implements PaymentService {
	private final PaymentDao paymentDao = new PaymentDaoImpl();

	// Method to add payment
	@Override
	public void addPayment(Payment payment) {
		paymentDao.addPayment(payment);
	}

	// Method to return payment using payment id
	@Override
	public Payment getPaymentById(String paymentId) {
		return paymentDao.getPaymentById(paymentId);
	}

	// Method to update payment by id
	@Override
	public void updatePayment(Payment payment) {
		paymentDao.updatePayment(payment);
	}

	// Deletes a payment using id
	@Override
	public void deletePayment(String paymentId) {
		paymentDao.deletePayment(paymentId);
	}

	// Method to return all payments stored in the system.
	@Override
	public Map<String, Payment> getAllPayments() {
		return paymentDao.getAllPayments();
	}
}
