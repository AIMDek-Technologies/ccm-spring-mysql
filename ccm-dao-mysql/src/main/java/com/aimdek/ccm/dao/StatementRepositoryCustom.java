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

import com.aimdek.ccm.document.Statement;

/**
 * The Interface StatementDao.
 *
 * @author aimdek.team
 */
public interface StatementRepositoryCustom extends GenericDao<Statement, String> {
	
	/**
	 * Save statement.
	 *
	 * @param statement the statement
	 */
	public void saveStatement(Statement statement);

	/**
	 * Find last statement.
	 *
	 * @return the statement
	 */
	public Statement findLastStatement();


	/**
	 * Gets the statements.
	 *
	 * @param start the start
	 * @param end the end
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters the filters
	 * @param userId the user id
	 * @return the list of statement
	 */
	public List<Statement> getStatements(int start, int end, String sortField, String sortOrder, Map<String, Object> filters, long userId);

	/**
	 * Gets the statements count.
	 *
	 * @param filters the filters
	 * @param userId the user id
	 * @return the statements count
	 */
	public long getStatementsCount(Map<String, Object> filters, long userId);
}
