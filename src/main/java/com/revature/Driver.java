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
		
		//Adding Users now persists to the database. Will add reimbursements through DBeaver because thinking of using Trigger Functions 
		//for inserting reimbursements and updating reimbursements
		addUsers();
		
//		String testpw = "test";
//		StringBuilder sbtest = new StringBuilder();
//		sbtest.append(testpw.hashCode());
//		String testhpw = sbtest.toString();
//		System.out.println(testhpw);
		
	}
	
	public static void addUsers() {
		String pw1 = "edlovescat";
		StringBuilder sb1 = new StringBuilder();
		sb1.append(pw1.hashCode());
		String hpw1 = sb1.toString();
		
		String pw2 = "iloveberries";
		StringBuilder sb2 = new StringBuilder();
		sb2.append(pw2.hashCode());
		String hpw2 = sb2.toString();
		
		UserRole ur1 = urDao.getRoleById(1);
		UserRole ur2 = urDao.getRoleById(2);
		
		User u1 = new User("the_eddie", hpw1, "Eddie", "Ballar", "rawwpork@gmail.com", ur1);
		User u2 = new User("opnami", hpw2, "Nancy", "Ballar", "lightituplikedynamite@gmail.com", ur2);
		
		us.addUser(u1);
		us.addUser(u2);
	}
	
}
