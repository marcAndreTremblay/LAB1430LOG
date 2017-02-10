package edu.gordon.simulation;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
		
	public void register(Observer obj);	
	public void notifyObservers(Boolean state, Boolean cardInserted);
	public void notifyObserversTransaction();

}
