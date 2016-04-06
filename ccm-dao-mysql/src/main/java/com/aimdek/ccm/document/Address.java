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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JPA entity Address.
 *
 * @author aimdek.team
 */
@Entity
@Table(name = "ccm_address")
public class Address implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7065347680570388634L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	/** The house name. */
	@Column(name = "house_name")
	private String houseName;

	/** The street. */
	@Column(name = "street")
	private String street;

	/** The area. */
	@Column(name = "area")
	private String area;

	/** The user id. */
	@Column(name = "user_id")
	private long userId;

	/** The geo id. */
	@Column(name = "geo_id")
	private long geoId;

	/**
	 * Gets the house name.
	 *
	 * @return the houseName
	 */
	public String getHouseName() {
		return this.houseName;
	}

	/**
	 * Sets the house name.
	 *
	 * @param houseName
	 *            the houseName to set
	 */
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	public String getArea() {
		return this.area;
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

	/**
	 * Gets the geo id.
	 *
	 * @return the geoId
	 */
	public long getGeoId() {
		return geoId;
	}

	/**
	 * Sets the geo id.
	 *
	 * @param geoId
	 *            the geoId to set
	 */
	public void setGeoId(long geoId) {
		this.geoId = geoId;
	}

	/**
	 * Sets the area.
	 *
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
}
