package com.kurtulussahin.java.ibtechtasks.tasks.operation;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.BagFactory;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.BagKey;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.AccountDao;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Account;

public class AccountOperation implements CrudOperations {

	private AccountDao accountDao;

	public AccountOperation() {
		this.accountDao = new AccountDao();
	}
	@Override
	public Bag add(Bag bag) {
		String accountName = (String) bag.getValue(BagKey.ACCOUNTNAME);
		Long customerId = (Long) bag.getValue(BagKey.CUSTOMERID);

		Account account = new Account(customerId, accountName);
		Account createdAccount = accountDao.create(account);
		
		Bag createdAccountBag = BagFactory.createBag();
		createdAccountBag.put(BagKey.ID, createdAccount.getId());
		createdAccountBag.put(BagKey.CUSTOMERID, createdAccount.getCustomerId());
		createdAccountBag.put(BagKey.ACCOUNTNAME, createdAccount.getAccountName());
		createdAccountBag.put(BagKey.BALANCE, createdAccount.getBalance());
		createdAccountBag.put(BagKey.STATUS, createdAccount.getStatus());

		
		return createdAccountBag;
	}

	@Override
	public Bag update(Bag bag) {
		long accountId = (long) bag.getValue(BagKey.ACCOUNTID);
		long balance = (long) bag.getValue(BagKey.BALANCE);	
		String accountName = (String) bag.getValue(BagKey.ACCOUNTNAME);
		int status = (int) bag.getValue(BagKey.STATUS);
		
		Account createdAccount = accountDao.update(accountId,  accountName,  balance,  status);
		Bag createdAccountBag = new Bag();
		createdAccountBag.put(BagKey.ID, createdAccount.getId());
		createdAccountBag.put(BagKey.CUSTOMERID, createdAccount.getCustomerId());
		createdAccountBag.put(BagKey.ACCOUNTNAME, createdAccount.getAccountName());
		createdAccountBag.put(BagKey.BALANCE, createdAccount.getBalance());
		createdAccountBag.put(BagKey.STATUS, createdAccount.getStatus());
		
		return createdAccountBag;
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
		AccountDao.deleteAll();
	}
}
