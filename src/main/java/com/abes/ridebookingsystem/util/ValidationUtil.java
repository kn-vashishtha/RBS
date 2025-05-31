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
    
 // Phone number validation (Indian numbers)
    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("^(\\+91)?[6-9]\\d{9}$");
    }
    
 // Common empty check
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
 // License number validation (basic check)
    public static boolean isValidLicenseNumber(String license) {
        return license != null && !license.trim().isEmpty();
    }
    
 // Ride distance validation
    public static boolean isValidDistance(double distance) {
        return distance >= 2 && distance <= 100;
    }

    // Location validation (basic check)
    public static boolean isValidLocation(String location) {
        return location != null && !location.trim().isEmpty();
    }

    // Vehicle type validation
    public static boolean isValidVehicleType(String vehicleType) {
        if (vehicleType == null) return false;
        try {
            VehicleType.valueOf(vehicleType.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    
    }
 // Enum validation helper
    public static <T extends Enum<T>> boolean isValidEnum(Class<T> enumClass, String value) {
        if (value == null) return false;
        try {
            Enum.valueOf(enumClass, value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
}