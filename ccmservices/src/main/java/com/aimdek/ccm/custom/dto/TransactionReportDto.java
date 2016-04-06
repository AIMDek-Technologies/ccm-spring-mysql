/**
 * 
 */
package com.aimdek.ccm.custom.dto;

/**
 * The Class TransactionReportDto.
 *
 * @author aimdek.team
 */
public class TransactionReportDto {
	
	/** The transaction date. */
	private String transactionDate;
	
	/** The description. */
	private String description;
	
	/** The amount. */
	private Double amount;
	
	/**
	 * Gets the transaction date.
	 *
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return transactionDate;
	}
	
	/**
	 * Sets the transaction date.
	 *
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
