/**
 * 
 */
package com.aimdek.ccm.custom.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aimdek.ccm.document.CreditCard;

/**
 * The Class CustomerDto.
 *
 * @author aimdek.team
 */
public class CustomerDto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3381233689050853771L;

	/** The customer id. */
	private long customerId;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The email. */
	private String email;

	/** The phone number. */
	private String phoneNumber;

	/** The card number. */
	private String cardNumber;

	/** The credit limit. */
	private double creditLimit;

	/** The available credit limit. */
	private double availableCreditLimit;

	/** The generated id. */
	private long generatedId;

	/** The credit card list. */
	private List<CreditCard> creditCardList = new ArrayList<CreditCard>();

	/** The selected action. */
	private int selectedAction;

	/**
	 * Gets the selected action.
	 *
	 * @return the selectedAction
	 */
	public int getSelectedAction() {
		return selectedAction;
	}

	/**
	 * Sets the selected action.
	 *
	 * @param selectedAction
	 *            the selectedAction to set
	 */
	public void setSelectedAction(int selectedAction) {
		this.selectedAction = selectedAction;
	}

	/**
	 * Gets the credit card list.
	 *
	 * @return the creditCardList
	 */
	public List<CreditCard> getCreditCardList() {
		return creditCardList;
	}

	/**
	 * Sets the credit card list.
	 *
	 * @param creditCardList
	 *            the creditCardList to set
	 */
	public void setCreditCardList(List<CreditCard> creditCardList) {
		this.creditCardList = creditCardList;
	}

	/**
	 * Sets the customer id.
	 *
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Gets the customer id.
	 *
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the card number.
	 *
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
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
	 * Gets the credit limit.
	 *
	 * @return the creditLimit
	 */
	public double getCreditLimit() {
		return creditLimit;
	}

	/**
	 * Sets the credit limit.
	 *
	 * @param creditLimit
	 *            the creditLimit to set
	 */
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 * Gets the available credit limit.
	 *
	 * @return the availableCreditLimit
	 */
	public double getAvailableCreditLimit() {
		return availableCreditLimit;
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
	 * @return the generatedId
	 */
	public long getGeneratedId() {
		return generatedId;
	}

	/**
	 * @param generatedId the generatedId to set
	 */
	public void setGeneratedId(long generatedId) {
		this.generatedId = generatedId;
	}
}
