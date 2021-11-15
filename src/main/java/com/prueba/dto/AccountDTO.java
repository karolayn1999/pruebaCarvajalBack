package com.prueba.dto;

public class AccountDTO {

	private String id;
	private String accountType;
	private String accountNumber;
	private String status;
	private long totalBalance;
	private ClientDTO client;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(long totalBalance) {
		this.totalBalance = totalBalance;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

}