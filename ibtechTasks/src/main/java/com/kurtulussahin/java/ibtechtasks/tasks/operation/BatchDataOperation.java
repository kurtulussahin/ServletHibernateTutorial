package com.kurtulussahin.java.ibtechtasks.tasks.operation;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.BagKey;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.AccountDao;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.BatchDataDao;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Account;

public class BatchDataOperation implements CrudOperations {

	private BatchDataDao batchDataDao;

	public BatchDataOperation() {
		this.batchDataDao = new BatchDataDao();
	}

	@Override
	public void deleteAll() {
		BatchDataDao.deleteAll();
	}

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
}
