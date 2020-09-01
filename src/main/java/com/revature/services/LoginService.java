package com.revature.services;

import com.revature.models.LoginDTO;
import com.revature.models.User;

public class LoginService {

	public UserServices us = new UserServices();
	
	public boolean login(LoginDTO l) {
		try { 
			String username = l.username;
			String password = l.password;
			
			User u = us.getUserByUsername(username);
			if (u != null) {
				StringBuilder sb = new StringBuilder();
				sb.append(password.hashCode());
				String hashedPassword = sb.toString();
				
				if (u.getPassWord().equals(hashedPassword)) {
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
