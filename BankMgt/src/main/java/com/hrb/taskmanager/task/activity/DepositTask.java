package com.hrb.taskmanager.task.activity;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrb.taskmanager.task.account.Treasury;

public class DepositTask {
	DepositTask() {
		
	}
	private static final Logger logger = LoggerFactory.getLogger(DepositTask.class);
	public void deposit(Map<Integer, Integer> depositDetails) {
		Treasury treasury = new Treasury();
		treasury.deposit(depositDetails);
	}
}
