package com.example.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Account;

public class AccountDAOImpl implements AccountDAO {
	
	
	private BankDataBaseConnection bankCon;
	
	public AccountDAOImpl() {
	}
	
	public AccountDAOImpl(BankDataBaseConnection bankCon) {
		super();
		this.bankCon = bankCon;
	}

	@Override 
	public boolean insertAccount(Account account, int clientId) {
try(Connection con = bankCon.getDBConnection()){
			
			String sql = "{? = call insert_account(?,?,?)}";
			
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, account.getAccountType());
			cs.setBigDecimal(3, BigDecimal.valueOf(account.getBalance()));
			cs.setInt(4, clientId);
			cs.execute();
			boolean success = Boolean.parseBoolean(cs.getString(1));
			System.out.println(cs.getString(1)); 
			return success;		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> getAllAccounts() {
		
		List<Account> accountList = new ArrayList<>();
		
		try(Connection con = bankCon.getDBConnection()){
			
			String sql = "select * from card";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				accountList.add(new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return accountList;
	}

	@Override
	public List<Account> getAccountsByClientId(int clientId) {
		List<Account> accountList = new ArrayList<>();
		
		try(Connection con = bankCon.getDBConnection()){
			
			String sql = "select * from card where walletid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				accountList.add(new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return accountList;
	}

	@Override
	public Account getAccountById(int accountId) {
		
		Account account = new Account();
		
		try(Connection con = bankCon.getDBConnection()){
			
			String sql = "select * from account where accountid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				account = new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return account;
		
	}

	@Override
	public void updateAccountBalance(Account account) {
		
		try(Connection con = bankCon.getDBConnection()){
			
			String sql = "update account set balance=? where accountid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getAccountId());
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void changeAccountTypeForId(int accountId, String atype) {
		try(Connection con = bankCon.getDBConnection()){
					
					String sql = "update account set account_type=? where accountid=?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, atype);
					ps.setInt(2, accountId);
					ps.execute();
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
	@Override
	public boolean deleteAccount(int id) {
		try(Connection con = bankCon.getDBConnection()){
			String sql = "delete from account where accountid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}