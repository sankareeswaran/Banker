package com.hrb.taskmanager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.hrb.taskmanager.task.account.AccountDetail;
import com.hrb.taskmanager.task.account.Denominations;
import com.hrb.taskmanager.task.account.Person;
import com.hrb.taskmanager.task.core.Activity;
import com.hrb.taskmanager.task.core.ActivityManager;

//@SpringBootApplication
public class TaskInitializerApp {

	private static final Logger logger = LoggerFactory.getLogger(TaskInitializerApp.class);

	public Map<String, Person> getPersons() {
		Map<String, Person> persons = new HashMap<String, Person>();
		
		AccountDetail accDetail = new AccountDetail();
		String accNumber = "1234567890";
		Person person = new Person();
		person.setAccountDetail(getAccountDetail(accNumber, "SBI0000123", 1, 500.0000));
		persons.put(accNumber, person);
		
		return persons;
	}

	public AccountDetail getAccountDetail(String accNo, String accBranch, int accType, double balance) {
		AccountDetail accDetail = new AccountDetail();
		accDetail.setAccountNumber(accNo);
		accDetail.setAccountBranch(accBranch);
		accDetail.setAccountType(accType);
		accDetail.setAvailableBal(balance);
		return accDetail;
	}

	public static void main(String[] args) {
		logger.info("Loading App...");
		if (null != args && args.length > 0) {
			Arrays.stream(args).forEach(activityName -> {
				logger.info("Requested Task: {}", activityName);
				try {
					Denominations denominations = new Denominations();
					Activity activity = ActivityManager.getActivityByActivityName(activityName);
					if (null == activity) {
						logger.error("Unable to find Task for {}.", activityName);
					} else {
						switch (activity) {
						case DEPOSIT:
							logger.info("Activity deposit");

						case WITHDRAW:
							logger.info("Activity withdraw");

						case BALANCE:
							logger.info("Activity balance"+denominations.getBalance());

						default:
							logger.info("Activity not found");
						}
					}
				} catch (Exception exp) {
					logger.error("Exception occurred. Task: {}. Check the command line args. Error: ", activityName,
							exp);
				}
				logger.info("Task completed: {}", activityName);
			});
		} else {
			logger.info("Task has no args");
		}
		logger.info("Loading main app completed");
		logger.info("{} has been completed...", TaskInitializerApp.class.getName());
	}
}