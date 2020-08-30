package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface IUserDAO {

	public List<User> getAllUsers();
	
	public User getUserById(int id);
	public User getUserByUsernameAndPassword(String username, String password);
	
	public boolean addUser(User u);
	public boolean updateUser(User u);
}
