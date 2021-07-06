package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public abstract class State {
	
	
	public abstract void process(Operation wrapper) throws AccountException, OperationException;
	
	public abstract void cancel(Operation wrapper) throws AccountException;
}

