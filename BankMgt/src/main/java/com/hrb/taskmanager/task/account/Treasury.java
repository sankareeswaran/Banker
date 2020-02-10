package com.hrb.taskmanager.task.account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrb.taskmanager.TaskInitializerApp;

public class Treasury {
	private static final Logger logger = LoggerFactory.getLogger(TaskInitializerApp.class);

	Map<Integer, Integer> availableCurrencies = new HashMap<>();
	Map<Integer, Integer> dispensedCurr = new HashMap<>();

	public Treasury() {
	}

	public void loadCash() {
		availableCurrencies.put(1, 4);
		availableCurrencies.put(5, 10);
		availableCurrencies.put(10, 10);
		availableCurrencies.put(20, 3);
	}

	public void deposit(Map<Integer, Integer> depositDetails) {
		for (Entry<Integer, Integer> entry : depositDetails.entrySet()) {
			if (availableCurrencies.get(entry.getKey()) != null) {
				availableCurrencies.put(entry.getKey(), (availableCurrencies.get(entry.getKey()) + entry.getValue()));
			}
		}
	}

	public void withdraw(double amount) {
		int limitAmount = (int) amount;
		dispensedCurr = new HashMap<>();
		LinkedHashMap<Integer, Integer> sortedCurr = new LinkedHashMap<>();
		availableCurrencies.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).filter(x -> x.getKey() > 0 && x.getValue() > 0)
				.forEachOrdered(x -> sortedCurr.put(x.getKey(), x.getValue()));

		for (Entry<Integer, Integer> entry : sortedCurr.entrySet()) {
			System.out.println("before amount " + amount + ",  entry.getKey() " + entry.getKey());
			if (amount >= 1) {
				if (availableCurrencies.get(entry.getKey()) >= (int) amount / entry.getKey() &&  (int) amount / entry.getKey() < amount) {
					dispensedCurr.put(entry.getKey(), (int) amount / entry.getKey());
					availableCurrencies.put(entry.getKey(), (availableCurrencies.get(entry.getKey()) - (int) amount / entry.getKey()));
					amount = amount % entry.getKey();
					System.out.println("amount " + amount);
				} else {
					dispensedCurr.put(entry.getKey(), availableCurrencies.get(entry.getKey()));
					availableCurrencies.put(entry.getKey(), 0);
					amount = amount - (entry.getKey() * availableCurrencies.get(entry.getKey()));
				}
			}
		}
		printDispenedCurrency();
	}

	public void printDispenedCurrency() {
		List currList = new ArrayList();
		dispensedCurr.forEach((curValue, quantity) -> {
			if (curValue > 0 && quantity > 0) {
				currList.add(new StringBuilder().append(curValue).append("s=").append(+quantity).toString());
			}
		});
		logger.info("Dispensed: {}", String.join(", ", currList));
	}

	public String printDenomination() {
		List currList = new ArrayList();
		availableCurrencies.forEach((curValue, quantity) -> {
			if (curValue > 0 && quantity > 0) {
				currList.add(new StringBuilder().append(curValue).append("s=").append(+quantity).toString());
			}
		});
		return String.join(", ", currList);
	}

	public double getBalance() {
		return availableCurrencies.entrySet().stream()
				.mapToLong(e -> (e.getKey() > 0 && e.getValue() > 0) ? (e.getKey() * e.getValue()) : 0).sum();
	}
}
