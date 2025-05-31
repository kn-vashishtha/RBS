package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Payment;
import com.abes.ridebookingsystem.util.DataCollection;

import java.util.Map;

public class PaymentDaoImpl implements PaymentDao {
//implementing class for the interface 	

	// Adds a new payment

	@Override
	public void addPayment(Payment payment) {
		DataCollection.paymentMap.put(payment.getPaymentId(), payment);
		// Add a payment object into paymentMap using id as key
	}

	// Retrieves a payment by its unique payment ID

	@Override
	public Payment getPaymentById(String paymentId) {
		return DataCollection.paymentMap.get(paymentId);
		// looks in the paymentMap for the given paymentId and returns the corresponding
		// payment object
	}

	// Updates payment

	@Override
	public void updatePayment(Payment payment) {
		DataCollection.paymentMap.put(payment.getPaymentId(), payment);
		// puts the payment into the map with the same key i.e paymentId which replaces
		// the old entry
	}

	// delete payment

	@Override
	public void deletePayment(String paymentId) {
		DataCollection.paymentMap.remove(paymentId);
		// removes the entry from the paymentMap using the payment ID as the key
	}

	// Returns all payment
	
	@Override
	public Map<String, Payment> getAllPayments() {
		return DataCollection.paymentMap;
		// returns the whole paymentMap
	}
}
