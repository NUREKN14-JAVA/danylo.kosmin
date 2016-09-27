package kn.kosmin;

import java.sql.Date;
import java.util.Calendar;

public class User {
	private long id;
	private String firstName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public java.util.Date getDateOfBirthd() {
		return dateOfBirthd;
	}
	public void setDateOfBirthd(java.util.Date dateOfBirthd2) {
		this.dateOfBirthd = dateOfBirthd2;
	}
	private String lastName;
	private java.util.Date dateOfBirthd;
	public Object getFullName() {
		return getFirstName() + "," + getLastName();
	}
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.setTime(dateOfBirthd);
		int year = calendar.get(Calendar.YEAR);
		return currentYear - year;
	}
}
