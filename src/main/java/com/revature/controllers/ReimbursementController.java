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

public class ReimbursementController {

	private static ReimbursementServices rs = new ReimbursementServices();
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
		res.getWriter().println(om.writeValueAsString(all));
		res.setStatus(200);
		
	}
	
	//Sends back all reimbursements by author
	public void getAllReimbursementsByAuthor(HttpServletRequest req, HttpServletResponse res, User u) throws IOException {
		
		List<Reimbursement> allReimbsByAuthor = rs.getAllReimbursementsByAuthor(u);
		res.getWriter().println(om.writeValueAsString(allReimbsByAuthor));
		res.setStatus(200);
		
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
