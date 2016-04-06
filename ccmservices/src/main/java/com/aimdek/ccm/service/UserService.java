package com.aimdek.ccm.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.aimdek.ccm.custom.dto.CustomerDto;
import com.aimdek.ccm.document.User;


/**
 * The Interface UsersService.
 * @author aimdek.team
 */
public interface UserService {
	
	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findUserByEmail(String email);
	
	/**
	 * Find user by id.
	 *
	 * @param Id the id
	 * @return the user
	 */
	public User findUserById(long Id);
	
	/**
	 * Save user.
	 *
	 * @param user the user
	 */
	public void saveUser(User user);
	
	/**
	 * Gets the customers.
	 *
	 * @param start the start
	 * @param end the end
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters the filters
	 * @param creditCardReqired the credit card reqired
	 * @return the customer list
	 */
	public List<CustomerDto> getCustomers(int start,int end,String sortField, String sortOrder, Map<String,Object> filters, boolean creditCardReqired);
	
	/**
	 * Gets the customers count.
	 *
	 * @param filters the filters
	 * @return the long
	 */
	public long getCustomersCount(Map<String,Object> filters);
	
	/**
	 * Next customer id.
	 *
	 * @return the long
	 */
	public long nextCustomerId();
	
	/**
	 * Gets the customers.
	 *
	 * @return the list
	 */
	public List<User> getCustomers();
	
	/**
	 * Removes the customer.
	 *
	 * @param customerId the customer id
	 */
	public void removeCustomer(long customerId);
	
	/**
	 * Upload profile picture.
	 *
	 * @param customerId the customer id
	 * @param file the file
	 * @return the string
	 */
	public String uploadProfilePicture(long customerId,File file);
	
	/**
	 * Gets the assigned url.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String getAssignedUrl(String key);

	/**
	 * Delete file from store.
	 *
	 * @param key the key
	 */
	public void deleteFileFromStore(String key);
	
	/**
	 * Check duplicate email address.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean checkDuplicateEmailAddress(User user);
	
	/**
	 * Update customer credit card flag.
	 *
	 * @param customerId the customer id
	 * @param hasCreditCard the has credit card
	 */
	public void updateCustomerCreditCardFlag(long customerId, boolean hasCreditCard);
	
	/**
	 * Generate secured password.
	 *
	 * @param password the password
	 * @return the string
	 */
	public String generateSecuredPassword(String password);
	
}
