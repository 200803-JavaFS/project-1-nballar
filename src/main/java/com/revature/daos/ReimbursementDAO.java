package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAO implements IReimbursementDAO {

	public ReimbursementDAO() {
		super();
	}
	
	@Override
	public List<Reimbursement> getAllReimbursements() {
		Session sesh = HibernateUtil.getSession();
		
		List<Reimbursement> list = sesh.createQuery("FROM Reimbursement").list();
		
		return list;
	}

	@Override
	public List<Reimbursement> getAllReimbursementsByAuthor(User author) {
		Session sesh = HibernateUtil.getSession();
		
		List<Reimbursement> list = sesh.createQuery("FROM Reimbursement WHERE reimbAuthorId = "+author.getUserId(), Reimbursement.class).list();
		
		return list;
	}

	@Override
	public List<Reimbursement> getAllReimbursementsByStatus(ReimbStatus status) {
		Session sesh = HibernateUtil.getSession();
		
		List<Reimbursement> list = sesh.createQuery("FROM Reimbursement WHERE reimbStatusId = "+status.getStatusId(), Reimbursement.class).list();
		
		return list;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		Reimbursement r = sesh.get(Reimbursement.class, id);
		
		return r;
	}

	@Override
	public boolean addReimbursement(Reimbursement r) {
		Session sesh = HibernateUtil.getSession();
		
		try {
			sesh.save(r);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateReimbursement(Reimbursement r) {
		Session sesh = HibernateUtil.getSession();
		
		try {
			sesh.merge(r);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
