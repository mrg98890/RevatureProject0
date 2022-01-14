package com.example.eval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.AccountDAOImpl;
import com.example.dao.BankDataBaseConnection;
import com.example.model.Account;



public class AccountDAOTest {
	@Mock
	private BankDataBaseConnection bankCon;
	@Mock
	private Connection con;
	@Mock
	private PreparedStatement ps;
	@Mock
	private CallableStatement cs;
	@Mock
	private ResultSet rs;
	
	private AccountDAOImpl aDao;
	
	private Account account;
	private List<Account> accountList;
	
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		aDao = new AccountDAOImpl(bankCon);
		//
		when(bankCon.getDBConnection()).thenReturn(con);
		when(con.prepareStatement(isA(String.class))).thenReturn(ps);
		when(con.prepareCall(isA(String.class))).thenReturn(cs);
		when(ps.executeQuery()).thenReturn(rs);
		when(ps.execute()).thenReturn(true);
		when(cs.execute()).thenReturn(true);
		when(cs.getString(isA(Integer.class))).thenReturn("Test Success");
		//
		account = new Account(1,"Swiss",2000);
		accountList = Arrays.asList(account);
		//
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt(1)).thenReturn(account.getAccountId());
		when(rs.getString(2)).thenReturn(account.getAccountType());
		when(rs.getDouble(3)).thenReturn(account.getBalance());
	}
	
	@Test
	public void getAccountByIdSuccess() {
		Account testAccount = aDao.getAccountById(1);
		assertEquals(account.getAccountId(),testAccount.getAccountId());
		assertEquals(account.getAccountType(),testAccount.getAccountType());
		assertEquals(account.getBalance(), testAccount.getBalance());
		
	}
	
	@Test
	public void insertAccountSuccess() throws SQLException {
		aDao.insertAccount(account, 12);
		verify(cs,times(1)).execute();
		
	}
	@Test
	public void getAllAccountsSuccess() {
		 List<Account> testlist = aDao.getAllAccounts();
		 assertEquals(accountList.size(), testlist.size());
	}
	@Test
	public void getAccountsByClientIdSuccess() {
		List<Account> testlist = aDao.getAccountsByClientId(1);
		 assertEquals(accountList.size(), testlist.size());
	}
	@Test
	public void updateAccountBalanceSuccess() throws SQLException {
		aDao.updateAccountBalance(account);
		verify(ps,times(1)).execute();
	}
	@Test
	public void changeAccountTypeForIdSuccess() throws SQLException {
		aDao.changeAccountTypeForId(1,"Savings");
		verify(ps,times(1)).execute();
		
		
	}
	@Test
	public void deleteAccountSuccess() throws SQLException {
		aDao.deleteAccount(1);
		verify(ps,times(1)).execute();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//end	
}
