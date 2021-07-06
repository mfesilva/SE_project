package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class Sibs {
	final Operation[] operations;
	Services services;

	public Sibs(int maxNumberOfOperations, Services services) {
		this.operations = new Operation[maxNumberOfOperations];
		this.services = services;
	}

	public void transfer(String sourceIban, String targetIban, int amount)
			throws SibsException, AccountException, OperationException {
			
		if (targetIban == null || targetIban.length() == 0) { 
			throw new OperationException();
		}
		else if (sourceIban == null || sourceIban.length() == 0) {
			throw new OperationException();
		}

		this.services.withdraw(sourceIban, amount);
		this.services.deposit(targetIban, amount);
		addOperation(sourceIban, targetIban, amount);
		
	}

	public int addOperation(String sourceIban, String targetIban, int value)
			throws OperationException, SibsException {
		int position = -1;
		for (int i = 0; i < this.operations.length; i++) {
			if (this.operations[i] == null) {
				position = i;
				break;
			}
		} 

		if (position == -1) {
			throw new SibsException();
		}

		Operation operation = new Operation(sourceIban, targetIban, value, this);

		this.operations[position] = operation;
		return position;
	}

	public void removeOperation(int position) throws SibsException {
		if (position < 0 || position > this.operations.length) {
			throw new SibsException();
		}
		this.operations[position] = null;
	}

	public Operation getOperation(int position) throws SibsException {
		if (position < 0 || position > this.operations.length) {
			throw new SibsException();
		}
		return this.operations[position];
	}

//-------------------------Project Part 1-------------------------
	
	 public int numberOfOperations() {
		 int count = 0;
		 for (Operation e : operations) {
			 if (e != null) {
				 count ++;
			 }
		 }
		 return count;
	 }
	 public int getNumberOfOperations() {
		 return numberOfOperations();
		 }
	  
	 public int totalValueOfOperations() {
		 int novo = 0;
		 for (Operation e : operations) {
			 if (e != null) {
			 novo = e.getValue() + novo;
			 }
		 }
		 return novo;
	 }
	 
	 public int getTotalValueOperation() {
		 return totalValueOfOperations();
		 }
	 
	 
//-------------------------Project Part 2-------------------------
	 
	 public void transfer_improved(String SourceIBAN, String TargetIBAN, int amount) throws OperationException, SibsException {
			addOperation(SourceIBAN, TargetIBAN, amount);
	 }
	 
	 public void processOperations() throws OperationException, AccountException {
		 for (Operation e : operations) {
			 if (e == null) {
				 throw new OperationException();
			  }
			e.process();	
		 }
	 }
	 
	 public void cancelOperation(int i) throws AccountException {
		 Operation o = operations[i];
		 o.cancel();
	 }
	 
}