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

import static com.aimdek.ccm.util.CCMConstant.*;

import java.util.ArrayList;
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

import com.aimdek.ccm.dao.UserRepositoryCustom;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.UserRepository;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class UsersDaoImpl provides implementation of the interface UsersDao.
 *
 * @author aimdek.team
 */
@Transactional
public class UserRepositoryImpl extends BasicAbstractGenericDaoImpl<User, String> implements UserRepositoryCustom {

	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);
	
	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * {@inheritDoc}
	 */
	public void saveUser(User user) {
		userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> getCustomers(int start, int limit, String sortField, String sortOrder, Map<String, Object> filters) {
		
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		Predicate isCustomerRole = builder.equal(root.get(FIELDCONSTANT_USER_ROLE), ROLE_CUSTOMER);
		Predicate hasCreditCard = builder.equal(root.get(FIELD_CONSTANT_HAS_CREDIT_CARD), TRUE);
		query.where(builder.and(isCustomerRole, hasCreditCard));
		
		query.select(root);
		
		addSorting(sortField, sortOrder, query, builder, root);
		addFilterCriteria(filters, builder, root, query);
		return entityManager.createQuery(query).setFirstResult(start).setMaxResults(limit).getResultList();
	}

	
	/**
	 * Adds the filter criteria.
	 *
	 * @param filters the filters
	 * @param builder the builder
	 * @param root the root
	 * @param query the query
	 */
	private void addFilterCriteria(Map<String, Object> filters, CriteriaBuilder builder, Root<User> root, CriteriaQuery query) {
		if (!filters.isEmpty()) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (Entry<String, Object> entry : filters.entrySet()) {
				if (!entry.getKey().equalsIgnoreCase(FIELDCONSTANT_BALANCE) && !entry.getKey().equalsIgnoreCase(FIELDCONSTANT_CARDNUMBER)
						&& !entry.getKey().equalsIgnoreCase(FIELDCONSTANT_CREDITLIMIT)) {
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
	private void addSorting(String sortField, String sortOrder, CriteriaQuery<User> query,CriteriaBuilder builder,Root<User> root) {
		boolean isSet = FALSE;
		if (CommonUtil.isNotNull(sortField) && !sortField.contains(FIELDCONSTANT_CARDNUMBER) && !sortField.contains(FIELDCONSTANT_CREDITLIMIT) && !sortField.contains(FIELDCONSTANT_BALANCE)) {
			isSet = TRUE;
			if (sortOrder.startsWith(SORT_ORDER_ASCENDING)) {
				query.orderBy(builder.asc(root.get(sortField)));
			} else {
				query.orderBy(builder.desc(root.get(sortField)));
			}
		}
		if (!isSet) {
			query.orderBy(builder.desc(root.get(FIELD_CONSTANT_CREATED_AT)));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public long getCustomersCount(Map<String, Object> filters) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<User> root = query.from(User.class);
		query.select(builder.count(root));
		query.where(builder.equal(root.<String>get(FIELDCONSTANT_USER_ROLE), ROLE_CUSTOMER));
		this.addFilterCriteria(filters, builder, root, query);
		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public User findLastCustomer() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(builder.equal(root.<String>get(FIELDCONSTANT_USER_ROLE), ROLE_CUSTOMER));
		query.orderBy(builder.desc(root.get(FIELD_CONSTANT_CREATED_AT)));
		try {
			return entityManager.createQuery(query).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.error("Error while retrieving last customer", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> getCustomers() {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(builder.equal(root.<String>get(FIELDCONSTANT_USER_ROLE), ROLE_CUSTOMER));
		return entityManager.createQuery(query).getResultList();
	}
}
