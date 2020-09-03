package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementServices;
import com.revature.services.UserServices;

public class ReimbursementController {

	private static ReimbursementServices rs = new ReimbursementServices();
	private static UserServices us = new UserServices();
	private static ObjectMapper om = new ObjectMapper();
	
	//Sends back a single reimbursement as a response
	public void getReimbursement(HttpServletResponse res, int id) throws IOException {
		
		Reimbursement r = rs.getReimbursementById(id);
		
		if (r == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(r);
			res.getWriter().println(json);
		}
		
	}
	
	//Sends back all of the reimbursements back as a response
	public void getAllReimbursements(HttpServletResponse res) throws IOException {
		
		List<Reimbursement> all = rs.getAllReimbursements();
		res.setStatus(200);
		String json = om.writeValueAsString(all);
		res.getWriter().println(json);
		
	}
	
	//Sends back all reimbursements by author
	public void getAllReimbursementsByAuthor(HttpServletResponse res, int id) throws IOException {
		
		User u = us.getUserById(id);
		List<Reimbursement> allReimbsByAuthor = rs.getAllReimbursementsByAuthor(u);
		if (allReimbsByAuthor != null && allReimbsByAuthor.isEmpty()) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(allReimbsByAuthor);
			res.getWriter().println(json);
		}
		
		
	}
	
	//Adds a new reimbursement
	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		System.out.println(body);
		
		Reimbursement r = om.readValue(body, Reimbursement.class);
		
		if (rs.addReimbursement(r)) {
			res.setStatus(201);
			res.getWriter().println("Reimbursement Created and Added to Database");
		} else {
			res.setStatus(403);
		}
		
	}
	
	
	
}
