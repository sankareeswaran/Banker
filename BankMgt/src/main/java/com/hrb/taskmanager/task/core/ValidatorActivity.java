package com.hrb.taskmanager.task.core;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorActivity {
	private static final Logger logger = LoggerFactory.getLogger(ValidatorActivity.class);

	public boolean validateDepositDetails(Map<Integer, Integer> depositDetails) {
		if (depositDetails.entrySet().stream().filter(e -> e.getValue() < 0).count() > 0) {
			logger.error("Incorrect deposit amount");
			return false;
		} else if (depositDetails.entrySet().stream().filter(e -> e.getValue() == 0).count() == depositDetails.size()) {
			logger.error("Deposit amount cannot be zero");
			return false;
		}
		return true;
	}

	public boolean validateWithdraw(double withdrawBal, double availableBal) {
		if(withdrawBal <= 0 || withdrawBal > availableBal) {
			logger.error("Incorrect or insufficient funds. Available {} and requested {}", availableBal, withdrawBal);
			return false;
		}
		return true;
	}
}