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

import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_CARDNUMBER;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.JPATestConfig;
import com.aimdek.ccm.document.Statement;
import com.aimdek.ccm.repositories.StatementRepository;

/**
 * The Class StatementDaoImplTest.
 *
 * @author aimdek.team
 */
@ContextConfiguration(classes = { JPATestConfig.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class StatementDaoImplTest extends BasicAbstractGenericDaoImplTest<Statement, String> {

	/** The statement repository. */
	@Autowired
	private StatementRepository statementRepository;

	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;

	/**
	 * Initialize.
	 */
	@Before
	public void initialize() {

		List<Statement> statementList = new ArrayList<Statement>();

		Statement statement = new Statement();
		statement.setAmountDue(500);
		statement.setCardNumber("1234-1234-1234-0008");
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(new Date());
		calendar1.add(Calendar.DATE, 15);
		calendar1.set(Calendar.HOUR_OF_DAY, 23);
		calendar1.set(Calendar.MINUTE, 59);
		calendar1.set(Calendar.MINUTE, 59);
		statement.setDueDate(calendar1.getTime());
		statement.setStatementDate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		statement.setToDate(calendar.getTime());
		statement.setCreditCardId(1);
		statementList.add(statement);

		Statement statement1 = new Statement();
		statement1.setAmountDue(500);
		statement1.setCardNumber("1234-1234-1234-0008");
		statement1.setDueDate(calendar1.getTime());
		statement1.setStatementDate(new Date());
		statement1.setToDate(calendar.getTime());
		statement1.setCreditCardId(2);
		statement1.setStatementDate(new Date());
		statementList.add(statement1);

		Statement statement2 = new Statement();
		statement2.setAmountDue(500);
		statement2.setCardNumber("1234-1234-1234-0008");
		statement2.setDueDate(calendar1.getTime());
		statement2.setStatementDate(new Date());
		statement2.setToDate(calendar.getTime());
		statement2.setCreditCardId(3);
		statement2.setStatementDate(new Date());
		statementList.add(statement2);

		Statement statement3 = new Statement();
		statement3.setAmountDue(500);
		statement3.setCardNumber("1234-1234-1234-0008");
		statement3.setDueDate(calendar1.getTime());
		statement3.setStatementDate(new Date());
		statement3.setToDate(calendar.getTime());
		statement3.setCreditCardId(3);
		statement3.setStatementDate(new Date());
		statementList.add(statement3);

		statementRepository.save(statementList);
	}

	/**
	 * Test find filter statement.
	 */
	@Test
	public void testFindFilterStatement() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Statement> query = builder.createQuery(Statement.class);
		Root<Statement> root = query.from(Statement.class);
		query.select(root);
		int limit = 4 - 2;
		List<Statement> statementList = entityManager.createQuery(query).setFirstResult(2).setMaxResults(limit).getResultList();

		assertEquals(2, statementList.size());
	}

	/**
	 * Test filtering.
	 */
	@Test
	public void testFiltering() {

		String cardNumber = "0008";
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Statement> query = builder.createQuery(Statement.class);
		Root<Statement> root = query.from(Statement.class);
		query.select(root);
		query.where(builder.like(root.<String> get(FIELDCONSTANT_CARDNUMBER), "%" + cardNumber));
		List<Statement> statementList = entityManager.createQuery(query).getResultList();

		assertEquals(4, statementList.size());
	}
}
