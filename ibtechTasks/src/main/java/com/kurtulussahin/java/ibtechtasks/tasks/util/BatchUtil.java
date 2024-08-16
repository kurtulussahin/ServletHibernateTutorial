package com.kurtulussahin.java.ibtechtasks.tasks.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kurtulussahin.java.ibtechtasks.tasks.dao.BatchDataDao;
import com.kurtulussahin.java.ibtechtasks.tasks.model.BatchData;
import com.kurtulussahin.java.ibtechtasks.tasks.operation.Operation;

public class BatchUtil {
	public static void startBatchProcess(int threadcount, int commitCount) {

		List<BatchData> batchDatas = getBatchData();
		printBatchInfo(batchDatas);
		int numberOfUnprocessedData= calculateNumberOfUnprocessedBatchData();
		while (numberOfUnprocessedData > 0) { // işlenemeyen data olursa sonsuza girecek
			execute(threadcount, commitCount, batchDatas);
			numberOfUnprocessedData= calculateNumberOfUnprocessedBatchData();
			batchDatas = getBatchData();
			printBatchInfo(batchDatas);
		}

		System.out.println("Batch Process Finished");
	}

	public static List<BatchData> getBatchData() {
		BatchDataDao batchDataDao = new BatchDataDao();
		List<BatchData> batchDatas = batchDataDao.getList();
		return batchDatas;
	}

	private static void execute(int threadcount, int commitCount, List<BatchData> batchDatas) {
		ExecutorService executor = Executors.newFixedThreadPool(threadcount);
		for (int i = 0; i < threadcount; i++) {
			Runnable worker = new Operation(i * commitCount, (i + 1) * commitCount, batchDatas);
			executor.execute(worker);
			printThreadInfo(threadcount, commitCount, i);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}
	}

	private static void printThreadInfo(int threadcount, int commitCount, int orderOfThread) {
		DateFormat dateformat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");

		System.out
				.println(orderOfThread + " thread basladı. " + dateformat.format(new Date(System.currentTimeMillis())));
		System.out.println("-->> threadcount: " + threadcount);
		System.out.println("-->> commitCount: " + commitCount);

	}

	private static void printBatchInfo(List<BatchData> batchDatas) {
		System.out.println("-->> batchDatas Size: " + batchDatas.size());
		System.out.println("-->> unprocessedBatchDataCount: " + calculateNumberOfUnprocessedBatchData());
	}

	public static int calculateNumberOfUnprocessedBatchData() {
		int unprocessedBatchDataCount = 0;
		List<BatchData> batchDatas = getBatchData();
		for (int i = 0; i < batchDatas.size(); i++) {
			if (!batchDatas.get(i).isStatus()) {
				unprocessedBatchDataCount++;
			}
		}
		return unprocessedBatchDataCount;
	}
}
