package com.kurtulussahin.java.ibtechtasks.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.BagKey;
import com.kurtulussahin.java.ibtechtasks.tasks.cmd.CommandExecuter;
import com.kurtulussahin.java.ibtechtasks.tasks.model.BatchData;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Customer;
import com.kurtulussahin.java.ibtechtasks.tasks.util.BatchUtil;
import com.kurtulussahin.java.ibtechtasks.tasks.util.TestDataUtil;

public class ReflectionTest {
	private Bag bag;
	
	@Before
	public void setUp() throws Exception {
		bag = new Bag();
	}

	@Test
	public void creatingCustomer() throws Exception {
		bag.put(BagKey.NAME, "Kurtuluş");
		bag.put(BagKey.SURNAME, "Şahin");

		Bag customerBag = CommandExecuter.execute("customer_add", bag);
		assertEquals("Kurtuluş",customerBag.getValue(BagKey.NAME));
		assertEquals("Şahin",customerBag.getValue(BagKey.SURNAME));
	}
	
	@Test
	public void creatingAndUpdatingCustomer() throws Exception {
		bag.put(BagKey.NAME, "test");
		bag.put(BagKey.SURNAME, "test");

		Bag customerBag = CommandExecuter.execute("customer_add", bag);
 
		Bag bagUpdate = new Bag();
		long id = (long) customerBag.getValue(BagKey.ID);
		bagUpdate.put(BagKey.ID, id);
		bagUpdate.put(BagKey.NAME, "Updated test");
		bagUpdate.put(BagKey.SURNAME, "Updated test");

		Bag updatedBag = CommandExecuter.execute("customer_update", bagUpdate);

		assertEquals("Updated test",updatedBag.getValue(BagKey.NAME));
		assertEquals("Updated test",updatedBag.getValue(BagKey.SURNAME));
	}
	
	@Test
	public void retrievingCustomerList() throws Exception {
		Bag customersBag = CommandExecuter.execute("customer_list", new Bag());
		List<Customer> customers = (List<Customer>) customersBag.getValue(BagKey.CUSTOMERLIST);

		for (Customer customerItem : customers) {
			System.out.print("--> Id: " + customerItem.getId());
			System.out.print(" Name: " + customerItem.getName());
			System.out.println(" Surname: " + customerItem.getSurname());
		}	
	}
	
	@Test
	public void deletingOldDataAndCreatingAndProcessingBatchData() throws Exception {
		TestDataUtil.deleteAllRows();
		TestDataUtil.createAccountsAndBatchData();
		BatchUtil.startBatchProcess(2,2);
		
		assertEquals(0,BatchUtil.calculateNumberOfUnprocessedBatchData());
	}
	
}