package com.example;

import com.example.controller.AccountController;
import com.example.controller.ClientController;

import io.javalin.Javalin;

public class BankDriver {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config ->{
			config.enableCorsForAllOrigins();
		});
		
		app.start(9001);
		
		
		//create client --#1--
		app.post("/clients", ClientController.CLIENTCREATE);
		//get all clients --#2--
		app.get("/clients", ClientController.CLIENTSGETALL);
		//get client by id --#3--
		app.get("/clients/:id", ClientController.CLIENTGET);
		//put, update client --#4--
		app.put("/clients/:id", ClientController.CLIENTUPDATENAMEBYID);
		//delete client by id --#5--
		app.delete("/clients/:id", ClientController.CLIENTDELETEID);
		// post clients id counts --#6-- create a new account for an id
		app.post("clients/:id/accounts", AccountController.ACCOUNTCREATEFORCLIENTID);//form param -->atype,deposit
		//get all accounts for a client by clientid--#7--
		app.get("clients/:id/accounts", AccountController.ALLACCOUNTSGETFORID);
		//get account for account id --#8--
		app.get("accounts/:id", AccountController.ACCOUNTFORACCOUNTID);
		// put update account type --#9--
		app.put("accounts/:id", AccountController.ACCOUNTTYPEUPDATE);
		//delete account by account id--#10--
		app.delete("accounts/:id", AccountController.ACCOUNTDELETEBYID);
		//patch depost --#11--
		app.patch("accounts/:id/deposit", AccountController.ACCOUNTDEPOSIT);
		//patch withdraw --#12--
		app.patch("accounts/:id/withdraw", AccountController.ACCOUNTWITHDRAW);
		
		
		app.error(404, (ctx)->{ 
			ctx.result("404, Resource Was not found");
		});
	}
	
}
