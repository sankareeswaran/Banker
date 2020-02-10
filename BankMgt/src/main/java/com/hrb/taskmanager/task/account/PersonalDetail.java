package com.hrb.taskmanager.task.account;

import java.util.Date;

public class PersonalDetail {
	String firstName = null;
	String lastName = null;
	Date dob = null;
	int matiralStatus;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getMatiralStatus() {
		return matiralStatus;
	}

	public void setMatiralStatus(int matiralStatus) {
		this.matiralStatus = matiralStatus;
	}
}
