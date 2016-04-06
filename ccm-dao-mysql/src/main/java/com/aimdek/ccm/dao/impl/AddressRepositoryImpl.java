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

package com.aimdek.ccm.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.dao.AddressRepositoryCustom;
import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.repositories.AddressRepository;

/**
 * The Class AddressDaoImpl provides implementation of the interface AddressDao.
 *
 * @author aimdek.team
 */
@Transactional
public class AddressRepositoryImpl extends BasicAbstractGenericDaoImpl<Address, String> implements AddressRepositoryCustom {

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * {@inheritDoc}
	 */
	public void saveAddress(Address address) {
		addressRepository.save(address);
	}

}
