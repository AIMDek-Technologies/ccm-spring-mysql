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

package com.aimdek.ccm.dao;

import java.util.List;
import java.util.Map;

import com.aimdek.ccm.document.CreditCard;

/**
 * The Interface CreditCardDao.
 *
 * @author aimdek.team
 */
public interface CreditCardRepositoryCustom extends GenericDao<CreditCard, String> {
	
	/**
	 * Save credit card.
	 *
	 * @param creditCard the credit card
	 */
	public void saveCreditCard(CreditCard creditCard);

	/**
	 * Gets the credit cards.
	 *
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters the filters
	 * @return the list of credit cards
	 */
	public List<CreditCard> getCreditCards(String sortField, String sortOrder, Map<String, Object> filters);


	/**
	 * Gets the credit cards count.
	 *
	 * @param filters the filters
	 * @return the credit cards count
	 */
	public long getCreditCardsCount(Map<String, Object> filters);

	/**
	 * Gets the customer credit cards count.
	 *
	 * @param userId the user id
	 * @return the customer credit cards count
	 */
	public long getCustomerCreditCardsCount(long userId);

}
