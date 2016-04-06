package com.aimdek.ccm.service;

import com.aimdek.ccm.document.Address;

/**
 * The Interface AddressService.
 * @author aimdek.team
 */
public interface AddressService {
	
	/**
	 * Save address.
	 *
	 * @param address the address
	 */
	public void saveAddress(Address address);
	
	/**
	 * Find address by user id.
	 *
	 * @param userId the user id
	 * @return the address
	 */
	public Address findAddressByUserId(long userId);
}
