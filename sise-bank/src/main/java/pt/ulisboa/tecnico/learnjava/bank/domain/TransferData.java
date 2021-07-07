package pt.ulisboa.tecnico.learnjava.bank.domain;

public class TransferData {
	
	private String source_phone;
	private String target_phone;
	private Integer amount;
	
	public TransferData(String source_phone, String target_phone, Integer amount) {
		this.source_phone = source_phone;
		this.target_phone = target_phone;
		this.amount = amount;
		
	}
	public String getSource_phone() {
		return source_phone;
	}

	public void setSource_phone(String source_phone) {
		this.source_phone = source_phone;
	}

	public String getTarget_phone() {
		return target_phone;
	}

	public void setTarget_phone(String target_phone) {
		this.target_phone = target_phone;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
