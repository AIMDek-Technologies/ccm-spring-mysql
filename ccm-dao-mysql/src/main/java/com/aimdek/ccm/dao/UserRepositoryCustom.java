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

import java.util.List;
import java.util.Map;

import com.aimdek.ccm.document.User;

/**
 * The Interface UserDao.
 *
 * @author aimdek.team
 */
public interface UserRepositoryCustom extends GenericDao<User, String> {
	
	/**
	 * Save user.
	 *
	 * @param User the User
	 */
	public void saveUser(User User);

	
	/**
	 * Gets the customers.
	 *
	 * @param start the start
	 * @param end the end
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters the filters
	 * @param userIds the user ids
	 * @return the customers
	 */
	public List<User> getCustomers(int start, int end, String sortField, String sortOrder, Map<String, Object> filters);

	
	/**
	 * Gets the customers count.
	 *
	 * @param filters the filters
	 * @return the customers count
	 */
	public long getCustomersCount(Map<String, Object> filters);

	
	/**
	 * Find last customer.
	 *
	 * @return the User
	 */
	public User findLastCustomer();

	
	/**
	 * Gets the customers.
	 *
	 * @return the customers
	 */
	public List<User> getCustomers();
	
}
