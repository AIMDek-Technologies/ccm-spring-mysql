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
 * JPA entity Statement.
 *
 * @author aimdek.team
 */
@Entity
@Table(name = "ccm_statements")
public class Statement implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6849608631069224471L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	/** The credit card id. */
	@Column(name = "creditcard_id")
	private long creditCardId;

	/** The statement date. */
	@Column(name = "statement_date")
	private Date statementDate;

	/** The from date. */
	@Column(name = "from_date")
	private Date fromDate;

	/** The to date. */
	@Column(name = "to_date")
	private Date toDate;

	/** The due date. */
	@Column(name = "due_date")
	private Date dueDate;

	/** The amount due. */
	@Column(name = "amount_due")
	private double amountDue;

	/** The document reference. */
	@Column(name = "document_reference")
	private String documentReference;

	/** The card number. */
	@Column(name = "card_number")
	private String cardNumber;

	/** The statement id. */
	@Column(name = "statement_id")
	private long statementId;

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
	 * Gets the statement date.
	 *
	 * @return the statementDate
	 */
	public Date getStatementDate() {
		if (statementDate != null) {
			return new Date(this.statementDate.getTime());
		}
		return statementDate;

	}

	/**
	 * Sets the statement date.
	 *
	 * @param statementDate
	 *            the statementDate to set
	 */
	public void setStatementDate(Date statementDate) {
		this.statementDate = statementDate;
	}

	/**
	 * Gets the from date.
	 *
	 * @return the fromDate
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getFromDate() {
		if (fromDate != null) {
			return new Date(this.fromDate.getTime());
		}
		return fromDate;
	}

	/**
	 * Sets the from date.
	 *
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the toDate
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getToDate() {
		if (toDate != null) {
			return new Date(this.toDate.getTime());
		}
		return toDate;
	}

	/**
	 * Sets the to date.
	 *
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * Gets the due date.
	 *
	 * @return the dueDate
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDueDate() {
		if (dueDate != null) {
			return new Date(this.dueDate.getTime());
		}
		return dueDate;
	}

	/**
	 * Sets the due date.
	 *
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Gets the amount due.
	 *
	 * @return the amountDue
	 */
	public double getAmountDue() {
		return this.amountDue;
	}

	/**
	 * Sets the amount due.
	 *
	 * @param amountDue
	 *            the amountDue to set
	 */
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	/**
	 * Gets the document reference.
	 *
	 * @return the documentReference
	 */
	public String getDocumentReference() {
		return this.documentReference;
	}

	/**
	 * Sets the document reference.
	 *
	 * @param documentReference
	 *            the documentReference to set
	 */
	public void setDocumentReference(String documentReference) {
		this.documentReference = documentReference;
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
	 * Gets the statement id.
	 *
	 * @return the statementId
	 */
	public long getStatementId() {
		return this.statementId;
	}

	/**
	 * Sets the statement id.
	 *
	 * @param statementId
	 *            the statementId to set
	 */
	public void setStatementId(long statementId) {
		this.statementId = statementId;
	}

}
