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

package com.aimdek.ccm.dao.impl;

import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_CUSTOMERNAME;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_USERID;
import static com.aimdek.ccm.util.CCMConstant.FIELD_CONSTANT_CREDIT_CARD_ID;
import static com.aimdek.ccm.util.CCMConstant.FIELD_CONSTANT_TRANSACTION_DATE;
import static com.aimdek.ccm.util.CCMConstant.FIELD_CONSTANT_TRANSACTION_ID;
import static com.aimdek.ccm.util.CCMConstant.MODULO;
import static com.aimdek.ccm.util.CCMConstant.ROLE_CUSTOMER;
import static com.aimdek.ccm.util.CCMConstant.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.dao.TransactionRepositoryCustom;
import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.TransactionRepository;
import com.aimdek.ccm.repositories.UserRepository;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class TransactionDaoImpl provides implementation of the interface
 * TransactionsDao.
 *
 * @author aimdek.team
 */
@Transactional
public class TransactionRepositoryImpl extends BasicAbstractGenericDaoImpl<Transaction, String> implements TransactionRepositoryCustom {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(TransactionRepositoryImpl.class);

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;

	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;

	/**
	 * {@inheritDoc}
	 */
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Transaction> getTransactions(int start, int limit, String sortField, String sortOrder, Map<String, Object> filters, long userId) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		addSorting(sortField, sortOrder, query, builder, root);
		addFilterCriteria(userId, filters, builder, root, query);

		return entityManager.createQuery(query).setFirstResult(start).setMaxResults(limit).getResultList();

	}

	/**
	 * Adds the sorting.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 * @param query
	 *            the query
	 * @param builder
	 *            the builder
	 * @param root
	 *            the root
	 */
	private void addSorting(String sortField, String sortOrder, CriteriaQuery query, CriteriaBuilder builder, Root<Transaction> root) {
		if (CommonUtil.isNotNull(sortField)) {
			if (sortOrder.startsWith(SORT_ORDER_ASCENDING)) {
				query.orderBy(builder.asc(root.get(sortField)));
			} else {
				query.orderBy(builder.desc(root.get(sortField)));
			}
		}
	}

	/**
	 * Adds the filter criteria.
	 *
	 * @param userId
	 *            the user id
	 * @param filters
	 *            the filters
	 * @param builder
	 *            the builder
	 * @param root
	 *            the root
	 * @param query
	 *            the query
	 */

	private void addFilterCriteria(long userId, Map<String, Object> filters, CriteriaBuilder builder, Root<Transaction> root, CriteriaQuery query) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!filters.isEmpty()) {
			for (Entry<String, Object> entry : filters.entrySet()) {
				if (CommonUtil.isNotNull(root.get(entry.getKey()))) {
					predicates.add(builder.like(root.<String> get(entry.getKey()), entry.getValue() + MODULO));
				}
			}
		}
		if (CommonUtil.isNotNull(userId)) {
			User user = userRepository.findById(userId);
			if (CommonUtil.isNotNull(user) && user.getRole().equals(ROLE_CUSTOMER)) {
				predicates.add(builder.equal(root.<Long> get(FIELDCONSTANT_USERID), user.getId()));
			}
		}

		query.where(predicates.toArray(new Predicate[] {}));
	}

	/**
	 * {@inheritDoc}
	 */
	public Transaction findLastTransaction() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		query.orderBy(builder.desc(root.get(FIELD_CONSTANT_TRANSACTION_ID)));
		try {
			return entityManager.createQuery(query).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.error("Error while retrieving last transaction", e);
		}
		return null;

	}

	/**
	 * {@inheritDoc}
	 */
	public long getTransactionsCount(Map<String, Object> filters, long userId) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(builder.count(root));
		addFilterCriteria(userId, filters, builder, root, query);

		return entityManager.createQuery(query).getSingleResult();

	}

	/**
	 * {@inheritDoc}
	 */
	public List<Transaction> findTransactionsByStatementDateAndCreditCardId(Date startDate, Date endDate, long creditCardId) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		Predicate date = builder.greaterThan(root.<Date> get(FIELD_CONSTANT_TRANSACTION_DATE), startDate);
		Predicate isCreditCardId = builder.equal(root.<String> get(FIELD_CONSTANT_CREDIT_CARD_ID), creditCardId);
		query.where(builder.and(date, isCreditCardId));
		return super.find(query);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Transaction> searchTransaction(String searchTerm) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		Predicate customerName = builder.like(root.<String> get(FIELDCONSTANT_CUSTOMERNAME), MODULO + searchTerm + MODULO);
		Predicate creditCardNumber = builder.like(root.<String> get(FIELDCONSTANT_CARDNUMBER), MODULO + searchTerm + MODULO);
		Predicate description = builder.like(root.<String> get(FIELD_CONSTANT_DESCRIPTION), MODULO + searchTerm + MODULO);
		query.where(builder.or(customerName, creditCardNumber, description));
		return super.find(query);
	}

}
