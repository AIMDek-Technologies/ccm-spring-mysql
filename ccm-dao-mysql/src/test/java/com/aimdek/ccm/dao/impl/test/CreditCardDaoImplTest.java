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

package com.aimdek.ccm.dao.impl.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.JPATestConfig;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.repositories.CreditCardRepository;

/**
 * The Class CreditCardDaoImplTest.
 *
 * @author aimdek.team
 */
@ContextConfiguration(classes = { JPATestConfig.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class CreditCardDaoImplTest {

	/** The credit card id. */
	private long creditCardId;

	/** The credit card repository. */
	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
	 * initialize.
	 */
	@Before
	public void initialize() {

		List<CreditCard> creditcardList = new ArrayList<CreditCard>();

		CreditCard creditCard = new CreditCard();
		creditCard.setAvailableCreditLimit(15000);
		creditCard.setCardNumber("1234-1234-1234-0008");
		creditCard.setCreditLimit(20000);
		creditCard.setCvv(420);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		creditCard.setLastStatementDate(calendar.getTime());
		creditCard.setStatementDate(new Date());
		creditCard.setNameOnCard("Test1 Test1");
		creditCard.setCardHolderId(1);
		creditCard.setValidFromDate(new Date());
		creditCard.setValidToDate(new Date());
		creditCardRepository.saveCreditCard(creditCard);
		creditCardId = creditCard.getId();

		CreditCard creditCard1 = new CreditCard();
		creditCard1.setAvailableCreditLimit(15000);
		creditCard1.setCardNumber("2345-1234-2345-0009");
		creditCard1.setCreditLimit(20000);
		creditCard1.setCvv(420);
		creditCard1.setLastStatementDate(calendar.getTime());
		creditCard1.setStatementDate(new Date());
		creditCard1.setNameOnCard("Test2 Test2");
		creditCard1.setCardHolderId(2);
		creditCard1.setValidFromDate(new Date());
		creditCard1.setValidToDate(new Date());
		creditcardList.add(creditCard1);

		CreditCard creditCard2 = new CreditCard();
		creditCard2.setAvailableCreditLimit(15000);
		creditCard2.setCardNumber("1234-1234-1234-0010");
		creditCard2.setCreditLimit(20000);
		creditCard2.setCvv(420);
		creditCard2.setLastStatementDate(calendar.getTime());
		creditCard2.setStatementDate(new Date());
		creditCard2.setNameOnCard("Test3 Test3");
		creditCard2.setCardHolderId(3);
		creditCard2.setValidFromDate(new Date());
		creditCard2.setValidToDate(new Date());
		creditcardList.add(creditCard2);

		CreditCard creditCard3 = new CreditCard();
		creditCard3.setAvailableCreditLimit(15000);
		creditCard3.setCardNumber("2345-1234-2345-0011");
		creditCard3.setCreditLimit(20000);
		creditCard3.setCvv(420);
		creditCard3.setLastStatementDate(calendar.getTime());
		creditCard3.setStatementDate(new Date());
		creditCard3.setNameOnCard("Test4 Test4");
		creditCard3.setCardHolderId(4);
		creditCard3.setValidFromDate(new Date());
		creditCard3.setValidToDate(new Date());
		creditcardList.add(creditCard3);

		CreditCard creditCard5 = new CreditCard();
		creditCard5.setAvailableCreditLimit(15000);
		creditCard5.setCardNumber("1234-1234-1234-0012");
		creditCard5.setCreditLimit(20000);
		creditCard5.setCvv(420);
		creditCard5.setLastStatementDate(calendar.getTime());
		creditCard5.setStatementDate(new Date());
		creditCard5.setNameOnCard("Test3 Test3");
		creditCard5.setCardHolderId(4);
		creditCard5.setValidFromDate(new Date());
		creditCard5.setValidToDate(new Date());
		creditcardList.add(creditCard5);

		creditCardRepository.save(creditcardList);
	}

	/**
	 * Test get credit cards from user id.
	 */
	@Test
	public void testGetCreditCardsFromUserId() {
		long cardHolderId = 4;
		List<CreditCard> creditCards = creditCardRepository.findByCardHolderId(cardHolderId);
		assertEquals(2, creditCards.size());
	}

	/**
	 * Test delete credit card.
	 */
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteCreditCard() {

		CreditCard creditCard = creditCardRepository.getOne(creditCardId);

		creditCardRepository.remove(creditCard);

		creditCardRepository.getOne(creditCardId);

	}

	/**
	 * Test find credit card by statement date.
	 */
	@Test
	public void testFindCreditCardByStatementDate() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY, 23);
		calendar1.set(Calendar.MINUTE, 59);
		calendar1.set(Calendar.SECOND, 59);

		List<CreditCard> creditCardList = creditCardRepository.findByStatementDateBetween(calendar.getTime(), calendar1.getTime());

		assertEquals(5, creditCardList.size());
	}

}
