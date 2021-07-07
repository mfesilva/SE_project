package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.domain.TransferData;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;

public class Transfer_MBwayController {
	
	private MBway clients;
	private TransferData data;
	private Services services = new Services();
	
	public Transfer_MBwayController(TransferData data, MBway clients) {
		this.data = data;
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
