package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.ReimbStatus;
import com.revature.utils.HibernateUtil;

public class ReimbStatusDAO implements IReimbStatusDAO {

	@Override
	public ReimbStatus getRStatusById(int id) {
		Session sesh = HibernateUtil.getSession();
		
		ReimbStatus rs = sesh.get(ReimbStatus.class, id);
		
		return rs;
	}

}
