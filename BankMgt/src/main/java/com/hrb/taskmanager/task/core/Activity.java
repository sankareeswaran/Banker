package com.hrb.taskmanager.task.core;

import java.util.Arrays;

public enum Activity {

	DEPOSIT("Deposit"), WITHDRAW("Withdraw"), BALANCE("Balance");

	private String activityName;

	private Activity(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityName() {
		return activityName;
	}

	public static Activity lookup(String activityName) {
		return Arrays.stream(Activity.values()).filter(activity -> activity.getActivityName().equals(activityName)).findFirst()
				.orElse(null);
	}
}