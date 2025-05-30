package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.User;
import com.abes.ridebookingsystem.util.DataCollection;
import java.util.Map;

// Implementation of the UserDao interface.

public class UserDaoImpl implements UserDao {

	private Map<String, User> userMap = DataCollection.userMap; // store users

	// Method to add user

	@Override
	public void addUser(User user) {
		if (userMap.containsKey(user.getUserId())) {
			throw new RuntimeException("User already exists");
		}
		userMap.put(user.getUserId(), user);
	}

	// method to get user by id

	@Override
	public User getUserById(String userId) {
		User user = userMap.get(userId);
		if (user == null) {
			throw new RuntimeException("User not found");
		}
		return user;
	}

	// method to update user

	@Override
	public void updateUser(User user) {
		if (!userMap.containsKey(user.getUserId())) {
			throw new RuntimeException("User not found");
		}
		userMap.put(user.getUserId(), user);
	}

	// method to delete user

	@Override
	public void deleteUser(String userId) {
		if (!userMap.containsKey(userId)) {
			throw new RuntimeException("User not found");
		}
		userMap.remove(userId);
	}

	// method to get all users

	@Override
	public Map<String, User> getAllUsers() {
		return userMap;
	}
}
