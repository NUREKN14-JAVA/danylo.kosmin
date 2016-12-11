package kn.kosmin.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import kn.kosmin.User;

public class AddServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}
	public void testAdd() {
        Date date = new Date();
        User newUser = new User("Danya", "Kosmin", date);
        User user = new User(new Long(1000), "Danya", "Kosmin", date);
        getMockUserDao().expectAndReturn("create", newUser, user);
        
        addRequestParameter("firstName", "Danya");
        addRequestParameter("lastName", "Kosmin");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }
	
	public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("lastName", "Danya");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
	
	public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter("firstName", "Kosmin");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDate() {
        Date date = new Date();
        addRequestParameter("firstName", "Danya");
        addRequestParameter("lastName", "Kosmin");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDateIncorrect() {
        Date date = new Date();
        addRequestParameter("firstName", "Danya");
        addRequestParameter("lastName", "Kosmin");
        addRequestParameter("date", "oooooo");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

}
