package com.revature.controllers;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
//import com.revature.services.UserServices;

public class UserController {

	//private static UserServices us = new UserServices();
	private static ObjectMapper om = new ObjectMapper();
	
	//This is to send the user role with your response so can go to role specific webpage
	public void sendUserRole(HttpServletRequest req, HttpServletResponse res, User u) throws IOException {
		
		if (u == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(u);
			res.getWriter().println(json);
		}
		
	}
	
	
}
