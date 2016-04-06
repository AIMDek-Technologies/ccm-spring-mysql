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
 * JPA entity User.
 *
 * @author aimdek.team
 */
@Entity
@Table(name = "ccm_users")
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7668432890359582676L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	/** The password. */
	@Column(name = "password")
	private String password;

	/** The first name. */
	@Column(name = "firstname")
	private String firstName;

	/** The last name. */
	@Column(name = "lastname")
	private String lastName;

	/** The birth date. */
	@Column(name = "birthdate")
	private Date birthDate;

	/** The email. */
	@Column(name = "email")
	private String email;

	/** The phone number. */
	@Column(name = "phone_number")
	private String phoneNumber;

	/** The profile picture. */
	@Column(name = "profile_picture")
	private String profilePicture;

	/** The address id. */
	@Column(name = "address_id")
	private long addressId;

	/** The role. */
	@Column(name = "role")
	private String role;

	/** The created by. */
	@Column(name = "created_by")
	private long createdBy;

	/** The customer id. */
	@Column(name = "customer_id")
	private long customerId;

	/** The created at. */
	@Column(name = "created_at")
	private final Date createdAt = new Date();

	/** The state. */
	@Column(name = "state")
	private String state;

	/** The has credit card. */
	@Column(name = "has_credit_card")
	private boolean hasCreditCard;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		// Nothing to Instantiates
	}

	/**
	 * Instantiates a new user with expected arguments.
	 *
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @param role
	 *            the role
	 */
	public User(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
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
		return this.lastName;
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
	 * Gets the birth date.
	 *
	 * @return the birthDate
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getBirthDate() {
		if (this.birthDate != null) {
			return new Date(this.birthDate.getTime());
		}
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
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
		return this.phoneNumber;
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
	 * Gets the profile picture.
	 *
	 * @return the profilePicture
	 */
	public String getProfilePicture() {
		return this.profilePicture;
	}

	/**
	 * Sets the profile picture.
	 *
	 * @param profilePicture
	 *            the profilePicture to set
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
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
	 * Gets the address id.
	 *
	 * @return the addressId
	 */
	public long getAddressId() {
		return addressId;
	}

	/**
	 * Sets the address id.
	 *
	 * @param addressId
	 *            the addressId to set
	 */
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return this.role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the customer id.
	 *
	 * @return the customerId
	 */
	public long getCustomerId() {
		return this.customerId;
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
	 * Gets the full name.
	 *
	 * @return the state
	 */
	public String getFullName() {
		return this.lastName + " " + this.firstName;
	}

	/**
	 * Checks if is checks for credit card.
	 *
	 * @return true, if is checks for credit card
	 */
	public boolean isHasCreditCard() {
		return hasCreditCard;
	}

	/**
	 * Sets the checks for credit card.
	 *
	 * @param hasCreditCard
	 *            the new checks for credit card
	 */
	public void setHasCreditCard(boolean hasCreditCard) {
		this.hasCreditCard = hasCreditCard;
	}
}
