package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.UserDao;
import com.abes.ridebookingsystem.dao.UserDaoImpl;
import com.abes.ridebookingsystem.dao.CustomerDao;
import com.abes.ridebookingsystem.dao.CustomerDaoImpl;
import com.abes.ridebookingsystem.dto.User;
import com.abes.ridebookingsystem.dto.Customer;

import java.util.Map;

//This class provides the actual implementation of user-related services.
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();

    //method that register's the user
    @Override
    public void registerUser(User user) {
        userDao.addUser(user);
        if (user instanceof Customer) {
            customerDao.addCustomer((Customer) user);
        }
    }

    // method that login's the user
    @Override
    public User loginUser(String userId, String password) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found.");
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password.");
        }
        return user;
    }
    
    //method that returns user of the id given
    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    //method that returns all the users 
    @Override
    public Map<String, User> getAllUsers() {
        return userDao.getAllUsers();
    }

	

	}
