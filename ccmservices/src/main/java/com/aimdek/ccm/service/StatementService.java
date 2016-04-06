/**
 * 
 */
package com.aimdek.ccm.service;

import java.util.List;
import java.util.Map;

import com.aimdek.ccm.document.Statement;

/**
 * The Interface StatementService.
 *
 * @author aimdek.team
 */
public interface StatementService{
	
	/**
	 * Generate monthly statement.
	 */
	public void generateMonthlyStatement();
	
	/**
	 * Next statement id.
	 *
	 * @return the long
	 */
	public long nextStatementId();
	
	/**
	 * Gets the statements.
	 *
	 * @param start the start
	 * @param end the end
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters the filters
	 * @param userId the user id
	 * @return the list
	 */
	public List<Statement> getStatements(int start, int end, String sortField, String sortOrder, Map<String, Object> filters, long userId);
	
	/**
	 * Gets the statements count.
	 *
	 * @param filters the filters
	 * @param userId the user id
	 * @return the long
	 */
	public long getStatementsCount(Map<String, Object> filters, long userId);
	
	/**
	 * Gets the assigned url.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String getAssignedUrl(String key);
	
	/**
	 * Find statement by statement id.
	 *
	 * @param statementId the statement id
	 * @return the statement
	 */
	public Statement findStatementByStatementId(long statementId);

}
