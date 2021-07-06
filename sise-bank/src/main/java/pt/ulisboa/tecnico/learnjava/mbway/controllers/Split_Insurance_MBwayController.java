package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import java.util.HashMap;
import java.util.Map;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;

public class Split_Insurance_MBwayController {

	private Integer number_fam_member;
	private Integer amount;
	private Integer count = 0;
	private MBway clients;
	private HashMap<String, Integer> friends;  
	private Services services = new Services();
	
	public Split_Insurance_MBwayController(Integer number_fam_member, Integer amount, MBway clients) {
		this.number_fam_member = number_fam_member;
		this.amount = amount;
		this.clients = clients;
		this.friends = new HashMap<String, Integer>();
	}

	public boolean confirmFriend(String phone) {
//		System.out.println("tou no confirm do controlador");
		if (clients.containsMbwayClient(phone)) { 
			return true;
		} else {
			return false;
		}
	}
	public void addFriend(String phone, int amount) {
		this.friends.put(phone, amount);
	}
	
	public int confirmMembersNumber() {
		if (friends.size() < this.number_fam_member) {
			return 0;
		} else if (friends.size() > this.number_fam_member) {
			return 1;
		}
		return 2;
	}
	
	public boolean confirmInsuranceValue() {  // 1) the total insurance value has to be the sum of the amounts paid by each family member
		for (Map.Entry<String, Integer> entry : friends.entrySet()) {
			this.count += entry.getValue();
		}
		return this.count == this.amount;
	}
	
	
	public boolean payInsurance() throws AccountException {
		
		for (Map.Entry<String, Integer> entry : friends.entrySet()) {  // 3) all family members must to have sufficient money in their bank accounts
			
			if (services.getAccountByIban(clients.getMbwayClient(entry.getKey()).getIban()).getBalance() < entry.getValue()) {
				return false;
			} 
			else {
			services.getAccountByIban(clients.getMbwayClient(entry.getKey()).getIban()).withdraw(entry.getValue());	
			}
		}
		return true;
		}
}
