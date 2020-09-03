package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.LoginService;

public class LoginController {

	private static LoginService ls = new LoginService();
	private static ObjectMapper om = new ObjectMapper();
	
	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		if (req.getMethod().equals("POST")) {
			
			BufferedReader reader = req.getReader();
			
			StringBuilder sb = new StringBuilder();
			
			String line = reader.readLine();
			
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			
			String body = new String(sb);
			
			User u = om.readValue(body, User.class);
			
			if(ls.login(u)) {
				HttpSession sess = req.getSession();
				sess.setAttribute("user", u);
				sess.setAttribute("loggedin", true);
				res.setStatus(200);
				res.getWriter().println("Login Successful");
			} else {
				HttpSession sess = req.getSession(false);
				if (sess != null) {
					sess.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login Failed");
			}
		}
	}
	
}
