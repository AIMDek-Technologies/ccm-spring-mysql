package com.aimdek.ccm.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimdek.ccm.dao.CreditCardRepositoryCustom;
import com.aimdek.ccm.document.CreditCard;

/**
 * The Interface CreditCardRepository.
 * 
 * @author aimdek.team
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>, CreditCardRepositoryCustom {

	/**
	 * Find by card holder id.
	 *
	 * @param customerId
	 *            the customer id
	 * @return the list
	 */
	public List<CreditCard> findByCardHolderId(long customerId);

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the credit card
	 */
	public CreditCard findById(long id);

	/**
	 * Find by statement date between.
	 *
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @return the list
	 */
	public List<CreditCard> findByStatementDateBetween(Date startDate, Date endDate);

	/**
	 * Find by card number.
	 *
	 * @param cardNumber
	 *            the card number
	 * @return the credit card
	 */
	public CreditCard findByCardNumber(String cardNumber);

}
