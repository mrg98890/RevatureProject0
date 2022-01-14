package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankDataBaseConnection {
	
	private static final String URL = "jdbc:postgresql://rossnet.cesehnwtmcrr.us-east-2.rds.amazonaws.com:5432/postgres";
	private static final String username ="Michael";
	private static final String password = "Funfun-0811";
	
	public Connection getDBConnection() throws SQLException {
		return DriverManager.getConnection(URL, username,password);
	}

}
