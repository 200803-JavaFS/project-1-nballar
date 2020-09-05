package com.revature.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.services.UserServices;

public class MasterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static LoginController lc = new LoginController();
	private static ReimbursementController rc = new ReimbursementController();
	private static UserController uc = new UserController();
	private static UserServices us = new UserServices();
	
	public MasterServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("application/json");
		res.setStatus(400);
		final String URI = req.getRequestURI().replace("/project1/", "");
		String[] portions = URI.split("/");
		
		System.out.println(Arrays.toString(portions));
		if (portions.length == 0) {
			req.getRequestDispatcher("index.html").forward(req, res);
		}
		
		try {
			switch (portions[0]) {
				case "login":
					lc.login(req, res);
					break;
				case "success":
					if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
						User u = (User) req.getSession().getAttribute("user");
						u = us.getUserByUsername(u.getUserName());
						if(req.getMethod().equals("GET")) {
							uc.sendUserRole(req, res, u);
						}
					}
					break;
				case "reimbursements":
					if(req.getMethod().equals("GET")) {
						if(portions.length == 2) {
							int rId = Integer.parseInt(portions[1]);
							rc.getReimbursement(res, rId);
						}else if(portions[1].equals("status")) {
							int rsId = Integer.parseInt(portions[2]);
							rc.getAllReimbursementsByStatus(res, rsId);
						} else if(portions[1].equals("author")){
							int raId = Integer.parseInt(portions[2]);
							rc.getAllReimbursementsByAuthor(res, raId);
						}else {
							rc.getAllReimbursements(res);
						}
						
					} else if(req.getMethod().equals("POST")) {
						rc.addReimbursement(req, res);
					} else if(req.getMethod().equals("PUT")) {
						rc.updateReimbStatus(req, res);
					}
					break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}
