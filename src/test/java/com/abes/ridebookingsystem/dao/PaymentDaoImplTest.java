package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Payment;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDaoImplTest {

    private PaymentDao paymentDao;

    @BeforeEach
    void setUp() {
        paymentDao = new PaymentDaoImpl();
        DataCollection.paymentMap.clear(); // Clear payment map before each test
    }

    @Test
    void testAddPayment() {
        Payment payment = new Payment("P1", "R1", 500.0, "Credit Card");
        paymentDao.addPayment(payment);

        Payment retrieved = paymentDao.getPaymentById("P1");
        assertNotNull(retrieved);
        assertEquals(500.0, retrieved.getAmount());
        assertEquals("Credit Card", retrieved.getPaymentMethod());
    }

    @Test
    void testGetPaymentById() {
        Payment payment = new Payment("P2", "R2", 300.0, "Cash");
        DataCollection.paymentMap.put("P2", payment);

        Payment retrieved = paymentDao.getPaymentById("P2");
        assertNotNull(retrieved);
        assertEquals("Cash", retrieved.getPaymentMethod());
    }

    @Test
    void testUpdatePayment() {
        Payment payment = new Payment("P3", "R3", 200.0, "Debit Card");
        paymentDao.addPayment(payment);

        Payment updatedPayment = new Payment("P3", "R3", 250.0, "Debit Card");
        paymentDao.updatePayment(updatedPayment);

        Payment result = paymentDao.getPaymentById("P3");
        assertEquals(250.0, result.getAmount());
    }

    @Test
    void testDeletePayment() {
        Payment payment = new Payment("P4", "R4", 150.0, "UPI");
        paymentDao.addPayment(payment);

        paymentDao.deletePayment("P4");
        assertNull(paymentDao.getPaymentById("P4"));
    }

    @Test
    void testGetAllPayments() {
        Payment payment1 = new Payment("P5", "R5", 100.0, "Net Banking");
        Payment payment2 = new Payment("P6", "R6", 400.0, "Wallet");

        paymentDao.addPayment(payment1);
        paymentDao.addPayment(payment2);

        Map<String, Payment> payments = paymentDao.getAllPayments();
        assertEquals(2, payments.size());
        assertTrue(payments.containsKey("P5"));
        assertTrue(payments.containsKey("P6"));
    }
}
