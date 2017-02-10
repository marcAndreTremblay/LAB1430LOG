package edu.gordon.simulation;

import edu.gordon.atm.ATM;

public interface Observer {

	public void update(Boolean state, Boolean cardInserted);
	public void update();
	
}
