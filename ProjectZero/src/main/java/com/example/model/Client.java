package com.example.model;

import java.util.List;

public class Client {
	
	private int clientId;
	private String clientName;
	private List<Account> accountList;
	
	public Client() {
		
	}
	
	public Client(String clientName) {
		super();
		this.clientName = clientName;
	}
	
	public Client( String clientName, List<Account> accountList) {
		super();
		this.clientName = clientName;
		this.accountList = accountList;
	}

	public Client(int clientId, String clientName, List<Account> accountList) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.accountList = accountList;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public int getClientId() {
		return clientId;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", accountList=" + accountList + "]";
	}
	

}
