package com.metkal.taskit.dao;

import java.util.List;

import com.metkal.taskit.model.User;

public interface UserDao {
	
	List<User> getUsers();
	
	User getUser(String email);
	
	User addUser(User user);
	
	boolean deleteUser(long id, String password);
	
	User updateUser(User user);
	
	boolean changePassword(long id, String password, String newPassword);
	

}
