package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.User;
import com.abes.ridebookingsystem.dto.Customer;
import java.util.Map;

//This interface defines services related to users

public interface UserService {
    void registerUser(User user);//registers the user
    User loginUser(String userId, String password);//logins the user
    User getUserById(String userId);//get user by id
    Map<String, User> getAllUsers();//get all users
}
