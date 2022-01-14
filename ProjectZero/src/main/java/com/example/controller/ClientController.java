package com.example.controller;

import java.util.Arrays;
import java.util.List;

import com.example.dao.ClientDAOImpl;
import com.example.dao.BankDataBaseConnection;
import com.example.model.Account;
import com.example.model.Client;
import com.example.service.ClientService;

import io.javalin.http.Handler;

public class ClientController {
	
	private static ClientService cServ = new ClientService(new ClientDAOImpl(new BankDataBaseConnection()));
	
	
	// CLIENT CREATE --#1--
	public static final Handler CLIENTCREATE = (ctx) -> {
		String c =ctx.formParam("cname");
		Client client = new Client(c);
		boolean statesuccess = cServ.createClient(client);
		if(statesuccess) {
			ctx.status(201);
		}else {
			ctx.status(418);
		}
		
	};
	// CLIENTS GET ALL --#2--
	public static final Handler CLIENTSGETALL = (ctx) -> {
		List<Client> clients = cServ.showAllClients();
		ctx.json(clients);
	};
	
	//get client by id --#3--
		public static final Handler CLIENTGET = (ctx) ->{
			int id = Integer.parseInt(ctx.pathParam("id"));
			System.out.println(id);
			Client client = cServ.findClientByClientId(id);
			if(client.getClientName() == null) {
				ctx.status(404);
			}else {
				ctx.json(client.getClientName()); 
			}
		};
	// CLIENTUPDATENAMEBYID --#4--
		
		public static final Handler CLIENTUPDATENAMEBYID = (ctx) ->{
			int id = Integer.parseInt(ctx.pathParam("id"));
			String c =ctx.formParam("cname");
			Client client = cServ.findClientByClientId(id);
			if(client.getClientName() == null) {
				ctx.status(404);
			}else {
				cServ.updateClientNameById(id,c); 
			}
		};
		
		
	//CLIENTDELETEID --#5--
	
	public static final Handler CLIENTDELETEID =(ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client client = cServ.findClientByClientId(id);
		if(client.getClientName() == null) {
			ctx.status(400);
		}else {
			boolean success = cServ.removeClient(id);
			if(success) {
				ctx.status(205);
			}else {
				ctx.status(418);
			}
		}
	};
	
	
	
//	
}
