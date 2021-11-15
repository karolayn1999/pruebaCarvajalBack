package com.prueba.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
@Table(name = "cuenta")
public class AccountEntity {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "tipo_cuenta")
	private String accountType;
	@Column(name = "numero_cuenta")
	private String accountNumber;
	@Column(name = "estado")
	private String status;
	@Column(name = "saldo_total")
	private long totalBalance;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private ClientEntity client;

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

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

}