package com.abes.ridebookingsystem.ui;

import java.util.Scanner;

import com.abes.ridebookingsystem.dto.*;
import com.abes.ridebookingsystem.service.UserService;
import com.abes.ridebookingsystem.service.UserServiceImpl;
import com.abes.ridebookingsystem.util.ValidationUtil;

public class RideBookingUI {
	private final UserServiceImpl userService = new UserServiceImpl();
	private final Scanner scanner = new Scanner(System.in);

	public void start() {
		System.out.println("Welcome to the Ride Booking System!");

		while (true) {
			System.out.println("\n1. Register User");
			System.out.println("2. Login User");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");

			int option;
			try {
				option = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
				continue;
			}

			switch (option) {
			case 1 -> registerUser();
			case 2 -> loginUser();
			case 3 -> {
				System.out.println("Exiting system. Goodbye!");
				return;
			}
			default -> System.out.println("Invalid option! Please try again.");
			}
		}
	}
	

	private void registerUser() {
		// ==================== REGISTER USER ====================
		System.out.println("\n--- Register User ---");

		System.out.print("Enter user type (customer/driver): ");
		String type = scanner.nextLine().trim().toLowerCase();

		if (type.equals("admin")) {
			System.out.println("Admin cannot be registered.");
			return;
		}

		if (!type.equals("customer") && !type.equals("driver")) {
			System.out.println("Invalid user type.");
			return;
		}

		// User ID validation
		String userId;
		while (true) {
			System.out.print("User ID: ");
			userId = scanner.nextLine().trim();

			if (!ValidationUtil.isValidUserId(userId)) {
				System.out.println("User ID is required.");
				continue; // Go back to the beginning of the loop
			}

			if (userService.getAllUsers().containsKey(userId)) {
				System.out.println("User ID already exists. Please try a different one.");
				continue;
			}

			// If both checks pass, break the loop
			break;
		}

		// Name validation
		String name;
		while (true) {
			System.out.print("Name: ");
			name = scanner.nextLine().trim();

			if (!ValidationUtil.isValidName(name)) {
				System.out.println("Enter Correct Name.");
				continue; // Go back to the beginning of the loop
			}

			// Valid input
			break;
		}

		String email;
		while (true) {
			System.out.print("Email: ");
			email = scanner.nextLine().trim();

			if (email.isEmpty()) {
				System.out.println("Email is required.");
			} else if (!ValidationUtil.isValidEmail(email)) {
				System.out.println("Invalid email format.");
			} else {
				break;
			}
		}

		// Password validation
		String password;
		while (true) {
			System.out.println("Password requirements:");
			System.out.println(" - 8 to 20 characters");
			System.out.println(" - At least 1 digit");
			System.out.println(" - At least 1 lowercase alphabet");
			System.out.println(" - At least 1 uppercase alphabet");
			System.out.println(" - At least 1 special character from ( @ # $ % ^ & - + = ( ) ! )");
			System.out.println(" - No whitespace allowed");
			System.out.print("Password: ");
			password = scanner.nextLine().trim();

			if (password.isEmpty()) {
				System.out.println("Password is required.");
			} else if (!ValidationUtil.isValidPassword(password)) {
				System.out.println("Invalid password format.");
			} else {
				break;
			}
		}
		

		User user = null;
		try {
			switch (type) {
			case "customer" -> {
				// Phone validation
				String phone;
				while (true) {
					System.out.print("Phone Number: ");
					phone = scanner.nextLine().trim();
					if (phone.isEmpty()) {
						System.out.println("Phone number is required.");
					} else if (!ValidationUtil.isValidPhoneNumber(phone)) {
						System.out.println("Enter a valid 10-digit phone number starting with 6, 7, 8, or 9.");
					} else {
						break;
					}
				}

				user = new Customer(userId, name, email, password, phone);
			}
			
			}
		} 
		catch (Exception e) {
			System.out.println("Unexpected error during registration: " + e.getMessage());
		}
	}
		
	private void loginUser() {
		System.out.println("\n--- User Login ---");

		String userId;
		while (true) {
			System.out.print("User ID: ");
			userId = scanner.nextLine().trim();
			if (!ValidationUtil.isNotEmpty(userId)) {
				System.out.println("Name is required.");
			} else {
				break;
			}
		}

		String password;
		while (true) {
			System.out.print("Password: ");
			password = scanner.nextLine().trim();
			if (!ValidationUtil.isNotEmpty(password)) {
				System.out.println("Name is required.");
			} else {
				break;
			}
		}

		try {
			User user = userService.loginUser(userId, password);
			System.out.println(
					"Login successful! Welcome, " + user.getName() + " (" + user.getClass().getSimpleName() + ")");
			//postLoginMenu(user);
		} catch (Exception e) {
			System.out.println("Unexpected error during login: " + e.getMessage());
		}
	}

//	private void postLoginMenu(User user) throws RideException {
//		if (user instanceof Customer customer) {
//			customerMenu(customer);
//		} else if (user instanceof Driver driver) {
//			driverMenu(driver);
//		} else if (user instanceof Admin admin) {
//			adminMenu(admin);
//		}
//	}
	
	private void customerMenu(Customer customer) {
		while (true) {
			System.out.println("\n--- Customer Menu ---");
			System.out.println("1. Book a Ride");
			System.out.println("2. Cancel Ride");
			System.out.println("3. Logout");
			System.out.print("Choose an option: ");

			int choice;
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
				continue;
			}

			switch (choice) {
			case 1 -> bookRide(customer);
			case 2 -> cancelRide(customer);
			case 3 -> {
				System.out.println("Logging out...");
				System.out.println("Logged out");
				return;
			}
			default -> System.out.println("Invalid option. Try again.");
			}
		}
	}

}