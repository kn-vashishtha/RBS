package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.User;
import java.util.Map;

// interface that provides the methods that can be performed on User
public interface UserDao {
	void addUser(User user); // to add user

	User getUserById(String userId); // to get user by id

	void updateUser(User user); // to update user

	void deleteUser(String userId); // to delete user

	Map<String, User> getAllUsers(); // to return all users
}
