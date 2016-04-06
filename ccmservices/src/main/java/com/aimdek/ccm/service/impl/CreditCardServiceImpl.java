/**
 * 
 */
package com.aimdek.ccm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.repositories.CreditCardRepository;
import com.aimdek.ccm.service.CreditCardService;

/**
 * The Class CreditCardServiceImpl.
 *
 * @author aimdek.team
 */
@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService {

	/** The credit card repository. */
	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
	 * {@inheritDoc}
	 */
	public void saveCreditCard(CreditCard creditCard) {
		creditCardRepository.saveCreditCard(creditCard);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CreditCard> findCreditCardsByCardHolderId(long cardHolderId) {

		List<CreditCard> creditCardList = creditCardRepository.findByCardHolderId(cardHolderId);

		return creditCardList;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeCreditCard(CreditCard creditCard) {
		creditCardRepository.delete(creditCard);
	}

	/**
	 * {@inheritDoc}
	 */
	public CreditCard findCreditCardById(long id) {
		return creditCardRepository.findById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CreditCard> findCreditCardsByStatementDate(Date startDate, Date endDate) {
		return creditCardRepository.findByStatementDateBetween(startDate, endDate);
	}

	/**
	 * {@inheritDoc}
	 */
	public long getCustomerCreditCardsCount(long cardHolderId) {
		return creditCardRepository.getCustomerCreditCardsCount(cardHolderId);
	}
}
