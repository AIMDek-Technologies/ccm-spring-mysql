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

import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_COUNTRY;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_STATE;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_ZIPCODE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aimdek.ccm.JPATestConfig;
import com.aimdek.ccm.dao.impl.BasicAbstractGenericDaoImpl;
import com.aimdek.ccm.document.Geographic;
import com.aimdek.ccm.repositories.GeographicRepository;

/**
 * The Class GeographicDaoImplTest.
 *
 * @author aimdek.team
 */
@ContextConfiguration(classes = { JPATestConfig.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class GeographicDaoImplTest extends BasicAbstractGenericDaoImpl<Geographic, String> {

	/** The geographic repository. */
	private GeographicRepository geographicRepository;

	/**
	 * Sets the geographic repository.
	 *
	 * @param geographicRepository
	 *            the new geographic repository
	 */
	@Autowired
	public void setGeographicRepository(GeographicRepository geographicRepository) {
		this.geographicRepository = geographicRepository;
	}

	/**
	 * Initialize.
	 */
	@Before
	public void initialize() {

		List<Geographic> geoList = new ArrayList<Geographic>();

		Geographic geographic = new Geographic();

		geographic.setZipcode(380015);
		geographic.setCountry("India");
		geographic.setState("Gujarat");
		geographic.setCity("Ahmedabad");
		geoList.add(geographic);

		Geographic geographic1 = new Geographic();

		geographic1.setZipcode(480015);
		geographic1.setCountry("India");
		geographic1.setState("Maharastra");
		geographic1.setCity("Mumbai");
		geoList.add(geographic1);

		Geographic geographic2 = new Geographic();

		geographic2.setZipcode(580015);
		geographic2.setCountry("India");
		geographic2.setState("Punjab");
		geographic2.setCity("Amritsar");
		geoList.add(geographic2);

		geographicRepository.save(geoList);
		geographicRepository.findAll().size();
	}

	/**
	 * Test get all geographic list.
	 */
	@Test
	public void testGetAllGeographicList() {

		List<Geographic> geList = geographicRepository.findAll();

		assertEquals(3, geList.size());
	}

	/**
	 * Test get state list from country.
	 */
	@Test
	public void testGetStateListFromCountry() {

		String country = "India";

		List<Geographic> geList = super.findExactList(FIELDCONSTANT_COUNTRY, country, Geographic.class);

		assertEquals(3, geList.size());
	}

	/**
	 * Test get city list from state.
	 */
	@Test
	public void testGetCityListFromState() {

		String state = "Gujarat";

		List<Geographic> geList = super.findExactList(FIELDCONSTANT_STATE, state, Geographic.class);

		assertEquals(1, geList.size());
	}

	/**
	 * Test get geo from zip.
	 */
	@Test
	public void testGetGeoFromZip() {

		long zipCode = 380015;

		Geographic ge = super.findExact(FIELDCONSTANT_ZIPCODE, zipCode, Geographic.class);

		assertNotNull(ge);
	}

	/**
	 * Test save geographic.
	 */
	@Test
	public void testSaveGeographic() {

		Geographic geographic = new Geographic();

		geographic.setZipcode(680015);
		geographic.setCountry("India");
		geographic.setState("Rajasthan");
		geographic.setCity("Jaipur");

		geographicRepository.saveGeographic(geographic);

		assertNotNull(geographic.getId());
	}
}
