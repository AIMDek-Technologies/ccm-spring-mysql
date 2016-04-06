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

package com.aimdek.ccm.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

/**
 * The Interface GenericDao.
 *
 * @author aimdek.team
 */
public interface GenericDao<T, ID extends Serializable> {
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @param entityClass the entity class
	 * @param collectionName the collection name
	 * @return the t
	 */
	public T findById(ID id, Class<T> entityClass);
	
	
	
	/**
	 * Retrieve all.
	 *
	 * @param retrieveClass the retrieve class
	 * @param collectionName the collection name
	 * @return the list
	 */
	public List<T> retrieveAll(Class<T> retrieveClass);
	
	/**
	 * Find exact list.
	 *
	 * @param key the key
	 * @param value the value
	 * @param retrieveClass the retrieve class
	 * @param collectionName the collection name
	 * @return the list
	 */
	public List<T> findExactList(String key, ID value, Class<T> retrieveClass);
	
	
	/**
	 * Find exact.
	 *
	 * @param key the key
	 * @param value the value
	 * @param entityClass the entity class
	 * @param collectionName the collection name
	 * @return the t
	 */
	public T findExact(String key, Object value, Class<T> entityClass);
	
	
	/**
	 * Find the list of entity of same entity class.
	 *
	 * @param query the query
	 * @param entityClass the entity class
	 * @param collectionName the collection name
	 * @return the list
	 */
	public List<T> find(CriteriaQuery<T> query);
	
	/**
	 * Find one.
	 *
	 * @param query the query
	 * @param entityClass the entity class
	 * @param collectionName the collection name
	 * @return the t
	 */
	public T findOne(CriteriaQuery<T> query);
	
	
	/**
	 * Removes the given object from the given collection.
	 *
	 * @param object the object
	 * @param collectionName the collection name
	 */
	public void remove(T object);
}
