package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRole;

public class ServiceTests {

	public static ReimbStatus rstest;
	public static ReimbType rttest;
	public static Reimbursement rtest;
	public static User utest;
	public static UserRole urtest;
	
	public static LoginService ls;
	public static ReimbursementServices rs;
	public static UserServices us;
	
	@BeforeClass
	public static void setServices() {
		ls = new LoginService();
		rs = new ReimbursementServices();
		us = new UserServices();
	}
	
	@Before
	public void setTests() {
		urtest = new UserRole(1, "Employee");
		utest = new User("test1", "3556498", "Test", "Test", "test1@gmail.com", urtest);
		rstest = new ReimbStatus(1, "pending");
		rttest = new ReimbType(1, "food");
		rtest = new Reimbursement(77.77, "test stuff", utest, rstest, rttest);
	}
	
	@Test
	public void addUserTest() {
		boolean addedUser = us.addUser(utest);
		assertEquals(addedUser, true);
	}
	
	
	@Test
	public void addReimbTest() {
		User ut = us.getUserById(7);
		Reimbursement rt = new Reimbursement(77.77, new Timestamp(System.currentTimeMillis()), null, "test stuff", ut, null, rstest, rttest);
		boolean addedReimb = rs.addReimbursement(rt);
		assertTrue(addedReimb);
	}
	
	@AfterClass
	public static void reset() {
		urtest = null;
		utest = null;
		rstest = null;
		rttest = null;
		rtest = null;
		ls = null;
		rs = null;
		us = null;
	}
	
	
}
