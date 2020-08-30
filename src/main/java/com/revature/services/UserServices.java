package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.User;

public class UserServices {

	private static final Logger log = LogManager.getLogger(UserServices.class);
	private static IUserDAO uDao = new UserDAO();

	public List<User> getAllUsers() {
		log.info("Getting all Users");
		return uDao.getAllUsers();
	}
	
	public User getUserById(int id) {
		log.info("Getting User with id "+id);
		return uDao.getUserById(id);
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		log.info("Getting User by username "+username+" and password "+password);
		return uDao.getUserByUsernameAndPassword(username, password);
	}
	
	public boolean addUser(User u) {
		log.info("Adding a new User "+u);
		return uDao.addUser(u);
	}
	
	public boolean updateUser(User u) {
		log.info("Updating User "+u);
		return uDao.updateUser(u);
	}
	
}
