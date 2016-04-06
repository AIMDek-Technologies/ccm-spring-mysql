/**
 * 
 */
package com.aimdek.ccm.test.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aimdek.ccm.amazons3.service.AmazonRepositoryService;
import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.AddressRepository;
import com.aimdek.ccm.repositories.CreditCardRepository;
import com.aimdek.ccm.repositories.TransactionRepository;
import com.aimdek.ccm.repositories.UserRepository;
import com.aimdek.ccm.service.UserService;
import com.aimdek.ccm.service.impl.UserServiceImpl;
import com.aimdek.ccm.util.CCMConstant;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class UserServiceImplTest.
 *
 * @author aimdek.team
 */
public class UserServiceImplTest {
	

	/** The user service. */
	@InjectMocks
	public UserService userService = new UserServiceImpl();
	
	/** The user. */
	private static User user = new User();
	
	/** The user service mock. */
	@Mock
	public UserService userServiceMock;
	
	/** The user repository. */
	@Mock
	public UserRepository userRepository;
	
	/** The address repository. */
	@Mock
	public AddressRepository addressRepository;
	
	/** The card repository. */
	@Mock
	public CreditCardRepository cardRepository;
	
	/** The transaction repository. */
	@Mock
	public TransactionRepository transactionRepository;
	
	/** The amazon repository service. */
	@Mock
	public AmazonRepositoryService amazonRepositoryService;
	
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User();
		user.setId(2);
		user.setBirthDate(CommonUtil.toDay());
		user.setCreatedBy(1);
		user.setCustomerId(1000);
		user.setEmail("tapan.parikh@aimdek.com");
		user.setFirstName("Tapan");
		user.setLastName("Parikh");
		user.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user.setPhoneNumber("(942)-852-2343");
		user.setRole("CUSTOMER");
	}

	/**
	 * Tear down after class.
	 *
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}
	
	
	/**
	 * Test create customer id.
	 */
	@Test
	public void testCreateCustomerID(){
		
		when(userRepository.findLastCustomer()).thenReturn(user);
		
		Assert.assertEquals(1001,userService.nextCustomerId());
	}
	
	/**
	 * Test create customer id null.
	 */
	@Test
	public void testCreateCustomerIdNull(){
		
		User user = null;
		
		when(userRepository.findLastCustomer()).thenReturn(user);
		
		Assert.assertEquals(1000,userService.nextCustomerId());
	}
	
	/**
	 * Test get user by email.
	 */
	@Test
	public void testGetUserByEmail(){
		
		String email ="tapan.parikh@aimdek.com";
		
		when(userRepository.findByEmail(email)).thenReturn(user);
		
		Assert.assertTrue(user != null && user.getId() > 0);
	}
	
	/**
	 * Test get user by email null.
	 */
	@Test
	public void testGetUserByEmailNull(){
		
		String email ="ravi@aimdek.com";
		
		User user = null;
		
		when(userRepository.findByEmail(anyString())).thenReturn(user);
		
		Assert.assertEquals(user, userRepository.findByEmail(email));
	}
	
	/**
	 * Test get user by user id.
	 */
	@Test
	public void testGetUserByUserId(){
		
		long id =2;
		
		when(userRepository.findById(anyLong())).thenReturn(user);
		
		Assert.assertEquals(user, userRepository.findById(id));
	}
	
	/**
	 * Test get user by user id null.
	 */
	@Test
	public void testGetUserByUserIdNull(){
		
		String id ="";
		
		User user = null;
		
		when(userRepository.findByEmail(anyString())).thenReturn(user);
		
		Assert.assertEquals(user, userRepository.findByEmail(id));
	}
	
	/**
	 * Test get customer list.
	 */
	@Test
	public void testGetCustomerList(){
		
		List<User> userList = new ArrayList<User>();
		
		userList.add(user);
		
		User user2 = new User();
		
		user2.setId(3);
		user2.setBirthDate(CommonUtil.toDay());
		user2.setCreatedBy(1);
		user2.setCustomerId(1000);
		user2.setEmail("nikunj.malaviya@aimdek.com");
		user2.setFirstName("Nikunj");
		user2.setLastName("Patel");
		user2.setPassword("$2a$10$Au5AkI3KUAXDtnifn5zVTOmvxvpM51xfCAt00kR48On1yPL3jF9Ly");
		user2.setPhoneNumber("(794)-564-6413");
		user2.setRole("CUSTOMER");
		user2.setCustomerId(1002);
		
		userList.add(user2);
		
		when(userRepository.getCustomers(anyInt(),anyInt(),anyString(),anyString(),anyMapOf(String.class,Object.class))).thenReturn(userList);
		List<User> user = userRepository.getCustomers(0,5,null,CCMConstant.SORT_ORDER_ASCENDING,new HashMap<String, Object>());
		Assert.assertEquals(userList,user);
		Assert.assertTrue(user.size()> 0);
		Assert.assertNotNull(user);
		for(User newUser : user)
		{
			Assert.assertNotNull(newUser.getId());
		}
	}
	
	/**
	 * Test delete customer data.
	 */
	@Test
	public void testDeleteCustomerData(){
		
		long customerId = 1;
		
		when(userRepository.findById(customerId)).thenReturn(user);
		
		List<CreditCard> creditcardList = new ArrayList<CreditCard>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		CreditCard creditCard1 = new CreditCard();
		creditCard1.setAvailableCreditLimit(15000);
		creditCard1.setCardNumber("2345-1234-2345-0009");
		creditCard1.setCreditLimit(20000);
		creditCard1.setCvv(420);
		creditCard1.setLastStatementDate(calendar.getTime());
		creditCard1.setStatementDate(CommonUtil.toDay());
		creditCard1.setNameOnCard("Test2 Test2");
		creditCard1.setCardHolderId(1);
		creditCard1.setValidFromDate(CommonUtil.toDay());
		creditCard1.setValidToDate(CommonUtil.toDay());
		creditCard1.setId(1);
		creditcardList.add(creditCard1);
		
		CreditCard creditCard2 = new CreditCard();
		creditCard2.setAvailableCreditLimit(15000);
		creditCard2.setCardNumber("1234-1234-1234-0010");
		creditCard2.setCreditLimit(20000);
		creditCard2.setCvv(420);
		creditCard2.setLastStatementDate(calendar.getTime());
		creditCard2.setStatementDate(CommonUtil.toDay());
		creditCard2.setNameOnCard("Test3 Test3");
		creditCard2.setCardHolderId(2);
		creditCard2.setValidFromDate(CommonUtil.toDay());
		creditCard2.setValidToDate(CommonUtil.toDay());
		creditCard2.setId(2);
		creditcardList.add(creditCard2);
		
		CreditCard creditCard3 = new CreditCard();
		creditCard3.setAvailableCreditLimit(15000);
		creditCard3.setCardNumber("2345-1234-2345-0011");
		creditCard3.setCreditLimit(20000);
		creditCard3.setCvv(420);
		creditCard3.setLastStatementDate(calendar.getTime());
		creditCard3.setStatementDate(CommonUtil.toDay());
		creditCard3.setNameOnCard("Test4 Test4");
		creditCard3.setCardHolderId(3);
		creditCard3.setValidFromDate(CommonUtil.toDay());
		creditCard3.setValidToDate(CommonUtil.toDay());
		creditCard3.setId(3);
		creditcardList.add(creditCard3);
		
		CreditCard creditCard4 = new CreditCard();
		creditCard4.setAvailableCreditLimit(15000);
		creditCard4.setCardNumber("1234-1234-1234-0012");
		creditCard4.setCreditLimit(20000);
		creditCard4.setCvv(420);
		creditCard4.setLastStatementDate(calendar.getTime());
		creditCard4.setStatementDate(CommonUtil.toDay());
		creditCard4.setNameOnCard("Test3 Test3");
		creditCard4.setCardHolderId(3);
		creditCard4.setValidFromDate(CommonUtil.toDay());
		creditCard4.setValidToDate(CommonUtil.toDay());
		creditCard4.setId(4);
		creditcardList.add(creditCard4);
		
		when(cardRepository.findByCardHolderId(customerId)).thenReturn(creditcardList);
		
		List<Transaction> transactionList = new ArrayList<Transaction>();
		
		Transaction transaction1 = new Transaction();
		transaction1.setAmount(200);
		transaction1.setBalance(24800);
		transaction1.setCardNumber("1234-1234-1234-0008");
		transaction1.setCreditCardId(1);
		transaction1.setCustomerName("Test1 Test1");
		transaction1.setDescription("Test2");
		transaction1.setTransactionDate(CommonUtil.toDay());
		transaction1.setTransactionId(1002);
		transaction1.setId(1);
		transactionList.add(transaction1);
		
		Transaction transaction2 = new Transaction();
		transaction2.setAmount(200);
		transaction2.setBalance(24800);
		transaction2.setCardNumber("1234-1234-1234-0009");
		transaction2.setCreditCardId(2);
		transaction2.setCustomerName("Test2 Test2");
		transaction2.setDescription("Test3");
		transaction2.setTransactionDate(CommonUtil.toDay());
		transaction2.setTransactionId(1003);
		transaction2.setId(2);
		transactionList.add(transaction2);
		
		Transaction transaction3 = new Transaction();
		transaction3.setAmount(200);
		transaction3.setBalance(24800);
		transaction3.setCardNumber("1234-1234-1234-0010");
		transaction3.setCreditCardId(3);
		transaction3.setCustomerName("Test3 Test3");
		transaction3.setDescription("Test4");
		transaction3.setTransactionDate(CommonUtil.toDay());
		transaction3.setTransactionId(1004);
		transaction3.setId(3);
		transactionList.add(transaction3);
		
		Transaction transaction4 = new Transaction();
		transaction4.setAmount(200);
		transaction4.setBalance(24800);
		transaction4.setCardNumber("1234-1234-1234-0010");
		transaction4.setCreditCardId(3);
		transaction4.setCustomerName("Test3 Test3");
		transaction4.setDescription("Test5");
		transaction4.setTransactionDate(CommonUtil.toDay());
		transaction4.setTransactionId(1005);
		transaction4.setId(4);
		transactionList.add(transaction4);
		
		when(transactionRepository.findByCreditCardId(anyLong())).thenReturn(transactionList);
		
		Address address = new Address();
		address.setArea("testArea");
		address.setGeoId(1);
		address.setHouseName("TestHome");
		address.setStreet("TestStreet");
		address.setUserId(user.getId());
		address.setId(1);
		
		when(addressRepository.findByUserId(customerId)).thenReturn(address);
		
		userService.removeCustomer(customerId);
		
		verify(userRepository,times(1)).findById(anyLong());
		
		verify(cardRepository,times(1)).findByCardHolderId(anyLong());
		
		verify(cardRepository,times(4)).delete(any(CreditCard.class));
		
		verify(transactionRepository,times(4)).findByCreditCardId(anyLong());
		
		verify(transactionRepository,times(16)).delete(any(Transaction.class));
		
		verify(addressRepository,times(1)).delete(any(Address.class));
	}
}
