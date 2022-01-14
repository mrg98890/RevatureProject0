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

import com.example.dao.ClientDAOImpl;
import com.example.dao.AccountDAOImpl;
import com.example.dao.BankDataBaseConnection;
import com.example.model.Account;
import com.example.model.Client;


public class CientDAOTest {
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
	private ClientDAOImpl cDao;
	
	private Client client;
	private List<Client> clientList;
	
	
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		cDao = new ClientDAOImpl(bankCon);
		
		//
		when(bankCon.getDBConnection()).thenReturn(con);
		when(con.prepareStatement(isA(String.class))).thenReturn(ps);
		when(con.prepareCall(isA(String.class))).thenReturn(cs);
		when(ps.executeQuery()).thenReturn(rs);
		when(ps.execute()).thenReturn(true);
		when(cs.execute()).thenReturn(true);
		when(cs.getString(isA(Integer.class))).thenReturn("Test Success");
		client = new Client(1,"Michael Grange",null);
		clientList = Arrays.asList(client);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt(1)).thenReturn(client.getClientId());
		when(rs.getString(2)).thenReturn(client.getClientName());
		
		
	}
	
	@Test
	public void clientInsertSuccess() throws SQLException {
		cDao.clientInsert(client);
		verify(cs,times(1)).execute();
	}
	@Test
	public void getAllClientsSuccess() throws SQLException {
		List<Client> testlist =cDao.getAllClients();
		assertEquals(clientList.size(),testlist.size());
		
	}
	@Test
	public void getClientById() {
		Client testclient = cDao.getClientById(1);
		assertEquals(client.getClientName(),testclient.getClientName());
	}
	@Test
	public void getClientByClientName() {
		Client testclient = cDao.getClientByClientName("Michael Grange");
;		assertEquals(client.getClientId(),testclient.getClientId());
	}
	@Test
	public void updateClientName() throws SQLException {
		cDao.updateClientName(1, "Michael");
		verify(ps,times(1)).execute();
	}
	
	@Test
	public void deleteClientById() throws SQLException {
		cDao.deleteClientById(1);
		verify(ps,times(1)).execute();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
