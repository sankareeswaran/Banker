package com.hrb.taskmanager.task.account;

public class Person {
	PersonalDetail personDetail;
	AccountDetail accountDetail;
	Address permanentAddress;
	Address communicationAddress;

	public PersonalDetail getPersonDetail() {
		return personDetail;
	}
	public void setPersonDetail(PersonalDetail personDetail) {
		this.personDetail = personDetail;
	}
	public AccountDetail getAccountDetail() {
		return accountDetail;
	}
	public void setAccountDetail(AccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}
	public Address getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public Address getCommunicationAddress() {
		return communicationAddress;
	}
	public void setCommunicationAddress(Address communicationAddress) {
		this.communicationAddress = communicationAddress;
	}
}
