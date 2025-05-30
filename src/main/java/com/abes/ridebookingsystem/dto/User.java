package com.abes.ridebookingsystem.dto;

//It is an abstract class and is the parent for admin, customer, and driver

public abstract class User {
	protected String userId; // unique identification
	protected String name; // user full name
	protected String email; // user email id
	protected String password;// user password

	// Constructs a new User.
	
	public User(String userId, String name, String email, String password) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	//getter methods

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	//setter methods

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	// overridden to string method

	@Override
	public String toString() {
		return "User{" + "userId='" + userId + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
	}
}

