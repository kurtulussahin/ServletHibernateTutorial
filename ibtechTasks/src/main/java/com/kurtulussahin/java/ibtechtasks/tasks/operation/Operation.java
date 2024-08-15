package com.kurtulussahin.java.ibtechtasks.tasks.operation;

import java.util.List;

import com.kurtulussahin.java.ibtechtasks.tasks.dao.AccountDao;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.BatchDataDao;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Account;
import com.kurtulussahin.java.ibtechtasks.tasks.model.BatchData;

public class Operation implements Runnable {

	private int startNumber;
	private int endNumber;
	private List<BatchData> batchDatas;

	public Operation(int startNumber, int endNumber, List<BatchData> batchDatas) {
		super();
		this.startNumber = startNumber;
		this.endNumber = endNumber;
		this.batchDatas = batchDatas;
	}

	@Override
	public void run() {

		BatchDataDao batchDataDao = new BatchDataDao();
		AccountDao accountDao = new AccountDao();

		for (int i = startNumber; i < endNumber; i++) {
			BatchData batchData = batchDatas.get(i);
			Account account = accountDao.getAccount(batchData.getAccountNo());

			if (batchData.getTransactionType() == 'A') {
				accountDao.updateBalance(account.getId(), account.getBalance() + batchData.getAmount());
			} else {
				accountDao.updateBalance(account.getId(), account.getBalance() - batchData.getAmount());
			}
			batchDataDao.updateStatus(batchData.getSiraNo(), true);

			System.out.println("--> " + i + ". batch data gerceklestirildi -->> " + Thread.currentThread().getName());
		}
	}
}
