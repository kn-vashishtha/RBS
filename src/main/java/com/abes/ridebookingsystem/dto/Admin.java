package com.abes.ridebookingsystem.dto;                                                    // Package declaration

public class Admin extends User {                                                           // Admin class extends 'User'
	private String role;                                                                    // Additional field specific to Admin class

	public Admin(String userId, String name, String email, String password, String role) {   // Constructor to initialize Admin 
		super(userId, name, email, password);                                                // Call to the parent class User 
		this.role = role;                                                                    // Set the role
	}

	public String getRole() {                                                                // Getter method 
		return role;
	}

	public void setRole(String role) {                                                       // Setter method
		this.role = role;
	}

	@Override
	public String toString() {                                                                               // Overriding the toString()
		return "Admin{" + "userId='" + userId + '\'' + ", name='" + name + '\'' + ", email='" + email + '\''  // Customize the string
				+ ", role='" + role + '\'' + '}';
	}
}