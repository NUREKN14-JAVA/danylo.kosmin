package kn.kosmin.gui;


import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import kn.kosmin.User;
import kn.kosmin.db.DaoFactory;
import kn.kosmin.db.DaoFactoryImpl;
import kn.kosmin.db.MockDaoFactory;
import kn.kosmin.db.MockUserDao;
import kn.kosmin.gui.MainFrame;
import kn.kosmin.util.Messages;


public class MainFrameTest extends JFCTestCase {

	
	private MainFrame mainFrame;
	private ArrayList users;
	private Mock mockUserDao;
	private User expectedUser;
	private User user;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		Properties properties = new Properties();
		properties.setProperty("dao.factory",MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
		setHelper(new JFCTestHelper());
		mockUserDao.expectAndReturn("findAll", new ArrayList());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	
	protected void tearDown() throws Exception {
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Component find(Class componentClass, String name)
	{		NamedComponentFinder finder;
				finder = new NamedComponentFinder(componentClass, name);
				finder.setWait(0);
					Component component = finder.find(mainFrame,0);
					assertNotNull("Could not find component '" + name + "'", component);
	return component;
	}
	
	public void testBrowseControls() {
		
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(3, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("Имя", table.getColumnName(1));
		assertEquals("Фамилия", table.getColumnName(2));
		
		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailsButton");
	}
	
	public void testAddUser(){
		String firstName = "Danya";
		String lastName = "Kosmin";
		Date now = new Date();
		user = new User(firstName,lastName, now);
		
		expectedUser = new User(new Long(1), firstName, lastName, now);
		mockUserDao.expectAndReturn("create", user, expectedUser);
		users = new ArrayList();
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll", users);
		
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(0, table.getRowCount());
		
		JButton addButton = (JButton) find(JButton.class, "addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		
		find(JPanel.class, "addPanel");
		
		JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
		JButton okButton = (JButton) find(JButton.class, "okButton");
		 find(JButton.class, "cancelButton");
		getHelper().sendString(new StringEventData(this, firstNameField, firstName));
	
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		DateFormat formatter = DateFormat.getDateInstance();

		String date = formatter.format(now);
		getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, "browsePanel");
		table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
		
		
	}
	
	
}
