package com.prueba.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tarjeta")
public class CardEntity {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "tipo_tarjeta")
	private String cardType;
	@Column(name = "numero_tarjeta")
	private String cardNumber;
	@Column(name = "fecha_vencimiento")
	private Date dueDate;
	@Column(name = "fecha_expedicion")
	private Date issuanceDate;
	@Column(name = "codigo_seguridad")
	private int securityCode;
	@Column(name = "numero_oculto")
	private String secretNumber;
	@Column(name = "cupo")
	private int quota;
	@ManyToOne
	@JoinColumn(name = "cuenta_id")
	private AccountEntity idAccount;

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

	public AccountEntity getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(AccountEntity idAccount) {
		this.idAccount = idAccount;
	}

}