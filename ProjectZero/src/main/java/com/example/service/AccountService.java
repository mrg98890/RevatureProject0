package com.example.service;

import java.util.List;

import com.example.dao.AccountDAOImpl;
import com.example.model.Account;

public class AccountService {
	
	private AccountDAOImpl aDao;
	
	public AccountService() {
		
	}
	
	public AccountService(AccountDAOImpl aDao) {
		this.aDao=aDao;
	}
	
	public Account addFunds(int accountId, double value) {
		
		Account account = aDao.getAccountById(accountId);
		account.setBalance(account.getBalance()+value);
		aDao.updateAccountBalance(account);
		return account;
		
	}
	
	public Account subtractFunds(int accountId, double value) {
		Account account = aDao.getAccountById(accountId);
		account.setBalance(account.getBalance()-value);
		aDao.updateAccountBalance(account);
		return account;
	}
	
	public Account findAccountByAccountId(int accountId) {
		
		return aDao.getAccountById(accountId);
		
	}
	
	public List<Account> findAllAccount(){
		return aDao.getAllAccounts();
	}
	
	public List<Account> findAccountByClientId(int clientId) {
		return aDao.getAccountsByClientId(clientId);
	}
	
	public boolean createAccount(Account account, int clientId) {
		return aDao.insertAccount(account,clientId);
	}
	
	public Account retrieveAccountForId(int accountid) {
		return aDao.getAccountById(accountid);
	}
	
	public void updateAccountTypeById(int id, String c) {
		aDao.changeAccountTypeForId(id, c);
	}
	
	public boolean removeAccount(int id) {
		return aDao.deleteAccount(id);
	}
}