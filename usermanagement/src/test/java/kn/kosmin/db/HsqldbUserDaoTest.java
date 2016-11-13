package kn.kosmin.db;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import kn.kosmin.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	

	public void setUp() throws Exception {
	super.setUp();
	dao = new HsqldbUserDao(connectionFactory);
	}

	@Test
	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Danya");
			user.setLastName("Kosmin");
			user.setDateOfBirthd(new Date());
			assertNotNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			 
			e.printStackTrace();
			fail(e.toString());
		}
		
	}

	public void testFindAll(){
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null",collection);
			assertEquals("Collection size.",2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	public void testUpdate() throws DatabaseException {
		try{
		User user = new User();
		user.setFirstName("Danya");
		user.setLastName("Kosmin");
		user.setDateOfBirthd(new Date());
		user.setId(1000L);
		dao.update(user);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	public void testDelete() throws DatabaseException {
		try{
		User user = new User();
		User newUser = new User();
		user.setId(1001L);;
		dao.delete(user);
		newUser = dao.find(user.getId());
		assertNull("User did not delete",newUser);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	public void testFind() throws DatabaseException {
		try{
		User user = dao.find(1001L);
		assertEquals("User dose not exist",user.getId(),1001L);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		}
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
