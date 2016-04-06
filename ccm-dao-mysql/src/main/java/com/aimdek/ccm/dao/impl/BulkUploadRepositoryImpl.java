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

import static com.aimdek.ccm.util.CCMConstant.FIELD_CONSTANT_CREATED_AT;
import static com.aimdek.ccm.util.CCMConstant.MODULO;
import static com.aimdek.ccm.util.CCMConstant.SORT_ORDER_ASCENDING;

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

import com.aimdek.ccm.dao.BulkUploadRepositoryCustom;
import com.aimdek.ccm.document.BulkUpload;
import com.aimdek.ccm.repositories.BulkUploadRepository;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class BulkUploadDaoImpl provides implementation of the interface
 * BulkUploadDao.
 *
 * @author aimdek.team
 */
@Transactional
public class BulkUploadRepositoryImpl extends BasicAbstractGenericDaoImpl<BulkUpload, String> implements BulkUploadRepositoryCustom {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BulkUploadRepositoryImpl.class);

	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;

	/** The bulk upload repository. */
	@Autowired
	private BulkUploadRepository bulkUploadRepository;

	/**
	 * {@inheritDoc}
	 */
	public void saveBulkUpload(BulkUpload bulkUpload) {
		bulkUploadRepository.save(bulkUpload);

	}

	/**
	 * {@inheritDoc}
	 */
	public BulkUpload findLastBulkUpload() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BulkUpload> query = builder.createQuery(BulkUpload.class);
		Root<BulkUpload> root = query.from(BulkUpload.class);
		query.select(root);
		query.orderBy(builder.desc(root.get(FIELD_CONSTANT_CREATED_AT)));
		try {
			return entityManager.createQuery(query).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.error("Error while retrieving last bulkupload", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BulkUpload> getBulkUploads(int start, int limit, String sortField, String sortOrder, Map<String, Object> filters) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BulkUpload> query = builder.createQuery(BulkUpload.class);
		Root<BulkUpload> root = query.from(BulkUpload.class);
		query.select(root);
		addSorting(sortField, sortOrder, query, builder, root);
		addFilterCriteria(filters, builder, root, query);

		return entityManager.createQuery(query).setFirstResult(start).setMaxResults(limit).getResultList();

	}

	/**
	 * Adds the filter criteria.
	 *
	 * @param filters
	 *            the filters
	 * @param query
	 *            the query
	 */
	private void addFilterCriteria(Map<String, Object> filters, CriteriaBuilder builder, Root<BulkUpload> root, CriteriaQuery query) {
		if (!filters.isEmpty()) {
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (Entry<String, Object> entry : filters.entrySet()) {
				if (CommonUtil.isNotNull(root.get(entry.getKey()))) {
					predicates.add(builder.like(root.<String>get(entry.getKey()), entry.getValue() + MODULO));
				}
			}
			query.where(predicates.toArray(new Predicate[] {}));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public long getBulkUploadsCount(Map<String, Object> filters) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<BulkUpload> root = query.from(BulkUpload.class);
		query.select(builder.count(root));
		addFilterCriteria(filters, builder, root, query);

		return entityManager.createQuery(query).getSingleResult();
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
	 */
	private void addSorting(String sortField, String sortOrder, CriteriaQuery query, CriteriaBuilder builder, Root<BulkUpload> root) {
		if (CommonUtil.isNotNull(sortField)) {
			if (sortOrder.startsWith(SORT_ORDER_ASCENDING)) {
				query.orderBy(builder.asc(root.get(sortField)));
			} else {
				query.orderBy(builder.desc(root.get(sortField)));
			}
		}
	}
}
