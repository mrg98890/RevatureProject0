package com.example.model;

public class Account {
	
	private int accountId;
	private String accountType;
	private double balance;
	
	public Account() {
		
	}
	
	public Account(String accountType, double balance) {
		super();
		this.accountType = accountType;
		this.balance = balance;
	}

	public Account(int accountId, String accountType, double balance) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setCardType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", balance=" + balance + "]\n";
	}

}
