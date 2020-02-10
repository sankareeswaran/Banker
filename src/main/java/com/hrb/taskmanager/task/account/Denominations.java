package com.hrb.taskmanager.task.account;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Denominations {

	Map<Integer, Integer> availableCurrencies = new HashMap<Integer, Integer>();
	availableCurrencies.put(5, 20);
	availableCurrencies.put(10, 30);
	availableCurrencies.put(20, 50);

	public void deposit(double amount) {
		
	}
	
	public void withdraw(double amount) {
		
	}
	
	public double getBalance() {
		return availableCurrencies.entrySet().stream().mapToLong(e -> e.getKey() * e.getValue()).sum();
	}
}
