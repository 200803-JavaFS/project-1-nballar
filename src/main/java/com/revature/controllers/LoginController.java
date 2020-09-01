package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
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
			
			LoginDTO l = om.readValue(body, LoginDTO.class);
			
			if(ls.login(l)) {
				HttpSession sess = req.getSession();
				sess.setAttribute("user", l);
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
	
	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession sess = req.getSession(false);
		
		if(sess != null) {
			LoginDTO l = (LoginDTO) sess.getAttribute("user");
			sess.invalidate();
			res.setStatus(200);
			res.getWriter().println(l.username+" has logged out successfully");
		} else {
			res.setStatus(400);
			res.getWriter().println("You must be logged in to log out.");
		}
	}
	
}
