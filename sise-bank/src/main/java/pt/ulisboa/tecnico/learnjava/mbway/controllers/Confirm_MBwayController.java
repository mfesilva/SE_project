package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBwayClient;

public class Confirm_MBwayController {
	
	public String phone;
	private MBwayClient client;
    private MBway clients;

    
    public Confirm_MBwayController(String phone, int code, MBway clients) {
        this.phone = phone;
    	this.clients = clients;
    }

    public int confirmMBway() throws AccountException {
    	if (clients.containsMbwayClient(phone)) {
    		this.client = clients.getMbwayClient(phone);
    		return client.getCode();
    	} else {
    		throw new AccountException();
    	}
    }
}
