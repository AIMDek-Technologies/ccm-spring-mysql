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
package com.aimdek.ccm.dao.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.JPATestConfig;
import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.document.Geographic;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.AddressRepository;
import com.aimdek.ccm.repositories.GeographicRepository;
import com.aimdek.ccm.repositories.UserRepository;

/**
 * The Class AddressDaoImplTest.
 * 
 * @author aimdek.team
 */
@ContextConfiguration(classes = { JPATestConfig.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressDaoImplTest {

	/** The user id. */
	private long userId;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The geographic repository. */
	@Autowired
	private GeographicRepository geographicRepository;

	/**
	 * initialize.
	 */
	@Before
	public void initialize() {

		User user = new User();

		user.setBirthDate(new Date());
		user.setCreatedBy(1);
		user.setCustomerId(1000);
		user.setEmail("test.test@aimdek.com");
		user.setFirstName("Test0");
		user.setLastName("Test0");
		user.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user.setPhoneNumber("(111)-111-1111");
		user.setRole("CUSTOMER");
		userRepository.saveUser(user);
		userId = user.getId();

		Geographic geographic = new Geographic();

		geographic.setZipcode(380015);
		geographic.setCountry("India");
		geographic.setState("Gujarat");
		geographic.setCity("Ahmedabad");
		geographicRepository.saveGeographic(geographic);

		Address address = new Address();
		address.setArea("testArea");
		address.setGeoId(geographic.getId());
		address.setHouseName("TestHome");
		address.setStreet("TestStreet");
		address.setUserId(user.getId());
		addressRepository.saveAddress(address);

	}

	/**
	 * Test save address.
	 */
	@Test
	public void testSaveAddress() {

		Address address = new Address();
		address.setArea("testArea");
		address.setGeoId(1);
		address.setHouseName("Test1Home");
		address.setStreet("TestS1treet");
		address.setUserId(userId);

		addressRepository.saveAddress(address);

		assertNotNull(address.getId());
	}

	/**
	 * Test get address from user id.
	 */
	@Test
	public void testGetAddressFromUserId() {

		Address address = addressRepository.findByUserId(userId);

		assertEquals(userId, address.getUserId());
	}

	/**
	 * Test fail get address from user id.
	 */
	@Test
	public void testFailGetAddressFromUserId() {

		long nonValidId = -1;

		Address address = addressRepository.findByUserId(nonValidId);

		assertNull(address);
	}

	/**
	 * Test remove user address.
	 */
	@Test(expected = EntityNotFoundException.class)
	public void testRemoveUserAddress() {

		Address address = addressRepository.findByUserId(userId);

		long id = address.getId();

		addressRepository.remove(address);

		addressRepository.getOne(id);

	}
}
