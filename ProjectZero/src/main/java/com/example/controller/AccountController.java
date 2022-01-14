package com.example.controller;

import com.example.dao.AccountDAOImpl;
import com.example.dao.ClientDAOImpl;
import com.example.dao.BankDataBaseConnection;
import com.example.model.Account;
import com.example.model.Client;
import com.example.service.AccountService;
import com.example.service.ClientService;

import io.javalin.http.Handler;

public class AccountController {
	
	private static ClientService cServ = new ClientService(new ClientDAOImpl(new BankDataBaseConnection()));
	private static AccountService aServ = new AccountService(new AccountDAOImpl(new BankDataBaseConnection()));
	
	//ACCOUNTCREATEBYID--#6--
		public static final Handler ACCOUNTCREATEFORCLIENTID = (ctx) -> {
			int id = Integer.parseInt(ctx.pathParam("id"));
			String at =ctx.formParam("atype");
			double bal = Double.parseDouble(ctx.formParam("deposit"));
			Account account = new Account(at,bal);
			boolean statesuccess = aServ.createAccount(account,id);
			if(statesuccess) {
				ctx.status(201);
			}else {
				ctx.status(418);
			}
		};
	//ALLACCOUNTSGETFORID --#7--
		public static final Handler ALLACCOUNTSGETFORID = (ctx) ->{
			int id = Integer.parseInt(ctx.pathParam("id"));
			System.out.println(id);
			Client client = cServ.findClientByClientId(id);
			if(client.getClientName() == null) {
				ctx.status(404);
			}else {
				ctx.json(client); 
			}
		};
	//ACCOUNTFORACCOUNTID --#8--
		public static final Handler ACCOUNTFORACCOUNTID = (ctx) -> {
			
			int aId = Integer.parseInt(ctx.pathParam("id"));
			Account account = aServ.retrieveAccountForId(aId);
			if(account.getAccountType()==null) {
				ctx.status(404);
			}else {
				ctx.status(200);
				ctx.json(account);
			}
			
		};
	//ACCOUNTTYPEUPDATE --#9--
		public static final Handler ACCOUNTTYPEUPDATE = (ctx) -> {
			int aId = Integer.parseInt(ctx.pathParam("id"));
			String at =ctx.formParam("atype");
			Account account = aServ.findAccountByAccountId(aId);
			if(account.getAccountType() == null) {
				ctx.status(404);
			}else {
				aServ.updateAccountTypeById(aId,at); 
			}
		};
		
	//ACCOUNTDELETEBYID --#10--
		public static final Handler ACCOUNTDELETEBYID = (ctx) -> {
			int id = Integer.parseInt(ctx.pathParam("id"));
			Account account = aServ.findAccountByAccountId(id);
			if(account.getAccountType() == null) {
				ctx.status(400);
			}else {
				boolean success = aServ.removeAccount(id);
				if(success) {
					ctx.status(205);
				}else {
					ctx.status(418);
				}
			}
		};
		
	//ACCOUNTDEPOSIT--#11--
		public static final Handler ACCOUNTDEPOSIT = (ctx) -> {
			int id = Integer.parseInt(ctx.pathParam("id"));
			Account account = aServ.findAccountByAccountId(id);
			if(account.getAccountType() == null) {
				ctx.status(404);
			}else {
				aServ.addFunds(id, Double.parseDouble(ctx.formParam("deposit"))); 
			}
		};
		
	//AccountWithdraw--#12--
		public static final Handler ACCOUNTWITHDRAW = (ctx) -> {
			int id = Integer.parseInt(ctx.pathParam("id"));
			Account account = aServ.findAccountByAccountId(id);
			if(account.getAccountType() == null) {
				ctx.status(404);
			}else { if (account.getBalance()<Double.parseDouble(ctx.formParam("withdraw"))) {
				ctx.status(422);
			 }else {
				 aServ.subtractFunds(id, Double.parseDouble(ctx.formParam("withdraw"))); 
				 
			 }
			}
		};
	


}