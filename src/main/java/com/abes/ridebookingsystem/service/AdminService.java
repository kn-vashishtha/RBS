package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dto.Admin;
import java.util.Map;

public interface AdminService {
    void addAdmin(Admin admin);             // Adds a new Admin to the system
    Admin getAdminById(String userId);       //Retrieves an Admin by their user ID
    void updateAdmin(Admin admin);          // Updates an existing Admin's details
    void deleteAdmin(String userId);        //Deletes an Admin from the system by their user ID.
    Map<String, Admin> getAllAdmins();      // Retrieves all Admins in the system
}

