package pt.ulisboa.tecnico.learnjava.mbway.domain;

public class MBwayClient {
	
	private String iban;
    private String phone;
    private int code;
    

	public MBwayClient(String iban, String phone, int code) {
		this.iban = iban;
    	this.phone = phone;
    	this.code = code;
    	}
    
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
