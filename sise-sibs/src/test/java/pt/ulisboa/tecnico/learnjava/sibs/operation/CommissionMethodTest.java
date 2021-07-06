package pt.ulisboa.tecnico.learnjava.sibs.operation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Operation;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class CommissionMethodTest {
	
	private static final String SOURCE_IBAN = "SourceIban";
	private static final String TARGET_IBAN = "TargetIban";
	private static final int VALUE = 100;
	private Sibs sibs;
	
	@Test
	public void commissionSucess() throws OperationException {
		this.sibs = new Sibs(3, new Services());
		Operation operation = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE, sibs);
		assertEquals(2, operation.commission());
	}
}
