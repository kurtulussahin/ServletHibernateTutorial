package com.kurtulussahin.java.ibtechtasks.tasks.operation;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.AddresDao;

public class AddresOperation implements CrudOperations {

	@Override
	public Bag add(Bag bag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bag update(Bag bag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bag list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bag delete(Bag bag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		AddresDao.deleteAll();
	}
}
