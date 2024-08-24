package com.kurtulussahin.java.ibtechtasks.tasks.util;

import java.util.List;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.BagKey;
import com.kurtulussahin.java.ibtechtasks.tasks.cmd.CommandExecuter;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.AccountDao;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.BatchDataDao;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Account;
import com.kurtulussahin.java.ibtechtasks.tasks.model.BatchData;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Customer;

public class TestDataUtil {
	public static void createAccountsAndBatchData() {
		AccountDao accountDao = new AccountDao();
		BatchDataDao batchDataDao = new BatchDataDao();
		int numberOfAccount = 10;
		int numberOfBatchDataPerAccount = 2;
		createDummyAccounts(accountDao, batchDataDao, numberOfAccount, numberOfBatchDataPerAccount);
	}

	private static void createDummyAccounts(AccountDao accountDao, BatchDataDao batchDataDao, int numberOfAccount,
			int numberOfBatchDataPerAccount) {
		for (int i = 0; i < numberOfAccount; i++) {
			Account account = new Account("Vadesiz" + i, i * 10);
			accountDao.create(account);
			System.out.println(account);
			createDummyBatcDataForAccount(batchDataDao, account, numberOfBatchDataPerAccount);
		}
	}

	private static void createDummyBatcDataForAccount(BatchDataDao batchDataDao, Account account, int batchDataCount) {
		for (int j = 0; j < batchDataCount; j++) {
			char transactionType = 'A';
			if (j % 2 == 0) {
				transactionType = 'B';
			}
			BatchData batchData = new BatchData(false, account.getId(), j * 111, transactionType);
			batchDataDao.create(batchData);
		}
	}

	public static void deleteAllRows() throws Exception {
		Bag emptyBag = new Bag();
		CommandExecuter.execute("delete_all_customers", emptyBag);
		CommandExecuter.execute("delete_all_accounts", emptyBag);
		CommandExecuter.execute("delete_all_batchdata", emptyBag);
	}

	public static long AccountCreateTest() throws Exception {
		Account account;
		Bag bagAdd = new Bag();
		bagAdd.put(BagKey.NAME, "test");
		bagAdd.put(BagKey.SURNAME, "test");

		Bag customerBag = CommandExecuter.execute("customer_add", bagAdd);
		long customerId = (long) customerBag.getValue(BagKey.ID);

		bagAdd = new Bag();
		bagAdd.put(BagKey.CUSTOMERID, customerId);
		bagAdd.put(BagKey.ACCOUNTNAME, "MAIN");

		Bag accountBag = CommandExecuter.execute("account_add", bagAdd);

		Bag bagUpdate = new Bag();
		long accountId = (long) accountBag.getValue(BagKey.ID);
		bagUpdate.put(BagKey.ACCOUNTID, accountId);
		bagUpdate.put(BagKey.ACCOUNTNAME, "Updated MAIN");
		bagUpdate.put(BagKey.BALANCE, 100L);
		bagUpdate.put(BagKey.STATUS, 0);

		Bag updatedBag = CommandExecuter.execute("account_update", bagUpdate);

		return (long) accountBag.getValue(BagKey.ID);
	}

	public static void CutomerCreateTest() throws Exception {
		Bag bagAdd = new Bag();
		bagAdd.put(BagKey.NAME, "Kurtuluş");
		bagAdd.put(BagKey.SURNAME, "Şahin");

		Bag customerBag = CommandExecuter.execute("customer_add", bagAdd);

		bagAdd.put(BagKey.NAME, "test");
		bagAdd.put(BagKey.SURNAME, "test");

		customerBag = CommandExecuter.execute("customer_add", bagAdd);

		Bag bagUpdate = new Bag();
		long id = (long) customerBag.getValue(BagKey.ID);
		bagUpdate.put(BagKey.ID, id);
		bagUpdate.put(BagKey.NAME, "Updated test");
		bagUpdate.put(BagKey.SURNAME, "Updated test");

		Bag updatedBag = CommandExecuter.execute("customer_update", bagUpdate);

		Bag customersBag = CommandExecuter.execute("customer_list", new Bag());
		List<Customer> customers = (List<Customer>) customersBag.getValue(BagKey.CUSTOMERLIST);

		for (Customer customerItem : customers) {
			System.out.print("--> Id: " + customerItem.getId());
			System.out.print(" Name: " + customerItem.getName());
			System.out.println(" Surname: " + customerItem.getSurname());
		}
	}

}
