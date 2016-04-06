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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aimdek.ccm.document.Transaction;

/**
 * The Interface TransactionDao.
 *
 * @author aimdek.team
 */
public interface TransactionRepositoryCustom extends GenericDao<Transaction, String> {
	
	
	/**
	 * Save transaction.
	 *
	 * @param transaction the transaction
	 */
	public void saveTransaction(Transaction transaction);

	/**
	 * Gets the transactions.
	 *
	 * @param start the start
	 * @param end the end
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters the filters
	 * @param userId the user id
	 * @return the list of transaction
	 */
	public List<Transaction> getTransactions(int start, int end, String sortField, String sortOrder, Map<String, Object> filters, long userId);

	/**
	 * Find last transaction.
	 *
	 * @return the transaction
	 */
	public Transaction findLastTransaction();

	/**
	 * Gets the transactions count.
	 *
	 * @param filters the filters
	 * @param userId the user id
	 * @return the transactions count
	 */
	public long getTransactionsCount(Map<String, Object> filters, long userId);

	/**
	 * Find transactions by statement date and credit card id.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param creditCardId the credit card id
	 * @return the list of transaction
	 */
	public List<Transaction> findTransactionsByStatementDateAndCreditCardId(Date startDate, Date endDate, long creditCardId);
	
	/**
	 * Search transaction.
	 *
	 * @param searchTerm the search term
	 * @return the list
	 */
	public List<Transaction> searchTransaction(String searchTerm);

}
