package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class Operation {

	private final int value;
	private final String targetIban;
	private final String sourceIban;
	private final Sibs sibs;
	private State current;

	public Operation(String sourceIban, String targetIban, int value, Sibs sibs) throws OperationException {
		checkParameters(value);
		this.value = value;

		if (invalidString(targetIban)) {
			throw new OperationException();
		}
		if (invalidString(sourceIban)) {
			throw new OperationException();
		}

		this.targetIban = targetIban;
		this.sourceIban = sourceIban;
		this.sibs = sibs;
		
// Operation created has the state Registered
		current = new Registered();
	}
	
	public void setState(State state) {
		current = state;
	}
	
	public State getState() {
		return this.current;
	}
	
	public Sibs getSibs() {
		return sibs;
	}
	
	public void process() throws OperationException, AccountException {
		current.process(this);
	}
	
	public void cancel() throws AccountException {
			current.cancel(this);
	}
	
	private void checkParameters(int value) throws OperationException {
		if (value <= 0) {
			throw new OperationException(value);
		}
	}

	public int getValue() {
		return this.value;
	}

	private boolean invalidString(String name) {
		return name == null || name.length() == 0;
	}

	public int commission() {
		return (int) (this.value*0.02);
	}

	public String getTargetIban() {
		return this.targetIban;
	}
	
	public String getSourceIban() {
		return this.sourceIban;
	}

}
