/**
 * 
 */
package com.aimdek.ccm.service;

import java.util.Date;
import java.util.List;

import com.aimdek.ccm.document.CreditCard;

/**
 * The Interface CreditCardService.
 *
 * @author aimdek.team
 */
public interface CreditCardService {
	
	/**
	 * Save credit card.
	 *
	 * @param creditCard the credit card
	 */
	public void saveCreditCard(CreditCard creditCard);
	
	/**
	 * Find credit cards by card holder id.
	 *
	 * @param cardHolderId the card holder id
	 * @return the credit card from customer id
	 */
	public List<CreditCard> findCreditCardsByCardHolderId(long cardHolderId);
	
	/**
	 * Removes the credit card.
	 *
	 * @param creditCard the credit card
	 */
	public void removeCreditCard(CreditCard creditCard);
	
	/**
	 * Find credit card by id.
	 *
	 * @param id the id
	 * @return the credit card
	 */
	public CreditCard findCreditCardById(long id);
	
	/**
	 * Find credit cards by statement date.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the list
	 */
	public List<CreditCard> findCreditCardsByStatementDate(Date startDate, Date endDate);
	
	/**
	 * Gets the customer credit cards count.
	 *
	 * @param cardHolderId the card holder id
	 * @return the long
	 */
	public long getCustomerCreditCardsCount(long cardHolderId);

}
