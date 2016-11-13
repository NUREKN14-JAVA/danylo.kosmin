package kn.kosmin.db;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase{

	
	
	public void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null", daoFactory);
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull("UserDao insance is null", userDao);
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}

}

