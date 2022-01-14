package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Account;
import com.example.model.Client;

public class ClientDAOImpl implements ClientDAO {
	
	private BankDataBaseConnection bankCon;
	
	public ClientDAOImpl() {
		
	}

	public ClientDAOImpl(BankDataBaseConnection bankCon) {
		super();
		this.bankCon = bankCon;
	}

	@Override
	public boolean clientInsert(Client client) {
		
		try(Connection con = bankCon.getDBConnection()){
			
			String sql = "{? = call insert_client(?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, client.getClientName());
			cs.execute();
			boolean success = Boolean.parseBoolean(cs.getString(1));
			return success;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	@Override 
	public List<Client> getAllClients() {
		List<Client> clientList = new ArrayList<>();
		
		try(Connection con = bankCon.getDBConnection()){
		
			String sql = "select * from client";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				clientList.add(new Client(rs.getInt(1), rs.getString(2), null));
			}

			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return clientList;
	}

	@Override
	public Client getClientByClientName(String clientName) {
		
		try(Connection con = bankCon.getDBConnection()){
			
			String sql = "select * from client c left outer join account a on c.clientid = a.clientid where c.clientname=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, clientName);
			ResultSet rs = ps.executeQuery();
			Client client = new Client();
			List<Account> accountList = new ArrayList<>();
			
			while(rs.next()) {
				client = new Client(rs.getInt(1), rs.getString(2), null);
				accountList.add(new Account(rs.getInt(3), rs.getString(4), rs.getDouble(5)));
			}
			client.setAccountList(accountList);
			return client;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Client getClientById(int clientId) {
		try(Connection con = bankCon.getDBConnection()){
			
			String sql = "select * from client c left outer join account a  on c.clientid = a.clientid where c.clientid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();
			Client client = new Client();
			List<Account> accountList = new ArrayList<>();
			
			while(rs.next()) {
				client = new Client(rs.getInt(1), rs.getString(2), null);
				accountList.add(new Account(rs.getInt(3), rs.getString(4), rs.getDouble(5)));
			}
			client.setAccountList(accountList);
			return client;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void updateClientName(int clientId, String clientName) {
try(Connection con = bankCon.getDBConnection()){
			
			String sql = "update client set clientname=? where clientid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, clientName);
			ps.setInt(2, clientId);
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean deleteClientById(int clientId) {
		try(Connection con = bankCon.getDBConnection()){
			String sql = "delete from client where clientid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, clientId);
			ps.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

}
