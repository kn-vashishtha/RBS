package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Payment;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDaoImplTest {

	private PaymentDao paymentDao;

	@BeforeEach // This method runs before each test case
	void setUp() {
		paymentDao = new PaymentDaoImpl();// We initialize a new PaymentDaoImpl object
		DataCollection.paymentMap.clear();// Clear payment map before each test
	}

	@Test
	void testAddPayment() {
		Payment payment = new Payment("P1", "R1", 500.0, "Credit Card");
		paymentDao.addPayment(payment);// Add the payment
		Payment retrieved = paymentDao.getPaymentById("P1");// Retrieve it back to check if it was added correctly
		assertNotNull(retrieved); // Check that the payment is not null
		assertEquals(500.0, retrieved.getAmount()); // Checks for correct values
		assertEquals("Credit Card", retrieved.getPaymentMethod()); // Checks for Correct payment method
	}

	@Test
	void testGetPaymentById() {
		Payment payment = new Payment("P2", "R2", 300.0, "Cash");
		DataCollection.paymentMap.put("P2", payment); // Directly put a payment into the paymentMap
		Payment retrieved = paymentDao.getPaymentById("P2");
		assertNotNull(retrieved);
		assertEquals("Cash", retrieved.getPaymentMethod());
	}

	@Test
	void testUpdatePayment() {
		Payment payment = new Payment("P3", "R3", 200.0, "Debit Card");

		paymentDao.addPayment(payment);// Add the payment

		Payment updatedPayment = new Payment("P3", "R3", 250.0, "Debit Card");
		paymentDao.updatePayment(updatedPayment);// Update the payment

		Payment result = paymentDao.getPaymentById("P3");// Fetch the payment again and check if the updation work
		assertEquals(250.0, result.getAmount());
	}

	@Test
	void testDeletePayment() {
		Payment payment = new Payment("P4", "R4", 150.0, "UPI");

		paymentDao.addPayment(payment);// Add the payment

		paymentDao.deletePayment("P4");// Delete the payment

		assertNull(paymentDao.getPaymentById("P4")); // Fetching the deleted payment it should be null now
	}

	@Test
	void testGetAllPayments() {
		Payment payment1 = new Payment("P5", "R5", 100.0, "Net Banking");
		Payment payment2 = new Payment("P6", "R6", 400.0, "Wallet");

		// Add two different payments
		paymentDao.addPayment(payment1);
		paymentDao.addPayment(payment2);

		// Get all payments from DAO
		Map<String, Payment> payments = paymentDao.getAllPayments();

		// Check that the map contains exactly 2 entries and both are present
		assertEquals(2, payments.size());
		assertTrue(payments.containsKey("P5"));
		assertTrue(payments.containsKey("P6"));
	}
}
