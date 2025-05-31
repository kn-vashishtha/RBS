package com.abes.ridebookingsystem.dao;

import com.abes.ridebookingsystem.dto.Admin;
import com.abes.ridebookingsystem.util.DataCollection;

import java.util.Map;

public class AdminDaoImpl implements AdminDao {                           //AdminDao interface for admin CRUD operations

	@Override
	public void addAdmin(Admin admin) {                                  // Adds a new admin to the map
		DataCollection.adminMap.put(admin.getUserId(), admin);
	}

	@Override
	public Admin getAdminById(String userId) {                            // Retrieves an admin by user ID
		return DataCollection.adminMap.get(userId);
	}

	@Override 
	public void updateAdmin(Admin admin) {                                // Updates an existing admin
		DataCollection.adminMap.put(admin.getUserId(), admin);
	}

	@Override
	public void deleteAdmin(String userId) {                               // Delete an admin by user ID               
		DataCollection.adminMap.remove(userId);
	}

	@Override
	public Map<String, Admin> getAllAdmins() {                             // Retrieves all admins
		return DataCollection.adminMap;
	}
}
