package com.hrb.taskmanager.task.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityManager {
	private ActivityManager() {
	}

	private static final Logger logger = LoggerFactory.getLogger(ActivityManager.class);

	public static Activity getActivityByActivityName(String activityName) {
		logger.info("ActivityManager getTaskInstanceByTask taskName {}", activityName);
		Activity activity = Activity.lookup(activityName);
		logger.info("ActivityManager perform TaskName {} and mapping task {}", activityName, activity);
		if (null == activity) {
			return null;
		}
		return activity;
	}
}