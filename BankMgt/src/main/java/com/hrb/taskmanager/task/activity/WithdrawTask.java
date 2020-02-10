package com.hrb.taskmanager.task.activity;

import com.hrb.taskmanager.task.account.Treasury;

public class WithdrawTask {
	WithdrawTask() {
		
	}
	public void withdraw(double balance) {
		Treasury treasury = new Treasury();
		treasury.withdraw(balance);
	}
}
