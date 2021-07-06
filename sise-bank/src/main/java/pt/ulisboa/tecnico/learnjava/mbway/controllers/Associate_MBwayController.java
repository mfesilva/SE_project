package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import java.util.Random;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBwayClient;

public class Associate_MBwayController {
	private int code;
	private String iban;
	private String phone;
    private MBway clients; 
    private Services services = new Services();

    
    public Associate_MBwayController(String iban, String phone, MBway clients) {
    	this.iban = iban;
    	this.phone = phone;	
        this.clients = clients;
    }

    public int setMBway() throws AccountException {

    	if (services.getAccountByIban(iban).isInactive() == true || services.getAccountByIban(iban) == null || iban == "") {
    		throw new AccountException();
    	} else { 
    		this.code = new Random().nextInt(10000000);	
    		MBwayClient mbway = new MBwayClient(iban, phone, code);
    		clients.addMbwayClient(this.phone, mbway);
    		return this.code;	
    	}
    }

}
