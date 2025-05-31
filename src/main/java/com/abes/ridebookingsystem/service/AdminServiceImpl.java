package com.abes.ridebookingsystem.service;

import com.abes.ridebookingsystem.dao.AdminDao;
import com.abes.ridebookingsystem.dao.AdminDaoImpl;
import com.abes.ridebookingsystem.dto.Admin;

import java.util.Map;

public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = new AdminDaoImpl();

    @Override
    public void addAdmin(Admin admin) {              // Adds a new Admin to the system
        adminDao.addAdmin(admin);
    }

    @Override
    public Admin getAdminById(String userId) {       //Retrieves an Admin by their user ID
        return adminDao.getAdminById(userId);
    }

    @Override
    public void updateAdmin(Admin admin) {           // Updates an existing Admin's details
        adminDao.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(String userId) {       //Deletes an Admin from the system by their user ID
        adminDao.deleteAdmin(userId);  
    }

    @Override
    public Map<String, Admin> getAllAdmins() {    // Retrieves all Admins in the system
        return adminDao.getAllAdmins();
    }
}
