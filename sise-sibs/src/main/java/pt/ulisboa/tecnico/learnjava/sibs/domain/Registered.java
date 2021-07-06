package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;


public class Registered extends State {
	
	@Override
	public void process(Operation wrapper) throws AccountException, OperationException  {
		if (wrapper.getSibs().services.getAccountByIban(wrapper.getSourceIban()).getBalance() < wrapper.getValue()) {

			State pre_state = wrapper.getState();
			wrapper.setState(new Retry(pre_state));

			
		} else {
			wrapper.getSibs().services.withdraw(wrapper.getSourceIban(), wrapper.getValue());
			wrapper.setState(new Withdrawn());
	}
}
		
	@Override
	public void cancel(Operation wrapper) {
	}

}

