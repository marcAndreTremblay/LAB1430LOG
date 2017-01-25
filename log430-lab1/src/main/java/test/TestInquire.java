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
	@Test
	public void test_inquire_checkings()
	{		
		Card testCard = new Card(1);	
		Money amountTest = new Money(0);
		
		Message m = new Message(Message.INQUIRY, testCard, 42, 2, 0, -1, amountTest);
		
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$100.00");
	}
	
	@Test
	public void test_inquire_savings()
	{	
		Card testCard = new Card(1);	
		Money amountTest = new Money(0);
		
		Message m = new Message(Message.INQUIRY, testCard, 42, 2, 1, -1, amountTest);
		
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$1000.00");
	}
	
	@Test
	public void test_inquire_moneyMarket()
	{	
		Card testCard = new Card(2);	
		Money amountTest = new Money(0);
		
		Message m = new Message(Message.INQUIRY, testCard, 1234, 2, 2, -1, amountTest);
		
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$5000.00");
	}
}
