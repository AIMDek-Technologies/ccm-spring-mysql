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

import static com.aimdek.ccm.util.CCMConstant.FIELD_CONSTANT_ID;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.aimdek.ccm.dao.GenericDao;
/**
 * The Class BasicAbstractGenericDaoImpl provides implementation of the
 * interface GenericDao.
 *
 */
@SuppressWarnings("unchecked")
public class BasicAbstractGenericDaoImpl<EntityType, IDType extends Serializable> implements GenericDao<EntityType, IDType> {

	/** The persistent class. */
	private final Class<EntityType> persistentClass = (Class<EntityType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	/** The entity manager. */
	@Autowired
	private transient EntityManager entityManager;

	/**
	 * Gets the persistent class.
	 *
	 * @return the persistentClass
	 */
	public Class<EntityType> getPersistentClass() {
		return this.persistentClass;
	}

	/**
	 * {@inheritDoc}
	 */
	public EntityType findById(IDType id, Class<EntityType> entityClass) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EntityType> query = builder.createQuery(entityClass);
		Root<EntityType> root = query.from(entityClass);
		query.select(root);
		query.where(builder.equal(root.get(FIELD_CONSTANT_ID), id));

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EntityType> retrieveAll(Class<EntityType> entityClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EntityType> query = builder.createQuery(entityClass);
		Root<EntityType> root = query.from(entityClass);
		query.select(root);
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EntityType> findExactList(String key, IDType value, Class<EntityType> entityClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EntityType> query = builder.createQuery(entityClass);
		Root<EntityType> root = query.from(entityClass);
		query.select(root);
		query.where(builder.equal(root.get(key), value));

		return entityManager.createQuery(query).getResultList();

	}

	/**
	 * {@inheritDoc}
	 */
	public EntityType findExact(String key, Object value, Class<EntityType> entityClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EntityType> query = builder.createQuery(entityClass);
		Root<EntityType> root = query.from(entityClass);
		query.select(root);
		query.where(builder.equal(root.get(key), value));

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EntityType> find(CriteriaQuery<EntityType> query) {
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	public EntityType findOne(CriteriaQuery<EntityType> query) {
		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(EntityType object) {
		entityManager.remove(object);

	}

}
