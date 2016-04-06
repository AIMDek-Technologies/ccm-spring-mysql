/**
 * 
 */
package com.aimdek.ccm.service;

import com.aimdek.ccm.document.User;

/**
 * The Interface MailSenderService.
 *
 * @author aimdek.team
 */
public interface MailSenderService {

	
	/**
	 * Send customer registration email.
	 *
	 * @param user the user
	 * @param password the password
	 */
	public void sendCustomerRegistrationEmail(User user, String password);
}
