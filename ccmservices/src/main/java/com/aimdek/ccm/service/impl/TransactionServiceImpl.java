/**
 * 
 */
package com.aimdek.ccm.service.impl;

import static com.aimdek.ccm.util.CCMConstant.TRANSACTION_DEFAULT_ID;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.repositories.TransactionRepository;
import com.aimdek.ccm.service.TransactionService;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class TransactionServiceImpl.
 *
 * @author aimdek.team
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {


	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * {@inheritDoc}
	 */
	public void saveTransaction(Transaction transaction) {

		if (transaction.getTransactionId() < 1) {
			transaction.setTransactionId(nextTransactionId());
		}
		transactionRepository.saveTransaction((transaction));

	}

	/**
	 * {@inheritDoc}
	 */
	public List<Transaction> getTransactions(int start, int end, String sortField, String sortOrder, Map<String, Object> filters, long userId) {

		List<Transaction> transactionList = transactionRepository.getTransactions(start, end, sortField, sortOrder, filters, userId);

		return transactionList;
	}

	/**
	 * {@inheritDoc}
	 */
	public long nextTransactionId() {

		long transactionId = 0;

		Transaction transaction = transactionRepository.findLastTransaction();
		if (CommonUtil.isNotNull(transaction)) {
			transactionId = transaction.getTransactionId() + 1;
		} else {
			transactionId = TRANSACTION_DEFAULT_ID;
		}
		return transactionId;
	}

	/**
	 * {@inheritDoc}
	 */
	public long getTransactionsCount(Map<String, Object> filters, long userId) {
		return transactionRepository.getTransactionsCount(filters, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeTransaction(long id) {
		Transaction transaction = transactionRepository.findById(id);
		if (CommonUtil.isNotNull(transaction)) {
			transactionRepository.delete(transaction);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Transaction> searchTransaction(String searchTerm) {
		return transactionRepository.searchTransaction(searchTerm);
	}
}
