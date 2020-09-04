package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.ReimbType;
import com.revature.utils.HibernateUtil;

public class ReimbTypeDAO implements IReimbTypeDAO {

	@Override
	public ReimbType getRTypeById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		ReimbType rt = sesh.get(ReimbType.class, id);
		
		return rt;
	}

	
	
}
