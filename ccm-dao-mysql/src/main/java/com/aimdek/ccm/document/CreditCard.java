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
 * JPA entity CreditCard.
 *
 * @author aimdek.team
 */
@Entity
@Table(name = "ccm_credit_card")
public class CreditCard implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6978744302354288088L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	/** The card number. */
	@Column(name = "card_number")
	private String cardNumber;

	/** The valid from date. */
	@Column(name = "valid_from_date")
	private Date validFromDate;

	/** The valid to date. */
	@Column(name = "valid_to_date")
	private Date validToDate;

	/** The cvv. */
	@Column(name = "cvv")
	private long cvv;

	/** The name on card. */
	@Column(name = "name_on_card")
	private String nameOnCard;

	/** The statement date. */
	@Column(name = "statement_date")
	private Date statementDate;

	/** The last statement date. */
	@Column(name = "last_statement_date")
	private Date lastStatementDate;

	/** The card holder id. */
	@Column(name = "card_holder_id")
	private long cardHolderId;

	/** The credit limit. */
	@Column(name = "credit_limit")
	private double creditLimit;

	/** The available credit limit. */
	@Column(name = "available_credit_limit")
	private double availableCreditLimit;

	/** The royalty points. */
	@Column(name = "royalty_points")
	private long royaltyPoints;

	/** The created by. */
	@Column(name = "created_by")
	private long createdBy;

	/** The created at. */
	@Column(name = "created_at")
	private final Date createdAt = new Date();

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
	 * Gets the valid from date.
	 *
	 * @return the validFromDate
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getValidFromDate() {
		if (validFromDate != null) {
			return new Date(this.validFromDate.getTime());
		}
		return validFromDate;
	}

	/**
	 * Sets the valid from date.
	 *
	 * @param validFromDate
	 *            the validFromDate to set
	 */
	public void setValidFromDate(Date validFromDate) {
		this.validFromDate = validFromDate;
	}

	/**
	 * Gets the valid to date.
	 *
	 * @return the validToDate
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getValidToDate() {
		if (validToDate != null) {
			return new Date(this.validToDate.getTime());
		}
		return validToDate;
	}

	/**
	 * Sets the valid to date.
	 *
	 * @param validToDate
	 *            the validToDate to set
	 */
	public void setValidToDate(Date validToDate) {
		this.validToDate = validToDate;
	}

	/**
	 * Gets the cvv.
	 *
	 * @return the cvv
	 */
	public long getCvv() {
		return this.cvv;
	}

	/**
	 * Sets the cvv.
	 *
	 * @param cvv
	 *            the cvv to set
	 */
	public void setCvv(long cvv) {
		this.cvv = cvv;
	}

	/**
	 * Gets the name on card.
	 *
	 * @return the nameOnCard
	 */
	public String getNameOnCard() {
		return this.nameOnCard;
	}

	/**
	 * Sets the name on card.
	 *
	 * @param nameOnCard
	 *            the nameOnCard to set
	 */
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	/**
	 * Gets the statement date.
	 *
	 * @return the statementDate
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
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
	 * Gets the available credit limit.
	 *
	 * @return the availableCreditLimit
	 */
	public double getAvailableCreditLimit() {
		return this.availableCreditLimit;
	}

	/**
	 * Sets the available credit limit.
	 *
	 * @param availableCreditLimit
	 *            the availableCreditLimit to set
	 */
	public void setAvailableCreditLimit(double availableCreditLimit) {
		this.availableCreditLimit = availableCreditLimit;
	}

	/**
	 * Gets the royalty points.
	 *
	 * @return the royaltyPoints
	 */
	public long getRoyaltyPoints() {
		return this.royaltyPoints;
	}

	/**
	 * Sets the royalty points.
	 *
	 * @param royaltyPoints
	 *            the royaltyPoints to set
	 */
	public void setRoyaltyPoints(long royaltyPoints) {
		this.royaltyPoints = royaltyPoints;
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
	 * Gets the card holder id.
	 *
	 * @return the cardHolderId
	 */
	public long getCardHolderId() {
		return cardHolderId;
	}

	/**
	 * Sets the card holder id.
	 *
	 * @param cardHolderId
	 *            the cardHolderId to set
	 */
	public void setCardHolderId(long cardHolderId) {
		this.cardHolderId = cardHolderId;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the createdBy
	 */
	public long getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		if (createdAt != null) {
			return new Date(this.createdAt.getTime());
		}
		return createdAt;
	}

	/**
	 * Gets the last statement date.
	 *
	 * @return the lastStatementDate
	 */
	public Date getLastStatementDate() {
		if (lastStatementDate != null) {
			return new Date(this.lastStatementDate.getTime());
		}
		return lastStatementDate;
	}

	/**
	 * Sets the last statement date.
	 *
	 * @param lastStatementDate
	 *            the lastStatementDate to set
	 */
	public void setLastStatementDate(Date lastStatementDate) {
		this.lastStatementDate = lastStatementDate;
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof CreditCard) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a hash code value for the object.
	 *
	 * @return the int
	 */
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Gets the credit limit.
	 *
	 * @return the credit limit
	 */
	public double getCreditLimit() {
		return creditLimit;
	}

	/**
	 * Sets the credit limit.
	 *
	 * @param creditLimit
	 *            the new credit limit
	 */
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

}
