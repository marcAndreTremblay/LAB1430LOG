package test;

import static org.junit.Assert.*;

import edu.gordon.banking.Balances;
import edu.gordon.banking.Card;
import edu.gordon.banking.Message;
import edu.gordon.banking.Money;
import edu.gordon.simulation.SimulatedBank;
import org.junit.Test;

public class TestTransfer 
{
	@Test
	public void test_transfer_checkingsToSavings()
	{		
		Card testCard = new Card(1);	
		Money amountTest = new Money(20);
		Money moneyBalanceTest = new Money(10000);
		
		Message m = new Message(3, testCard, 42, 2, 0, 1, amountTest);
		
		Balances b = new Balances();
		b.setBalances(moneyBalanceTest, moneyBalanceTest);

		SimulatedBank sb = new SimulatedBank();
		sb.handleMessage(m, b);
		
		assertEquals(b.getAvailable().toString(), "$1020.00");
	}
}
