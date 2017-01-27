package test;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.gordon.banking.Balances;
import edu.gordon.banking.Card;
import edu.gordon.banking.Message;
import edu.gordon.banking.Money;
import edu.gordon.simulation.SimulatedBank;

public class TestInquire 
{
	//MAGIC NUMBERS
	public static final int ACCOUNT_CHECKINGS = 0;
	public static final int ACCOUNT_SAVINGS = 1;
	public static final int ACCOUNT_MONEYMARKET = 2;
	public static final int PIN_CARD1 = 42;
	public static final int PIN_CARD2 = 1234;
	public static final int SERIALNUMBER = 2;
	
	
	@Test
	public void test_inquire_checkings()
	{		
		Message m = new Message(Message.INQUIRY, new Card(1), PIN_CARD1, SERIALNUMBER, ACCOUNT_CHECKINGS, -1, new Money(0));		
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$100.00");
	}
	
	@Test
	public void test_inquire_savings()
	{	
		Message m = new Message(Message.INQUIRY, new Card(1), PIN_CARD1, SERIALNUMBER, ACCOUNT_SAVINGS, -1, new Money(0));		
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$1000.00");
	}
	
	@Test
	public void test_inquire_moneyMarket()
	{	
		Message m = new Message(Message.INQUIRY, new Card(2), PIN_CARD2, SERIALNUMBER, ACCOUNT_MONEYMARKET, -1, new Money(0));	
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$5000.00");
	}
}
