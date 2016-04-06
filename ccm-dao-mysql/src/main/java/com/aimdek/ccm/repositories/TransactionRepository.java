package com.aimdek.ccm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimdek.ccm.dao.TransactionRepositoryCustom;
import com.aimdek.ccm.document.Transaction;

/**
 * The Interface TransactionRepository.
 * @author aimdek.team
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryCustom {

	/**
	 * Find by credit card id.
	 *
	 * @param creditCardId the credit card id
	 * @return the list
	 */
	public List<Transaction> findByCreditCardId(long creditCardId);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the transaction
	 */
	public Transaction findById(long id);

}
