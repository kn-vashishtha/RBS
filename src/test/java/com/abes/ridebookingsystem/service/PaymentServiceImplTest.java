package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {

	private PaymentService paymentService;

	@BeforeEach
	// This method runs before each test
	void setUp() {
		paymentService = new PaymentServiceImpl(); // We create a new object of PaymentServiceImpl
		paymentService.getAllPayments().clear(); // Clear previous data before each test
	}

	@Test
	void testAddAndGetPayment() {
		Payment payment = new Payment("p1", "r1", 500.0, "UPI");
		paymentService.addPayment(payment); // Adding the payment to the service
		Payment result = paymentService.getPaymentById("p1");
		// Trying to fetch the same payment by its ID

		// Checking that the payment was successfully added and fetched
		assertNotNull(result); // Check that payment is not null
		assertEquals("r1", result.getRideId()); // Check that payment is not null
		assertEquals(500.0, result.getAmount()); // Ride ID should match
		assertEquals("UPI", result.getPaymentMethod()); // Payment method should match
	}

	@Test
	void testUpdatePayment() {
		Payment payment = new Payment("p2", "r2", 300.0, "Cash");
		paymentService.addPayment(payment); // Add the payment
		payment.setAmount(350.0);
		payment.setPaymentMethod("Card");
		paymentService.updatePayment(payment); // Update amount and method
		Payment updated = paymentService.getPaymentById("p2");

		// Checking if the changes were saved correctly
		assertEquals(350.0, updated.getAmount());
		assertEquals("Card", updated.getPaymentMethod());
	}

	@Test
	void testDeletePayment() {
		Payment payment = new Payment("p3", "r3", 150.0, "Card");
		paymentService.addPayment(payment); // Add the payment
		paymentService.deletePayment("p3"); // Delete the payment
		assertNull(paymentService.getPaymentById("p3")); // Now the value should be null
	}

	@Test
	void testGetAllPayments() {
		// Add 2 the payments
		paymentService.addPayment(new Payment("p4", "r4", 200.0, "Cash"));
		paymentService.addPayment(new Payment("p5", "r5", 100.0, "UPI"));

		// Getting all payments from the service
		Map<String, Payment> allPayments = paymentService.getAllPayments();

		// Checking if both payments were added
		assertEquals(2, allPayments.size()); // There should be 2 payments
		assertTrue(allPayments.containsKey("p4")); // Checking if it exists
		assertTrue(allPayments.containsKey("p5")); // Checking if it exist
	}
}
