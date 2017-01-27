
import static org.junit.Assert.*;

import java.net.InetAddress;

import org.junit.Test;

import edu.gordon.atm.*;
import edu.gordon.banking.Balances;
import edu.gordon.banking.Card;
import edu.gordon.banking.Message;
import edu.gordon.banking.Money;
import edu.gordon.simulation.SimulatedBank;
import edu.gordon.simulation.Simulation;

public class TestCase {

	@Test
	public void MoneyAddTest() {
		Money sut = new Money(10,20);
		Money test_amount = new Money(50,20);
		
		sut.add(test_amount);
		
		assertTrue(sut.Equal(new Money(60,40))==true);
	}
	@Test
	public void BalanceAvailabilityTest() {
		Balances b1 = new Balances();
			b1.setBalances(new Money(10), new Money(0));
		assertTrue(b1.getAvailable().Equal(new Money(10)) == false);
		assertTrue(b1.getAvailable().Equal(new Money(0)) == true);
	}
	@Test
	public void BalanceTotalTest() {
		Balances b1 = new Balances();
			b1.setBalances(new Money(10), new Money(0));
		assertTrue(b1.getTotal().Equal(new Money(10)) == true);
		assertTrue(b1.getTotal().Equal(new Money(0)) == false);
	}
	@Test
	public void TransactionDepossitATMAndSimulation() {
		 ATM theATM = new ATM(42, "Gordon College", "First National Bank of Podunk",
                 null /* We're not really talking to a bank! */);
		 Simulation theSimulation = new Simulation(theATM);
		 Card testCard = new Card(1);
		 Money deposit_test = new Money(20,30);
		 Message m1 = new Message(Message.INITIATE_DEPOSIT, testCard, 42, 2, -1, 1, deposit_test);
		Message m2 = new Message(Message.COMPLETE_DEPOSIT, testCard, 42, 2, -1, 1, deposit_test);
		
		Balances b1 = new Balances();
		 theSimulation.sendMessage(m1, null);
		 theSimulation.sendMessage(m2, b1);
		 
		 
		 assertTrue(b1.getTotal().Equal(new Money(1020,30)) == true);
	}
	@Test
	public void TransactionWitdrawalATMAndSimulation() {
		 ATM theATM = new ATM(42, "Gordon College", "First National Bank of Podunk",
                 null /* We're not really talking to a bank! */);
		 Simulation theSimulation = new Simulation(theATM);
		 Card testCard = new Card(1);
		 Money removed_amount = new Money(20);
		 Message m1 = new Message(Message.WITHDRAWAL, testCard, 42, 2, 1, -1, removed_amount);
			
			Balances b1 = new Balances();
		 theSimulation.sendMessage(m1, b1);
		 
		 
		 assertTrue(b1.getTotal().Equal(new Money(980)) == true);
	}
	@Test
	public void TransactionDepossitSimulatedBank 	() {
		SimulatedBank sb = new SimulatedBank();
		Card testCard = new Card(1);
		
		
		Money deposit_test = new Money(20,30);
		
		Message m1 = new Message(Message.INITIATE_DEPOSIT, testCard, 42, 2, -1, 1, deposit_test);
		Message m2 = new Message(Message.COMPLETE_DEPOSIT, testCard, 42, 2, -1, 1, deposit_test);
		
		Balances b1 = new Balances();
			

		sb.handleMessage(m1, null);
		sb.handleMessage(m2, b1);
		
		
		assertTrue(b1.getTotal().Equal(new Money(1020,30)) == true);
	}
	@Test
	public void TransactionWitdrawal() {
		SimulatedBank sb = new SimulatedBank();
		Card testCard = new Card(1);
		
		
		Money removed_amount = new Money(20);
		
		Message m1 = new Message(Message.WITHDRAWAL, testCard, 42, 2, 1, -1, removed_amount);
		
		Balances b1 = new Balances();
	
		sb.handleMessage(m1, b1);
		
		
		assertTrue(b1.getTotal().Equal(new Money(980)) == true);
	}

}
