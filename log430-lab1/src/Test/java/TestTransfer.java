

import static org.junit.Assert.*;
import edu.gordon.banking.Balances;
import edu.gordon.banking.Card;
import edu.gordon.banking.Message;
import edu.gordon.banking.Money;
import edu.gordon.simulation.SimulatedBank;
import org.junit.Test;

public class TestTransfer 
{
	//MAGIC NUMBERS
	public static final int ACCOUNT_CHECKINGS = 0;
	public static final int ACCOUNT_SAVINGS = 1;
	public static final int ACCOUNT_MONEYMARKET = 2;
	public static final int PIN_CARD1 = 42;
	public static final int PIN_CARD2 = 1234;
	public static final int SERIALNUMBER = 2;
	
	@Test
	public void test_transfer_checkingsToSavings()
	{		
		//Checks that money is money is transfered to savings
		Message m = new Message(Message.TRANSFER, new Card(1), PIN_CARD1, SERIALNUMBER, ACCOUNT_CHECKINGS, ACCOUNT_SAVINGS, new Money(10));		
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$1010.00");
		
		//checks that money transfered was in fact from checkings
		Message m2 = new Message(Message.INQUIRY, new Card(1), PIN_CARD1, SERIALNUMBER, ACCOUNT_CHECKINGS, -1, new Money(0));
		sb.handleMessage(m2, b);
		
		assertEquals(b.getAvailable().toString(), "$90.00");
	}
	
	@Test
	public void test_transfer_savingsToCheckings()
	{	
		//Checks that money is transfered to checkings
		Message m = new Message(Message.TRANSFER, new Card(1), PIN_CARD1, SERIALNUMBER, ACCOUNT_SAVINGS, ACCOUNT_CHECKINGS, new Money(40));		
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$140.00");
		
		//checks that money transfered was in fact from savings
		Message m2 = new Message(Message.INQUIRY, new Card(1), PIN_CARD1, SERIALNUMBER, ACCOUNT_SAVINGS, -1, new Money(0));
		sb.handleMessage(m2, b);
		
		assertEquals(b.getAvailable().toString(), "$960.00");
	}
	
	@Test
	public void test_transfer_MoneyMarketToCheckings()
	{
		//Checks that money is transfered to checkings
		Message m = new Message(Message.TRANSFER, new Card(2), PIN_CARD2, SERIALNUMBER, ACCOUNT_MONEYMARKET, ACCOUNT_CHECKINGS, new Money(30));	
		Balances b = new Balances();

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$130.00");
		
		//checks that money transfered was in fact from money market
		Message m2 = new Message(Message.INQUIRY, new Card(2), PIN_CARD2, SERIALNUMBER, ACCOUNT_MONEYMARKET, -1, new Money(0));
		sb.handleMessage(m2, b);
		
		assertEquals(b.getAvailable().toString(), "$4970.00");
	}
	
	@Test
	public void test_transfer_checkingsToMoneyMarket()
	{	
		//Checks that money is transfered to money market
		Message m = new Message(Message.TRANSFER, new Card(2), PIN_CARD2, SERIALNUMBER, ACCOUNT_CHECKINGS, ACCOUNT_MONEYMARKET, new Money(100));
		Balances b = new Balances();
		
		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$5100.00");
		
		//checks that money transfered was in fact from checkings
		Message m2 = new Message(Message.INQUIRY, new Card(2), PIN_CARD2, SERIALNUMBER, ACCOUNT_CHECKINGS, -1, new Money(0));
		sb.handleMessage(m2, b);
		
		assertEquals(b.getAvailable().toString(), "$0.00");
	}
}
