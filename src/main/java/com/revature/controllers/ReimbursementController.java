package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbStatusDAO;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.User;
import com.revature.services.ReimbursementServices;
import com.revature.services.UserServices;

public class ReimbursementController {

	private static ReimbStatusDAO rsdao = new ReimbStatusDAO();
	private static ReimbursementServices rs = new ReimbursementServices();
	private static UserServices us = new UserServices();
	private static ObjectMapper om = new ObjectMapper();
	
	//Sends back all of the reimbursements back as a response
	public void getAllReimbursements(HttpServletResponse res) throws IOException {
		
		List<Reimbursement> all = rs.getAllReimbursements();
		res.setStatus(200);
		String json = om.writeValueAsString(all);
		res.getWriter().println(json);
		
	}
	
	//Sends back all reimbursements by author
	public void getAllReimbursementsByAuthor(HttpServletResponse res, int aId) throws IOException {
		
		User u = us.getUserById(aId);
		List<Reimbursement> allReimbsByAuthor = rs.getAllReimbursementsByAuthor(u);
		if (allReimbsByAuthor != null && allReimbsByAuthor.isEmpty()) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(allReimbsByAuthor);
			res.getWriter().println(json);
		}
		
		
	}
	
	//Sends back all reimbursements by status
	public void getAllReimbursementsByStatus (HttpServletResponse res, int sId) throws IOException {
		
		ReimbStatus rStats = rsdao.getRStatusById(sId);
		List<Reimbursement> allReimbsByStatus = rs.getAllReimbursementsByStatus(rStats);
		
		if (allReimbsByStatus.isEmpty()) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(allReimbsByStatus);
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
		
		ReimbursementDTO rdto = om.readValue(body, ReimbursementDTO.class);
		
		double rAmt = rdto.amt;
		String rDesc = rdto.description;
		User rAuthor = us.getUserById(rdto.rAuthorId);
		ReimbStatus nrs = new ReimbStatus(1, "pending");
		
		String type = rdto.rType;
		
		ReimbType rtype = null;
		if (type.toLowerCase().equals("food")) {
			rtype = new ReimbType(1, "food");
		} else if (type.toLowerCase().equals("lodging")) {
			rtype = new ReimbType(2, "lodging");
		} else if (type.toLowerCase().equals("travel")) {
			rtype = new ReimbType(3, "travel");
		} else if (type.toLowerCase().equals("other")) {
			rtype = new ReimbType(4, "other");
		}
		
		Reimbursement r = new Reimbursement(rAmt, new Timestamp(System.currentTimeMillis()), null, rDesc, rAuthor, null, nrs, rtype);
		
		if (rs.addReimbursement(r)) {
			res.setStatus(201);
			res.getWriter().println("Reimbursement Created and Added to Database");
		} else {
			res.setStatus(403);
		}
		
	}
	
	//Updates a Reimbursement
	public void updateReimbStatus (HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder sb = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = new String(sb);
		
		ReimbursementDTO rdto = om.readValue(body, ReimbursementDTO.class);
		
		int rId = rdto.id;
		
		Reimbursement r = rs.getReimbursementById(rId);
		
		String status = rdto.rStatus;
		
		ReimbStatus rStatus = null;
		if (status.equals("approved")) {
			rStatus = new ReimbStatus(2, "approved");
		} else if (status.equals("denied")) {
			rStatus = new ReimbStatus(3, "denied");
		}
		
		int resolverId = rdto.rAuthorId;
		
		r.setReimbStatusId(rStatus);
		User resolver = us.getUserById(resolverId);
		r.setReimbResolverId(resolver);
		r.setResolved(new Timestamp(System.currentTimeMillis()));
		
		if (rs.updateReimbursement(r)) {
			res.setStatus(202);
			res.getWriter().println("Reimbursement Updated");
		} else {
			res.setStatus(403);
		}
		
	}
	
	
}
