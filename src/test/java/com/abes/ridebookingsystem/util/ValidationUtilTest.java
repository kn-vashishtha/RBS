package com.abes.ridebookingsystem.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidationUtilTest {

    // isValidUserId Tests
    @Test
    void testValidUserId() {
        assertTrue(ValidationUtil.isValidUserId("user123"));
    }

    @Test
    void testInvalidUserId_NullOrEmpty() {
        assertFalse(ValidationUtil.isValidUserId(null));
        assertFalse(ValidationUtil.isValidUserId(""));
        assertFalse(ValidationUtil.isValidUserId("   "));
    }

    // isValidName Tests
    @Test
    void testValidName() {
        assertTrue(ValidationUtil.isValidName("John Doe"));
        assertTrue(ValidationUtil.isValidName("Alice"));
    }

    @Test
    void testInvalidName_WithNumbersOrSpecialChars() {
        assertFalse(ValidationUtil.isValidName("John123"));
        assertFalse(ValidationUtil.isValidName("Alice!"));
        assertFalse(ValidationUtil.isValidName(""));
        assertFalse(ValidationUtil.isValidName(null));
    }

    // isValidEmail Tests
    @Test
    void testValidEmail() {
        assertTrue(ValidationUtil.isValidEmail("test@example.com"));
        assertTrue(ValidationUtil.isValidEmail("john.doe@sub.domain.co.in"));
    }

    @Test
    void testInvalidEmail() {
        assertFalse(ValidationUtil.isValidEmail("invalid-email"));
        assertFalse(ValidationUtil.isValidEmail("test@.com"));
        assertFalse(ValidationUtil.isValidEmail(""));
        assertFalse(ValidationUtil.isValidEmail(null));
    }

    // isValidPassword Tests
    @Test
    void testValidPassword() {
        assertTrue(ValidationUtil.isValidPassword("Password@123"));
        assertTrue(ValidationUtil.isValidPassword("Secure#Pass1"));
    }

    @Test
    void testInvalidPassword() {
        assertFalse(ValidationUtil.isValidPassword("short1!"));
        assertFalse(ValidationUtil.isValidPassword("nouppercase@1"));
        assertFalse(ValidationUtil.isValidPassword("NOLOWERCASE@1"));
        assertFalse(ValidationUtil.isValidPassword("NoSpecialChar1"));
        assertFalse(ValidationUtil.isValidPassword("        "));
        assertFalse(ValidationUtil.isValidPassword(null));
    }

    // isValidPhoneNumber Tests
    @Test
    void testValidPhoneNumber() {
        assertTrue(ValidationUtil.isValidPhoneNumber("9876543210"));
        assertTrue(ValidationUtil.isValidPhoneNumber("+919876543210"));
    }

    @Test
    void testInvalidPhoneNumber() {
        assertFalse(ValidationUtil.isValidPhoneNumber("1234567890"));
        assertFalse(ValidationUtil.isValidPhoneNumber("abcd123456"));
        assertFalse(ValidationUtil.isValidPhoneNumber(""));
        assertFalse(ValidationUtil.isValidPhoneNumber(null));
    }

    // isNotEmpty Tests
    @Test
    void testNotEmpty() {
        assertTrue(ValidationUtil.isNotEmpty("value"));
        assertFalse(ValidationUtil.isNotEmpty(""));
        assertFalse(ValidationUtil.isNotEmpty("   "));
        assertFalse(ValidationUtil.isNotEmpty(null));
    }

    // isValidLicenseNumber Tests
    @Test
    void testValidLicenseNumber() {
        assertTrue(ValidationUtil.isValidLicenseNumber("DL1234567890"));
    }

    @Test
    void testInvalidLicenseNumber() {
        assertFalse(ValidationUtil.isValidLicenseNumber(""));
        assertFalse(ValidationUtil.isValidLicenseNumber("   "));
        assertFalse(ValidationUtil.isValidLicenseNumber(null));
    }
}