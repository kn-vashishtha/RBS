package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentServiceImpl();
        // Clear previous data before each test
        paymentService.getAllPayments().clear();
    }

    @Test
    void testAddAndGetPayment() {
        Payment payment = new Payment("p1", "r1", 500.0, "UPI");
        paymentService.addPayment(payment);

        Payment result = paymentService.getPaymentById("p1");

        assertNotNull(result);
        assertEquals("r1", result.getRideId());
        assertEquals(500.0, result.getAmount());
        assertEquals("UPI", result.getPaymentMethod());
    }

    @Test
    void testUpdatePayment() {
        Payment payment = new Payment("p2", "r2", 300.0, "Cash");
        paymentService.addPayment(payment);

        // Update amount and method
        payment.setAmount(350.0);
        payment.setPaymentMethod("Card");

        paymentService.updatePayment(payment);

        Payment updated = paymentService.getPaymentById("p2");

        assertEquals(350.0, updated.getAmount());
        assertEquals("Card", updated.getPaymentMethod());
    }

    @Test
    void testDeletePayment() {
        Payment payment = new Payment("p3", "r3", 150.0, "Card");
        paymentService.addPayment(payment);

        paymentService.deletePayment("p3");

        assertNull(paymentService.getPaymentById("p3"));
    }

    @Test
    void testGetAllPayments() {
        paymentService.addPayment(new Payment("p4", "r4", 200.0, "Cash"));
        paymentService.addPayment(new Payment("p5", "r5", 100.0, "UPI"));

        Map<String, Payment> allPayments = paymentService.getAllPayments();

        assertEquals(2, allPayments.size());
        assertTrue(allPayments.containsKey("p4"));
        assertTrue(allPayments.containsKey("p5"));
    }
}
