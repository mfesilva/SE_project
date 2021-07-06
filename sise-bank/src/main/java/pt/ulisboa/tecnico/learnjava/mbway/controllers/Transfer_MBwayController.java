package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;

public class Transfer_MBwayController {

	private MBway clients;
	private String source_phone;
	private String target_phone;
	private Integer amount;
	private Services services = new Services();
	
	public Transfer_MBwayController(String source_phone, String target_phone, Integer amount, MBway clients) {
		this.source_phone = source_phone;
		this.target_phone = target_phone;
		this.amount = amount;
		this.clients = clients;
	}
	
	public int transfer() throws AccountException  {
		if (clients.containsMbwayClient(target_phone) == false) {
			return 1;
		} else if (services.getAccountByIban(clients.getMbwayClient(source_phone).getIban()).getBalance() < amount) {
			return 2;
		} else {
			services.getAccountByIban(clients.getMbwayClient(source_phone).getIban()).withdraw(amount);
			return 0;
		}		
	}
}
