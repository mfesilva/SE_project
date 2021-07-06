package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;

public class Deposited extends State {

	@Override
	public void process(Operation wrapper) throws AccountException  {
		if (wrapper.getSibs().services.getAccountByIban(wrapper.getSourceIban()).getBalance() < wrapper.getValue()) {
			wrapper.setState(new Erro());

			
		} else {
			wrapper.getSibs().services.withdraw(wrapper.getSourceIban(), wrapper.commission());
			wrapper.setState(new Completed());
		}
	}

	@Override
	public void cancel(Operation wrapper) throws AccountException {
			wrapper.getSibs().services.withdraw(wrapper.getTargetIban(), wrapper.getValue());
			wrapper.getSibs().services.deposit(wrapper.getSourceIban(), wrapper.getValue());
	}
}
