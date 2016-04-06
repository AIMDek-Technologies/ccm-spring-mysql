/**
 * 
 */
package com.aimdek.ccm.test.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.repositories.AddressRepository;
import com.aimdek.ccm.service.AddressService;
import com.aimdek.ccm.service.impl.AddressServiceImpl;

/**
 * The Class AddressServiceImplTest.
 *
 * @author aimdek.team
 */
public class AddressServiceImplTest {
	
	/** The address service. */
	@InjectMocks
	private AddressService addressService = new AddressServiceImpl();
	
	/** The address repository. */
	@Mock
	private AddressRepository addressRepository;
	
	/** The address. */
	private Address address = new Address();
	
	/**
	 * Setup address object.
	 */
	@Before
	public void setUp()  {
		
		MockitoAnnotations.initMocks(this);
		
		address.setArea("testArea");
		address.setGeoId(1);
		address.setHouseName("TestHome");
		address.setStreet("TestStreet");
		address.setUserId(1);
	}
	
	/**
	 * Test save user address not null.
	 */
	@Test
	public void testSaveUserAddressNotNull(){
		
		doNothing().when(addressRepository).saveAddress(address);
		
		addressService.saveAddress(address);
		
		verify(addressRepository,times(1)).saveAddress(address);
	}
	
	/**
	 * Test save user address null.
	 */
	@Test
	public void testSaveUserAddressNull(){
		
		address.setUserId(0);
		
		doNothing().when(addressRepository).saveAddress(address);
		
		addressService.saveAddress(address);
		
		verify(addressRepository,times(0)).saveAddress(address);
		
	}
	

}
