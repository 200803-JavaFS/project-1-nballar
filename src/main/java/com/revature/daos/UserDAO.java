package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAO implements IUserDAO{
	
	public UserDAO() {
		super();
	}

	@Override
	public List<User> getAllUsers() {
		Session sesh = HibernateUtil.getSession();
		
		List<User> list = sesh.createQuery("FROM User").list();
		
		return list;
	}

	@Override
	public User getUserById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		User u = sesh.get(User.class, id);
		
		return u;
	}

	@Override
	public User getUserByUsername(String username) {
		Session sesh = HibernateUtil.getSession();
		
		List<User> list = sesh.createQuery("FROM User WHERE userName = '"+username+"'", User.class).list();
		User u = list.get(0);
		
		return u;
	}

	@Override
	public boolean addUser(User u) {
		Session sesh = HibernateUtil.getSession();
		
		try {
			sesh.save(u);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User u) {
		Session sesh = HibernateUtil.getSession();
		
		try {
			sesh.merge(u);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
