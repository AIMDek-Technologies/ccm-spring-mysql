/*
 * Copyright (c) 2014-2015 AIMDek Technologies Private Limited. All Rights Reserved.
 * This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     AIMDek Technologies Private Limited - initial API and implementation
 */

package com.aimdek.ccm.dao.impl;

import static com.aimdek.ccm.util.CCMConstant.FIELD_CONSTANT_CREDIT_CARD_ID;
import static com.aimdek.ccm.util.CCMConstant.FIELD_CONSTANT_STATEMENT_DATE;
import static com.aimdek.ccm.util.CCMConstant.MODULO;
import static com.aimdek.ccm.util.CCMConstant.ROLE_CUSTOMER;
import static com.aimdek.ccm.util.CCMConstant.SORT_ORDER_ASCENDING;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.dao.StatementRepositoryCustom;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.Statement;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.CreditCardRepository;
import com.aimdek.ccm.repositories.StatementRepository;
import com.aimdek.ccm.repositories.UserRepository;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class StatementDaoImpl provides implementation of the interface
 * StatementDao.
 *
 * @author aimdek.team
 */
@Transactional
public class StatementRepositoryImpl extends BasicAbstractGenericDaoImpl<Statement, String> implements StatementRepositoryCustom {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(StatementRepositoryImpl.class);

	/** The card repository. */
	@Autowired
	CreditCardRepository cardRepository;

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/** The statement repository. */
	@Autowired
	private StatementRepository statementRepository;

	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;

	/**
	 * {@inheritDoc}
	 */
	public void saveStatement(Statement statement) {
		statementRepository.save(statement);
	}

	/**
	 * {@inheritDoc}
	 */
	public Statement findLastStatement() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Statement> query = builder.createQuery(Statement.class);
		Root<Statement> root = query.from(Statement.class);
		query.select(root);
		query.orderBy(builder.desc(root.get(FIELD_CONSTANT_STATEMENT_DATE)));
		try {
			return entityManager.createQuery(query).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.error("Error while retrieving last statement", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Statement> getStatements(int start, int limit, String sortField, String sortOrder, Map<String, Object> filters, long userId) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Statement> query = builder.createQuery(Statement.class);
		Root<Statement> root = query.from(Statement.class);
		query.select(root);
		addSorting(sortField, sortOrder, query, builder, root);
		addFilterCriteria(userId, filters, builder, root, query);
		return entityManager.createQuery(query).setFirstResult(start).setMaxResults(limit).getResultList();
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
	private void addFilterCriteria(long userId, Map<String, Object> filters, CriteriaBuilder builder, Root<Statement> root, CriteriaQuery query) {

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
				Expression<String> exp = root.get(FIELD_CONSTANT_CREDIT_CARD_ID);
				Predicate predicate = exp.in(retrieveCreditCardIdFromUserId(userId));
				predicates.add(predicate);
			}
		}

		query.where(predicates.toArray(new Predicate[] {}));
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
	private void addSorting(String sortField, String sortOrder, CriteriaQuery query, CriteriaBuilder builder, Root<Statement> root) {
		if (CommonUtil.isNotNull(sortField)) {
			if (sortOrder.startsWith(SORT_ORDER_ASCENDING)) {
				query.orderBy(builder.asc(root.get(sortField)));
			} else {
				query.orderBy(builder.desc(root.get(sortField)));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public long getStatementsCount(Map<String, Object> filters, long userId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Statement> root = query.from(Statement.class);
		query.select(builder.count(root));
		addFilterCriteria(userId, filters, builder, root, query);

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * Retrieve credit card id from user id.
	 *
	 * @param userId
	 *            the user id
	 * @return the list of ids.
	 */
	private List<Long> retrieveCreditCardIdFromUserId(long userId) {
		List<Long> ids = new ArrayList<Long>();
		List<CreditCard> creditCards = cardRepository.findByCardHolderId(userId);
		for (CreditCard creditCard : creditCards) {
			ids.add(creditCard.getId());
		}
		return ids;
	}
}
