package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class Retry extends State {
	
	private State state;
	private int count = 2;


	public Retry(State pre_state) {
		this.state = pre_state;

	}
	@Override
	public void process(Operation wrapper) throws OperationException, AccountException {
	
		if (count == 0) { 
			wrapper.setState(new Erro());
		} else {
			count--;
			wrapper.setState(this.state);
			wrapper.getState().process(wrapper);
			if (wrapper.getState() instanceof Retry) {
				wrapper.setState(this);
			}
		}
			
}

	@Override
	public void cancel(Operation wrapper) {
		
	}

}
