package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Customer;
import com.abes.ridebookingsystem.dto.User;
import com.abes.ridebookingsystem.util.DataCollection;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

	private UserDao userDao;

	// This method runs before each test case
	@BeforeEach
	void setUp() {
		userDao = new UserDaoImpl(); // Create a new instance of UserDaoImpl
		DataCollection.userMap.clear(); // Clear the userMap to start fresh
	}

	// Test case for adding a new user
	@Test
	void testAddUser() {

		User user = new Customer("U101", "Ananya", "ananya@gmail.com", "secure@123", "9876543210");

		userDao.addUser(user);
		User savedUser = DataCollection.userMap.get("U101");

		assertNotNull(savedUser);
		assertEquals("Ananya", savedUser.getName());
		assertEquals("ananya@gmail.com", savedUser.getEmail());
	}

	// Test case for getting a user by ID
	@Test
	void testGetUserById() {

		User user = new Customer("U102", "Rohit", "rohit@yahoo.com", "pass4321", "9123456780");
		DataCollection.userMap.put("U102", user);

		User result = userDao.getUserById("U102");

		assertNotNull(result);
		assertEquals("Rohit", result.getName());
		assertEquals("rohit@yahoo.com", result.getEmail());
	}

	// Test case for updating an existing user
	@Test
	void testUpdateUser() {

		User oldUser = new Customer("U103", "Pooja", "pooja@oldmail.com", "abc123", "9001112233");
		DataCollection.userMap.put("U103", oldUser);

		User newUser = new Customer("U103", "Pooja Sharma", "pooja@gmail.com", "xyz456", "9009998877");

		userDao.updateUser(newUser);

		// Fetch updated user from the map
		User updatedUser = DataCollection.userMap.get("U103");

		// Check if updates are correct
		assertEquals("Pooja Sharma", updatedUser.getName());
		assertEquals("pooja@gmail.com", updatedUser.getEmail());
	}

	// Test case for deleting a user
	@Test
	void testDeleteUser() {

		User user = new Customer("U104", "Karan", "karan@outlook.com", "qwerty", "8080707060");
		DataCollection.userMap.put("U104", user);

		userDao.deleteUser("U104");

		assertNull(DataCollection.userMap.get("U104"));
	}

	// Test case for getting all users
	@Test
	void testGetAllUsers() {

		User user1 = new Customer("U105", "Sneha", "sneha@abc.com", "pwd1", "7894561230");
		User user2 = new Customer("U106", "Arjun", "arjun@xyz.com", "pwd2", "8765432109");
		DataCollection.userMap.put("U105", user1);
		DataCollection.userMap.put("U106", user2);

		Map<String, User> users = userDao.getAllUsers();

		assertEquals(2, users.size());
		assertTrue(users.containsKey("U105"));
		assertTrue(users.containsKey("U106"));
	}
}
