package com.hrb.taskmanager.task.account;

public class AccountDetail {
	private String accountNumber = null;
	private String accountBranch = null;
	private int accountType = 0; // 1 for saving, 2 for current account
	private double availableBal = 0.0000;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public double getAvailableBal() {
		return availableBal;
	}

	public void setAvailableBal(double availableBal) {
		this.availableBal = availableBal;
	}
}
