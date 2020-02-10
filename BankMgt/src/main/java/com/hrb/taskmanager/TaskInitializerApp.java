package com.hrb.taskmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrb.taskmanager.task.account.AccountDetail;
import com.hrb.taskmanager.task.account.Treasury;
import com.hrb.taskmanager.task.account.Person;
import com.hrb.taskmanager.task.core.Activity;
import com.hrb.taskmanager.task.core.ActivityManager;
import com.hrb.taskmanager.task.core.ValidatorActivity;

//@SpringBootApplication
public class TaskInitializerApp {

	private static final Logger logger = LoggerFactory.getLogger(TaskInitializerApp.class);

	public Map<String, Person> getPersons() {
		Map<String, Person> persons = new HashMap<>();

		String accNumber = "1";
		Person person = new Person();
		person.setAccountDetail(getAccountDetail(accNumber, "SBI0000123", 1, 500.0000));
		persons.put(accNumber, person);

		accNumber = "2";
		person = new Person();
		person.setAccountDetail(getAccountDetail(accNumber, "SBI0000123", 1, 00.0000));
		persons.put(accNumber, person);

		accNumber = "3";
		person = new Person();
		person.setAccountDetail(getAccountDetail(accNumber, "SBI0000123", 1, 1000.0000));
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
		String activityName = null;
		String transNumber = null;
		ValidatorActivity validatorActivity = null;
		try {
			if (null != args && args.length > 1) {
				Map<Integer, Integer> depositDetails = new HashMap();
				Treasury treasury = new Treasury();
				validatorActivity = new ValidatorActivity();
				activityName = args[0];
				transNumber = args[1];
				logger.info("Transaction Number {}.", transNumber);
				treasury.loadCash();
				logger.info("Balance: {}, Total: {}", treasury.printDenomination(), treasury.getBalance());
				Activity activity = ActivityManager.getActivityByActivityName(activityName);
				if (null == activity) {
					logger.error("Invalid activity. Unable to find Activity for {}.", activityName);
				} else {
					switch (activity) {
					case DEPOSIT:
						logger.info("Activity deposit");
						for (int i = 2; i < args.length; i++) {
							logger.info("Activity {}", args[i]);
							String[] valuePair = args[i].split(",")[0].split(":");
							if(Optional.ofNullable(valuePair[0]) != null && Optional.ofNullable(valuePair[1]) != null ) {
								depositDetails.put(Integer.parseInt(valuePair[0].replace("s", "")), Integer.parseInt(valuePair[1]));						
							}
						}
						if(validatorActivity.validateDepositDetails(depositDetails)) {
							treasury.deposit(depositDetails);
						}
						logger.info("Balance: {}, Total: {}", treasury.printDenomination(), treasury.getBalance());
						break;

					case WITHDRAW:
						logger.info("Activity withdraw amount {}", args[2]);
						if(validatorActivity.validateWithdraw(Double.parseDouble(args[2]), treasury.getBalance())) {
							treasury.withdraw(Integer.parseInt(args[2]));
						}
						logger.info("Balance: {}, Total: {}", treasury.printDenomination(), treasury.getBalance());
						break;

					case BALANCE:
						logger.info("Balance: {}, Total: {}", treasury.printDenomination(), treasury.getBalance());
						break;

					default:
						logger.info("Activity not found");
					}
				}
			} else {
				logger.error("Invalid args. Contact bank admin for more details");
			}
		} catch (Exception exp) {
			logger.error("Exception occurred. Task: {}. Check the command line args. Error: ", activityName, exp);
		}
		logger.info("Task completed: {}", activityName);
		logger.info("Loading main app completed");
		logger.info("{} has been completed...", TaskInitializerApp.class.getName());
	}
}