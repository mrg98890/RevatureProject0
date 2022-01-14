package com.example.service;

import java.util.List;

import com.example.dao.ClientDAOImpl;
import com.example.model.Client;

public class ClientService {
	
	private ClientDAOImpl cDao;
	
	public ClientService() {
		
	}

	public ClientService(ClientDAOImpl cDao) {
		super();
		this.cDao = cDao;
	}
	
	public Client findClientByClientName(String clientName) {
		return cDao.getClientByClientName(clientName);
	}
	
	public Client findClientByClientId(int clientId) {
		return cDao.getClientById(clientId);
	}
	public boolean createClient(Client c) {
		return cDao.clientInsert(c);
	}
	public boolean removeClient (int id) {
		return cDao.deleteClientById(id);
	}
	public void updateClientNameById(int id, String c) {
		cDao.updateClientName(id, c);
	}
	public List<Client> showAllClients(){
		return cDao.getAllClients();
	}
	
	
	

}
