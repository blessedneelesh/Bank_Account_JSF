package com.cestar.model;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "acc")
public class BankAccount {
	int accountId;
	String accountType;
	String name;
	String contact;
	float balance;
	String createdDate;
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BankAccount(int accountId, String accountType, String name, String contact, float balance,
			String createdDate) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.name = name;
		this.contact = contact;
		this.balance = balance;
		this.createdDate = createdDate;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", accountType=" + accountType + ", name=" + name + ", contact="
				+ contact + ", balance=" + balance + ", createdDate=" + createdDate + "]";
	}
	
	
}
