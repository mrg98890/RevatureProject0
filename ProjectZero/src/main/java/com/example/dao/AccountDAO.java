package com.example.dao;

import java.util.List;

import com.example.model.Account;

public interface AccountDAO {
	
	boolean insertAccount(Account account, int clientId);
	List<Account> getAllAccounts();
	List<Account> getAccountsByClientId(int clientId);
	Account getAccountById(int accountId);
	void updateAccountBalance(Account account);
	void changeAccountTypeForId(int accountId,String accounttype );
	boolean deleteAccount(int id);

}
