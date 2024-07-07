package com.kurtulussahin.java.ibtechtasks.tasks.operation;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;

public interface CrudOperations {
	
	Bag add(Bag bag);
	
	Bag update(Bag bag);
	
	Bag list();
	
	Bag delete(Bag bag);
	
	void deleteAll();

}
