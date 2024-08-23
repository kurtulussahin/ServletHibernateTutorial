package com.kurtulussahin.java.ibtechtasks.tasks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batch_data")
public class BatchData {
	
	@Id 
	@Column(name="sira_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long siraNo;
	
	@Column(name="status") 
	private boolean status;
	
	@Column(name="account_no") 
	private long accountNo;
	
	@Column(name="amount") 
	private double amount;
	
	@Column(name="transaction_type") 
	private char transactionType;

	public BatchData(boolean status, long accountNo, double amount, char transactionType) {
		super();
		this.status = status;
		this.accountNo = accountNo;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	public BatchData() {
		super();
	}

	public long getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(long siraNo) {
		this.siraNo = siraNo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public char getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(char transactionType) {
		this.transactionType = transactionType;
	}
	
	

}
