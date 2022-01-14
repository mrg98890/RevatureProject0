package com.example.dao;

import java.util.List;

import com.example.model.Client;

public interface ClientDAO {
	
	boolean clientInsert(Client client);
	List<Client> getAllClients();
	Client getClientByClientName(String clientName);
	Client getClientById(int clientId);
	boolean deleteClientById(int clientId);
	void updateClientName(int clientId, String clientName);
	
	
}
