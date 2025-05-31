package com.abes.ridebookingsystem.util;

import java.util.regex.Pattern;

import com.abes.ridebookingsystem.dto.PaymentMethod;
import com.abes.ridebookingsystem.dto.VehicleType;

public class ValidationUtil {

    // Private constructor to prevent instantiation
    private ValidationUtil() {}

    // User ID validation
    public static boolean isValidUserId(String userId) {
        return userId != null && !userId.trim().isEmpty();
    }

    // Name validation - only letters and spaces
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[A-Za-z ]+$");
    }

    // Email validation
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }

    // Password validation
    public static boolean isValidPassword(String password) {
        return password != null && 
               password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&\\-+=()!])(?=\\S+$).{8,20}$");
    }
    
 // Common empty check
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}