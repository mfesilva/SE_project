  package pt.ulisboa.tecnico.learnjava.sibs.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Operation;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Registered;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class OperationConstructorMethodTest {
	private static final String SOURCE_IBAN = "SourceIban";
	private static final String TARGET_IBAN = "TargetIban";
	private static final int VALUE = 100;
	private Sibs sibs;

	@Test
	public void success() throws OperationException {
		this.sibs = new Sibs(3, new Services());
		Operation operation = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE, sibs);

		assertEquals(VALUE, operation.getValue());
		assertEquals(SOURCE_IBAN, operation.getSourceIban());
		assertEquals(TARGET_IBAN, operation.getTargetIban());
	}

	@Test(expected = OperationException.class)
	public void nullSourceIban() throws OperationException {
			new Operation(null, TARGET_IBAN, VALUE, sibs);

	}

	@Test(expected = OperationException.class)
	public void emptyTargetIban() throws OperationException {
		new Operation(SOURCE_IBAN, "", VALUE, sibs);
	}

	@Test
	public void checkParameterSucess() throws OperationException {
		Operation operation = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE, sibs);
		assertEquals(100, operation.getValue());
	}
	
	@Test(expected = OperationException.class)
	public void checkParameterNegative() throws OperationException {
		new Operation(SOURCE_IBAN, TARGET_IBAN, -1, sibs);
	}
	
	@Test // Operation created has the state Registered
	public void registeredTrue() throws OperationException {
		Operation operation = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE, sibs);
		assertTrue(operation.getState() instanceof Registered); 
	}
		
	
}
