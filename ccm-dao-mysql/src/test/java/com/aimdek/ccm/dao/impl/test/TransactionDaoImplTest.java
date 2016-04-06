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
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.JPATestConfig;
import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.repositories.TransactionRepository;

/**
 * The Class TransactionDaoImplTest.
 *
 * @author aimdek.team
 */
@ContextConfiguration(classes = { JPATestConfig.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionDaoImplTest {

	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * Initialize.
	 */
	@Before
	public void initialize() {

		List<Transaction> transactionList = new ArrayList<Transaction>();

		Transaction transaction = new Transaction();
		transaction.setAmount(1200);
		transaction.setBalance(23800);
		transaction.setCardNumber("1234-1234-1234-0008");
		transaction.setCreditCardId(1);
		transaction.setCustomerName("Test1 Test1");
		transaction.setDescription("Test1");
		transaction.setTransactionDate(new Date());
		transaction.setTransactionId(1001);
		transactionList.add(transaction);

		Transaction transaction1 = new Transaction();
		transaction1.setAmount(200);
		transaction1.setBalance(24800);
		transaction1.setCardNumber("1234-1234-1234-0008");
		transaction1.setCreditCardId(1);
		transaction1.setCustomerName("Test1 Test1");
		transaction1.setDescription("Test2");
		transaction1.setTransactionDate(new Date());
		transaction1.setTransactionId(1002);
		transactionList.add(transaction1);

		Transaction transaction2 = new Transaction();
		transaction2.setAmount(200);
		transaction2.setBalance(24800);
		transaction2.setCardNumber("1234-1234-1234-0009");
		transaction2.setCreditCardId(2);
		transaction2.setCustomerName("Test2 Test2");
		transaction2.setDescription("Test3");
		transaction2.setTransactionDate(new Date());
		transaction2.setTransactionId(1003);
		transactionList.add(transaction2);

		Transaction transaction3 = new Transaction();
		transaction3.setAmount(200);
		transaction3.setBalance(24800);
		transaction3.setCardNumber("1234-1234-1234-0010");
		transaction3.setCreditCardId(3);
		transaction3.setCustomerName("Test3 Test3");
		transaction3.setDescription("Test4");
		transaction3.setTransactionDate(new Date());
		transaction3.setTransactionId(1004);
		transactionList.add(transaction3);

		Transaction transaction4 = new Transaction();
		transaction4.setAmount(200);
		transaction4.setBalance(24800);
		transaction4.setCardNumber("1234-1234-1234-0010");
		transaction4.setCreditCardId(3);
		transaction4.setCustomerName("Test3 Test3");
		transaction4.setDescription("Test5");
		transaction4.setTransactionDate(new Date());
		transaction4.setTransactionId(1005);
		transactionList.add(transaction4);
		transactionRepository.save(transactionList);

	}

	/**
	 * Test get all transaction list.
	 */
	@Test
	public void testGetAllTransactionList() {

		List<Transaction> geList = transactionRepository.findAll();

		assertEquals(5, geList.size());
	}

}
