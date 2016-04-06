/**
 * 
 */
package com.aimdek.ccm.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.aimdek.ccm.service.StatementService;

/**
 * The Class MonthlyStatementScheduler.
 *
 * @author aimdek.team
 */
public class MonthlyStatementScheduler extends QuartzJobBean {
	
	
	/** The statement service. */
	private StatementService statementService;
	

	/**
	 * Sets the statement service.
	 *
	 * @param statementService the statementService to set
	 */
	public void setStatementService(StatementService statementService) {
		this.statementService = statementService;
	}

	/**
	 * Execute internal.
	 *
	 * @param jobExecutionContext the job execution context
	 * @throws JobExecutionException the job execution exception
	 */
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		statementService.generateMonthlyStatement();
		
	}

}

