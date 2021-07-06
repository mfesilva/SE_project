package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;

public class Withdrawn extends State{
	
	@Override
	public void process(Operation wrapper) throws AccountException  {
		if (wrapper.getSibs().services.getAccountByIban(wrapper.getSourceIban()).getBalance() < wrapper.getValue()) {

			State pre_state = wrapper.getState();
			wrapper.setState(new Retry(pre_state));
			
		} else {
			wrapper.getSibs().services.deposit(wrapper.getTargetIban(), wrapper.getValue());
			wrapper.setState(new Deposited());
			
		}
	}

	@Override
	public void cancel(Operation wrapper) throws AccountException   {
			wrapper.getSibs().services.deposit(wrapper.getSourceIban(), wrapper.getValue());{}	
	}
}
