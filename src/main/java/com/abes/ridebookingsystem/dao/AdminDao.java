package com.abes.ridebookingsystem.dao;                  // Package declaration 

import com.abes.ridebookingsystem.dto.Admin;             // Importing the Admin class from the dto
import java.util.Map;                                   // Importing Map interface

public interface AdminDao {                              // Declaring the AdminDao interface
	void addAdmin(Admin admin);                          // Method to add a new Admin

	Admin getAdminById(String userId);                   // Method to retrieve a specific Admin(user Id as key)

	void updateAdmin(Admin admin);                       // Method to update an existing Admin's information

	void deleteAdmin(String userId);                     // Method to delete an Admin (user Id as Key)

	Map<String, Admin> getAllAdmins();                   // Method to retrieve all Admins stored
}