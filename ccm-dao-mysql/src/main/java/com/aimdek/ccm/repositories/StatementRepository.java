package com.aimdek.ccm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimdek.ccm.dao.StatementRepositoryCustom;
import com.aimdek.ccm.document.Statement;

/**
 * The Interface StatementRepository
 * @author aimdek.team
 */
public interface StatementRepository extends JpaRepository<Statement, Long>, StatementRepositoryCustom {

	/**
	 * Find by statement id.
	 *
	 * @param statementId the statement id
	 * @return the statement
	 */
	public Statement findByStatementId(long statementId);
}
