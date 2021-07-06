package pt.ulisboa.tecnico.learnjava.mbway.cli;


import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBway;


public class Main {
	public static void main(String[] args) throws AccountException, BankException, ClientException {
		Services services = new Services();
		Bank bank1 = new Bank("CGD");
		Bank bank2 = new Bank("BPI");
		Client client1 = new Client(bank1, "Maria", "Soares", "123456789", "912346987", "Rua Alves Redol", 25);
		Client client2 = new Client(bank2, "Clara", "Rodrigues", "987654321", "917895234", "Rua Redol", 30);
		Client client3 = new Client(bank2, "Marco", "Andrade", "345123678", "967896734", "Rua da Figueira", 40);
		
		String iban1 = bank1.createAccount(AccountType.CHECKING, client1, 1000, 0); //associate-mbway CGDCK1 912346987
		String iban2 = bank2.createAccount(AccountType.CHECKING, client2, 5000, 0); //associate-mbway BPICK2 917895234
		String iban3 = bank2.createAccount(AccountType.CHECKING, client3, 2500, 0); //associate-mbway BPICK3 967896734
		
		//associate-mbway CGDCK1 912346987
		//associate-mbway BPICK2 917895234
		//associate-mbway BPICK3 967896734
		//mbway-transfer 912346987 917895234 50
		//mbway-transfer 967896734 917895234 50
		
		//mbway-split-insurance 3 100
		//friend 912346987 50
		//friend 917895234 25
		//friend 967896734 25
		
		MBway clients = new MBway();
		MBwayView view = new MBwayView(clients);
		view.FromUser();
   
    }
}
