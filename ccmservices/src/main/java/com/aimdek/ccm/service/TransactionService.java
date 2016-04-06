/**
 * 
 */
package com.aimdek.ccm.service;

import java.util.List;
import java.util.Map;

import com.aimdek.ccm.document.Transaction;

/**
 * The Interface TransactionService.
 *
 * @author aimdek.team
 */
public interface TransactionService {
	
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
	 * @return the list
	 */
	public List<Transaction> getTransactions(int start, int end, String sortField, String sortOrder, Map<String, Object> filters, long userId);
	
	/**
	 * Next transaction id.
	 *
	 * @return the long
	 */
	public long nextTransactionId();
	
	/**
	 * Gets the transactions count.
	 *
	 * @param filters the filters
	 * @param userId the user id
	 * @return the long
	 */
	public long getTransactionsCount(Map<String, Object> filters, long userId);
	
	/**
	 * Removes the transaction.
	 *
	 * @param transactionId the transaction id
	 */
	public void removeTransaction(long transactionId);
	
	/**
	 * Search transaction.
	 *
	 * @param searchTerm the search term
	 * @return the list
	 */
	public List<Transaction> searchTransaction(String searchTerm);
}
