package com.revature;


import com.revature.daos.IUserRoleDAO;
import com.revature.daos.UserRoleDAO;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.UserServices;

public class Driver {

	private static IUserRoleDAO urDao = new UserRoleDAO();
	private static UserServices us = new UserServices();
	
	public static void main(String[] args) {
		
		//This was just me getting the hashcode for another password to test and see if something's wrong on DBeaver's side.
		//System.out.println(hpw1);
		
		//For some reason, data isn't showing up in table in DBeaver when trying this method
		//BUT Hibernate's insert statement still pops up in the console.
		addUser1();
	}
	
	public static void addUser1() {
		String pw1 = "iloveberries";
		StringBuilder sb = new StringBuilder();
		sb.append(pw1.hashCode());
		String hpw1 = sb.toString();
		
		UserRole ur = urDao.getRoleById(2);
		
		User u = new User("opnami", hpw1, "Nancy", "Ballar", "rawwpork@gmail.com", ur);
		us.addUser(u);
	}
	
}
