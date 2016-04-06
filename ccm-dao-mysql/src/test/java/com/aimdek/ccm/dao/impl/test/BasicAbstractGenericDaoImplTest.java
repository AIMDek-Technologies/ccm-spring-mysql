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

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Class BasicAbstractGenericDaoImpl.
 *
 * @author aimdek.team
 */
@SuppressWarnings("unchecked")
public class BasicAbstractGenericDaoImplTest<EntityType, IDType extends Serializable> {

	/** The persistent class. */
	private Class<EntityType> persistentClass = (Class<EntityType>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	/** The entity manager. */
	@Autowired
	private transient EntityManager entityManager;

	/**
	 * Gets the persistent class.
	 *
	 * @return the persistentClass
	 */
	public Class<EntityType> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @param entityClass
	 *            the entity class
	 * @return the entity type
	 */
	public EntityType findById(IDType id, Class<EntityType> entityClass) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EntityType> query = builder.createQuery(entityClass);
		Root<EntityType> root = query.from(entityClass);
		query.select(root);
		query.where(builder.equal(root.get("id"), id));

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * Retrieve all.
	 *
	 * @param entityClass
	 *            the entity class
	 * @return the list
	 */
	public List<EntityType> retrieveAll(Class<EntityType> entityClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EntityType> query = builder.createQuery(entityClass);
		Root<EntityType> root = query.from(entityClass);
		query.select(root);
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * Find exact list.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @param entityClass
	 *            the entity class
	 * @return the list
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
	 * Find exact.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @param entityClass
	 *            the entity class
	 * @return the entity type
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
	 * Find.
	 *
	 * @param query
	 *            the query
	 * @return the list
	 */
	public List<EntityType> find(CriteriaQuery<EntityType> query) {
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * Find one.
	 *
	 * @param query
	 *            the query
	 * @return the entity type
	 */
	public EntityType findOne(CriteriaQuery<EntityType> query) {
		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * Removes the.
	 *
	 * @param object
	 *            the object
	 */
	public void remove(EntityType object) {
		entityManager.remove(object);

	}
}
