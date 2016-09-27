package kn.kosmin;

import java.util.Calendar;
import java.util.Date;


import org.junit.*;
import junit.framework.TestCase;

public class UserTest extends TestCase {


	private User user;
	private Date dateOfBirthd;
	
	public void setUp() throws Exception {
		super.setUp();
		user = new User();
	
		Calendar calendar = Calendar.getInstance();
		calendar.set(1997, Calendar.MARCH, 28);
		dateOfBirthd =  calendar.getTime();
	}


	public void testGetFullName(){
		user.setFirstName("Danya");
		user.setLastName("Kosmin");
		assertEquals("Danya,Kosmin", user.getFullName());
	}
	
	public void testGetAge(){
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(2016 - 1997 , user.getAge());
	}
}
