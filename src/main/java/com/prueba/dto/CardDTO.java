package com.prueba.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CardDTO {

	private String id;
	private String cardType;
	private String cardNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	private Date dueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	private Date issuanceDate;
	private int securityCode;
	private String secretNumber;
	private int quota;
	private AccountDTO idAccount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getIssuanceDate() {
		return issuanceDate;
	}

	public void setIssuanceDate(Date issuanceDate) {
		this.issuanceDate = issuanceDate;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public String getSecretNumber() {
		return secretNumber;
	}

	public void setSecretNumber(String secretNumber) {
		this.secretNumber = secretNumber;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public AccountDTO getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(AccountDTO idAccount) {
		this.idAccount = idAccount;
	}

}