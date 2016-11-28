package kn.kosmin;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

public class User {
	private long id;
	private String firstName;
	public User(String firstName, String lastName, java.util.Date date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirthd = date;
	}
	
	public User(Long id, String firstName, String lastName, java.util.Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirthd = date;
	}

	public User() {
	}

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

	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}
		if (this == obj){
			return true;
		}
		if (Objects.isNull(this.getId()) && Objects.isNull(obj)){
			return true;
			
		}
		return Objects.equals(this.getId(), (((User) obj).getId()));
	}
	public int hashCode() {
		if (Objects.isNull(this.getId())){
			return 0;
		}
		return Objects.hashCode(this.getId());
		
	}


	
	
}
