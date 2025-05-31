package com.abes.ridebookingsystem.ui;

import java.util.Scanner;
import com.abes.ridebookingsystem.exception.RideException;
import com.abes.ridebookingsystem.service.RideServiceImpl;

import com.abes.ridebookingsystem.dto.*;
import com.abes.ridebookingsystem.service.UserService;
import com.abes.ridebookingsystem.service.UserServiceImpl;
import com.abes.ridebookingsystem.util.ValidationUtil;

public class RideBookingUI {
	private final UserServiceImpl userService = new UserServiceImpl();
	private final Scanner scanner = new Scanner(System.in);
	private final RideServiceImpl rideService = new RideServiceImpl();

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
			case "driver" -> {
				// License validation
				String license;
				while (true) {
					System.out.print("License Number: ");
					license = scanner.nextLine().trim();
					if (!ValidationUtil.isValidLicenseNumber(license)) {
						System.out.println("License number is required.");
					} else {
						break;
					}
				}

				boolean available = true;
				user = new Driver(userId, name, email, password, license, available);
			}
			}

			userService.registerUser(user);
			System.out.println("User registered successfully!");
			
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

	private void postLoginMenu(User user) throws RideException {
		if (user instanceof Customer customer) {
			customerMenu(customer);
		} else if (user instanceof Driver driver) {
			driverMenu(driver);
		} else if (user instanceof Admin admin) {
			adminMenu(admin);
		}
	}
	
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
	private void driverMenu(Driver driver) throws RideException {
		String driverId = driver.getUserId();
		while (true) {
			System.out.println("\n--- Driver Menu ---");
			System.out.println("1. View available rides");
			System.out.println("2. Accept a ride");
			System.out.println("3. Reject a ride");
			System.out.println("4. Logout");
			System.out.print("Choose an option: ");

			int choice;
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
				continue;
			}

			switch (choice) {
						case 1: // View available rides
							List<Ride> availableRides = rideService.getVisibleRidesForDriver(driverId);
							if (availableRides.isEmpty()) {
								System.out.println("No rides available for you at the moment.");
							} else {
								System.out.println("Available rides:");
								for (Ride ride : availableRides) {
									System.out.println("Ride ID: " + ride.getRideId() + ", Customer: " + ride.getCustomerId()
											+ ", Fare: ₹" + ride.getFare());
								}
							}
							break;
						case 2: // Accept a ride
							System.out.print("Enter Ride ID to accept: ");
							String acceptRideId = scanner.nextLine();
							boolean accepted = rideService.acceptRide(driverId, acceptRideId);
							if (accepted) {
								System.out.println("Ride accepted successfully!");
							} else {
								System.out.println("Failed to accept ride. It may not be available or already accepted.");
							}
							break;
						case 3: // Reject a ride
							System.out.print("Enter Ride ID to reject: ");
							String rejectRideId = scanner.nextLine();
							boolean rejected = rideService.rejectRide(driverId, rejectRideId);
							if (rejected) {
								System.out.println("Ride rejected successfully!");
							} else {
								System.out.println("Failed to reject ride.");
							}
							break;
						case 4: {
							System.out.println("Logging out from driver account...");
							System.out.println("Logged out");
							return;
						}
						default:
							System.out.println("Invalid option. Try again.");
						}
		}}
//	admin
	private void bookRide(Customer customer) {
		System.out.println("\n--- Book a Ride ---");

		// Step 1: Show Vehicle Options
		System.out.println("Available Vehicle Options:");
		for (WheelerType wt : WheelerType.values()) {
			System.out.println("\n" + wt + ":");
			for (VehicleType vt : VehicleType.values()) {
				if (vt.getWheelerType() == wt) {
					System.out.println(" - " + vt);
				}
			}
		}
		// Step 6: Book Ride
				try {
					Ride bookedRide = rideService.bookRide(customer.getUserId(), source, destination, totalFare);

					// Step 7: Confirmation
					System.out.println("\n--- Payment Summary ---");
					System.out.println("Customer: " + customer.getName());
					System.out.println("Vehicle: " + selectedVehicle);
					System.out.println("From: " + source + " -> To: " + destination);
					System.out.println("Distance: " + distance + " km");
					System.out.println("Payment Mode: " + paymentMethod);
					System.out.printf("Total Amount Paid: ₹%.2f\n", totalFare);
					System.out.println("Ride booked successfully with ID: " + bookedRide.getRideId());

				} catch (Exception e) {
					System.out.println("Unexpected error during booking: " + e.getMessage());
				}
			}

			private void cancelRide(Customer customer) {
				System.out.println("\n--- Cancel a Ride ---");
				System.out.print("Enter Ride ID to cancel: ");
				String rideId = scanner.nextLine().trim();

				try {
					// Add validation if necessary to check ride ownership

					rideService.cancelRide(rideId);
					System.out.println("Ride " + rideId + " cancelled successfully.");

				} catch (RideException e) {
					System.out.println("Could not cancel ride: " + e.getMessage());
				} catch (Exception e) {
					System.out.println("Unexpected error during ride cancellation: " + e.getMessage());
				}
			}
	

}