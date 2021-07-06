package pt.ulisboa.tecnico.learnjava.mbway.domain;

import java.util.HashMap;

public class MBway {

	private HashMap<String, MBwayClient> clients;
    
    public MBway () {
    	this.clients = new HashMap<String, MBwayClient>();
    }
    
	public void addMbwayClient(String phone, MBwayClient mbway) {
		this.clients.put(phone, mbway);
	}
	
	public MBwayClient getMbwayClient(String phone) {
		return this.clients.get(phone);
	}
	
	public boolean containsMbwayClient(String phone) {
		if (clients.containsKey(phone)) {
			return true;
		} else {
			return false;
		}
	}
	public int getSize() {
		return this.clients.size();	
		}
	
}
