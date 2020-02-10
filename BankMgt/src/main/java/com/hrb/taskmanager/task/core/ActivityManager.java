package com.hrb.taskmanager.task.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityManager {
	private ActivityManager() {
	}

	private static final Logger logger = LoggerFactory.getLogger(ActivityManager.class);

	public static Activity getActivityByActivityName(String activityName) {
		Activity activity = Activity.lookup(activityName);
		logger.info("ActivityManager perform ActivityName {} and mapping activity {}", activityName, activity);
		if (null == activity) {
			return null;
		}
		return activity;
	}
}