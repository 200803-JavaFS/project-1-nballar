package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.UserRole;
import com.revature.utils.HibernateUtil;

public class UserRoleDAO implements IUserRoleDAO{

	@Override
	public UserRole getRoleById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		UserRole ur = sesh.get(UserRole.class, id);
		
		return ur;
	}

	
	
}
