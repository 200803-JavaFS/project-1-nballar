package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementServices {

	private static final Logger log = LogManager.getLogger(ReimbursementServices.class);
	private static IReimbursementDAO rDao = new ReimbursementDAO();
	
	public List<Reimbursement> getAllReimbursements(){
		log.info("Getting all Reimbursements");
		return rDao.getAllReimbursements();
	}
	
	public List<Reimbursement> getAllReimbursementsByAuthor(User author){
		log.info("Getting all Reimbursements by author "+author);
		return rDao.getAllReimbursementsByAuthor(author);
	}
	
	public List<Reimbursement> getAllReimbursementsByStatus(ReimbStatus status){
		log.info("Getting all Reimbursements by status"+status);
		return rDao.getAllReimbursementsByStatus(status);
	}
	
	public Reimbursement getReimbursementById(int id){
		log.info("Getting Reimbursement by id "+id);
		return rDao.getReimbursementById(id);
	}
	
	public boolean addReimbursement(Reimbursement r){
		log.info("Adding Reimbursement "+r);
		return rDao.addReimbursement(r);
	}
	
	public boolean updateReimbursement(Reimbursement r){
		log.info("Updating Reimbursement "+r);
		return rDao.updateReimbursement(r);
	}
	
}
