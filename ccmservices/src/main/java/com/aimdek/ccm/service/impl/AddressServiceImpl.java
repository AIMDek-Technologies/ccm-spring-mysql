/**
 * 
 */
package com.aimdek.ccm.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.repositories.AddressRepository;
import com.aimdek.ccm.service.AddressService;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class AddressServiceImpl.
 *
 * @author aimdek.team
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1412004612139878994L;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/**
	 * {@inheritDoc}
	 */
	public void saveAddress(Address address) {
		if (CommonUtil.isNotNull(address.getUserId())) {
			addressRepository.saveAddress(address);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Address findAddressByUserId(long userId) {

		Address address = addressRepository.findByUserId(userId);

		if (CommonUtil.isNull(address)) {
			address = new Address();
		}
		return address;
	}

}
