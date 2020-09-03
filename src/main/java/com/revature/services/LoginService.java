package com.revature.services;

import com.revature.models.User;

public class LoginService {

	public UserServices us = new UserServices();
	
	public boolean login(User u) {
		try { 
			String password = u.getPassWord();
			
			User realU = us.getUserByUsername(u.getUserName());
			if (realU != null) {
				StringBuilder sb = new StringBuilder();
				sb.append(password.hashCode());
				String hashedPassword = sb.toString();
				
				if (realU.getPassWord().equals(hashedPassword)) {
					return true;
				} else {
					System.out.println("Wrong password.");
				}
			}
			
		} catch (NullPointerException e) {
			System.out.println("User doesn't exist");
			e.printStackTrace();
		}
		
		return false;
	}
	
}
