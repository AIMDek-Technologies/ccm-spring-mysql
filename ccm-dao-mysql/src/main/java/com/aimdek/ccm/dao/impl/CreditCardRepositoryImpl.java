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

import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_BALANCE;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_CARDHOLDERID;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_CARDNUMBER;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_CREDITLIMIT;
import static com.aimdek.ccm.util.CCMConstant.MODULO;
import static com.aimdek.ccm.util.CCMConstant.ROLE_CUSTOMER;
import static com.aimdek.ccm.util.CCMConstant.SORT_ORDER_ASCENDING;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.dao.CreditCardRepositoryCustom;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.CreditCardRepository;
import com.aimdek.ccm.repositories.UserRepository;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class CreditCardDaoImpl provides implementation of the interface CreditCardDao.
 *
 * @author aimdek.team
 */
@Transactional
public class CreditCardRepositoryImpl extends BasicAbstractGenericDaoImpl<CreditCard, String> implements CreditCardRepositoryCustom {
	
	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The credit card repository. */
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	/**
	 * {@inheritDoc}
	 */
	public void saveCreditCard(CreditCard creditCard) {
		creditCardRepository.save(creditCard);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CreditCard> getCreditCards(String sortField, String sortOrder, Map<String, Object> filters) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CreditCard> query = builder.createQuery(CreditCard.class);
		Root<CreditCard> root = query.from(CreditCard.class);
		query.select(root);
		addSorting(sortField, sortOrder, query,builder,root);
		addFilterCriteria(filters, builder, root, query);

		return super.find(query);
	}

	/**
	 * Adds the filter criteria.
	 *
	 * @param filters the filters
	 * @param builder the builder
	 * @param root the root
	 * @param query the query
	 */
	
	private void addFilterCriteria(Map<String, Object> filters, CriteriaBuilder builder, Root<CreditCard> root, CriteriaQuery query) {
		if (!filters.isEmpty()) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (Entry<String, Object> entry : filters.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(FIELDCONSTANT_CARDNUMBER) || entry.getKey().equalsIgnoreCase(FIELDCONSTANT_CREDITLIMIT)
						|| entry.getKey().equalsIgnoreCase(FIELDCONSTANT_BALANCE)) {
					predicates.add(builder.like(root.<String>get(entry.getKey()), entry.getValue() + MODULO));
				}
			}
			query.where(predicates.toArray(new Predicate[] {}));
		}
	}
	
	
	/**
	 * Adds the sorting.
	 *
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param query the query
	 * @param builder the builder
	 * @param root the root
	 */
	
	private void addSorting(String sortField, String sortOrder, CriteriaQuery query,CriteriaBuilder builder,Root<CreditCard> root) {
		if (CommonUtil.isNotNull(sortField)
				&& (sortField.equalsIgnoreCase(FIELDCONSTANT_CARDNUMBER) || sortField.equalsIgnoreCase(FIELDCONSTANT_CREDITLIMIT) || sortField
						.equalsIgnoreCase(FIELDCONSTANT_BALANCE))) {
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
	public long getCreditCardsCount(Map<String, Object> filters) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<CreditCard> root = query.from(CreditCard.class);
		query.select(builder.count(root));
		addFilterCriteria(filters, builder, root, query);
		
		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public long getCustomerCreditCardsCount(long userId) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<CreditCard> root = query.from(CreditCard.class);
		query.select(builder.count(root));
		
		if (CommonUtil.isNotNull(userId)) {
			User user = userRepository.findById(userId);
			if (CommonUtil.isNotNull(user) && user.getRole().equals(ROLE_CUSTOMER)) {
				query.where(builder.equal(root.<Long>get(FIELDCONSTANT_CARDHOLDERID), user.getId()));
			}
		}
		return entityManager.createQuery(query).getSingleResult();
	}
}
