package com.kurtulussahin.java.ibtechtasks.tasks;

import java.text.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.BagKey;
import com.kurtulussahin.java.ibtechtasks.tasks.cmd.CommandExecuter;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.AccountDao;
import com.kurtulussahin.java.ibtechtasks.tasks.dao.BatchDataDao;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Account;
import com.kurtulussahin.java.ibtechtasks.tasks.model.BatchData;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Customer;
import com.kurtulussahin.java.ibtechtasks.tasks.operation.Operation;
import com.kurtulussahin.java.ibtechtasks.tasks.util.BatchUtil;
import com.kurtulussahin.java.ibtechtasks.tasks.util.TestDataUtil;

public class MainReflection {
	
	public static void main(String[] args) throws Exception {
		
		TestDataUtil.deleteAllRows();
		//TestDataUtil.CutomerCreateTest();
		//TestDataUtil.AccountCreateTest();
		TestDataUtil.createAccountsAndBatchData();
		
		int threadCount = 2;
		int commitCount = 2; 
		BatchUtil.startBatchProcess(threadCount,commitCount);
	}





	
	


}
