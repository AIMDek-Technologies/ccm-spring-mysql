/*
 * Copyright (c) 2014-2015 AIMDek Technologies Private Limited. All Rights Reserved.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     AIMDek Technologies Private Limited - initial API and implementation
 */

package com.aimdek.ccm.document;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aimdek.ccm.util.CustomDateDeserializer;
import com.aimdek.ccm.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * JPA entity Transaction.
 *
 * @author aimdek.team
 */
@Entity
@Table(name = "ccm_transactions")
public class Transaction implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8909353020949372172L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	/** The credit card id. */
	@Column(name = "creditcard_id")
	private long creditCardId;

	/** The amount. */
	@Column(name = "amount")
	private double amount;

	/** The transaction date. */
	@Column(name = "transaction_date")
	private Date transactionDate;

	/** The balance. */
	@Column(name = "balance")
	private double balance;

	/** The description. */
	@Column(name = "description")
	private String description;

	/** The transaction id. */
	@Column(name = "transaction_id")
	private long transactionId;

	/** The customer name. */
	@Column(name = "customer_name")
	private String customerName;

	/** The card number. */
	@Column(name = "card_number")
	private String cardNumber;

	/** The state. */
	private String state;

	/** The user id. */
	@Column(name = "user_id")
	private long userId;

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the card number.
	 *
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * Sets the card number.
	 *
	 * @param cardNumber
	 *            the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Gets the customer name.
	 *
	 * @return the customerName
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return this.amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the transaction date.
	 *
	 * @return the transactionDate
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getTransactionDate() {
		if (transactionDate != null) {
			return new Date(this.transactionDate.getTime());
		}
		return transactionDate;
	}

	/**
	 * Sets the transaction date.
	 *
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * Sets the balance.
	 *
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the transaction id.
	 *
	 * @return the transactionId
	 */
	public long getTransactionId() {
		return this.transactionId;
	}

	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId
	 *            the transactionId to set
	 */
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the credit card id.
	 *
	 * @return the creditCardId
	 */
	public long getCreditCardId() {
		return creditCardId;
	}

	/**
	 * Sets the credit card id.
	 *
	 * @param creditCardId
	 *            the creditCardId to set
	 */
	public void setCreditCardId(long creditCardId) {
		this.creditCardId = creditCardId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

}
