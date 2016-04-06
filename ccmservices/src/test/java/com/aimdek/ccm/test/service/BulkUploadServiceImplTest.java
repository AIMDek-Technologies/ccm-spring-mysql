/**
 * 
 */
package com.aimdek.ccm.test.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.document.BulkUpload;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.Geographic;
import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.AddressRepository;
import com.aimdek.ccm.repositories.BulkUploadRepository;
import com.aimdek.ccm.repositories.CreditCardRepository;
import com.aimdek.ccm.repositories.GeographicRepository;
import com.aimdek.ccm.repositories.UserRepository;
import com.aimdek.ccm.service.BulkUploadService;
import com.aimdek.ccm.service.TransactionService;
import com.aimdek.ccm.service.UserService;
import com.aimdek.ccm.service.impl.BulkUploadServiceImpl;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class BulkUploadServiceImplTest.
 *
 * @author aimdek.team
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommonUtil.class)
public class BulkUploadServiceImplTest {
	
	/** The bulk upload service. */
	@InjectMocks
	private BulkUploadService bulkUploadService = new BulkUploadServiceImpl();

	/** The customer file name. */
	private String custFileName = "customers.csv";
	
	/** The transaction file name. */
	private String transFileName = "transactions.xlsx";
	
	/** The customer content type. */
	private String custContentType = "text/csv";
	
	/** The transaction content type. */
	private String transContentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	/** The customer input stream. */
	private InputStream custInputStream;
	
	/** The transaction input stream. */
	private InputStream transInputStream;
	
	/** The bulk upload service test. */
	@Mock
	private BulkUploadServiceImpl bulkUploadServiceTest;
	
	/** The user repository. */
	@Mock
	private UserRepository userRepository;
	
	/** The bulk upload repository. */
	@Mock
	private BulkUploadRepository bulkUploadRepository;
	
	/** The users service. */
	@Mock
	private UserService usersService;
	
	/** The geographic repository. */
	@Mock
	private GeographicRepository geographicRepository;
	
	/** The card repository. */
	@Mock
	private CreditCardRepository cardRepository;
	
	/** The address repository. */
	@Mock
	private AddressRepository addressRepository;
	
	
	/** The transaction service. */
	@Mock
	private TransactionService transactionService;
	
	/**
	 * Setup inputstream for customer and transaction.
	 */
	@Before
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		
		PowerMockito.mockStatic(CommonUtil.class);
		
		String fullPathCust = getClass().getResource("/customers.csv").getPath();
		
		File customerFile = new File(fullPathCust);
		
		String fullPathTrans = getClass().getResource("/transactions.xlsx").getPath();
		
		File transactionFile = new File(fullPathTrans);
		
		try {
			custInputStream = new FileInputStream(customerFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			transInputStream = new FileInputStream(transactionFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Test upload customer.
	 */
	@Test
	public void testUploadCustomer(){
		
		User user = new User();
		user.setBirthDate(CommonUtil.toDay());
		user.setCreatedBy(1);
		user.setCustomerId(1000);
		user.setEmail("test.test@aimdek.com");
		user.setFirstName("Test0");
		user.setLastName("Test0");
		user.setPassword("$2a$10$Xlod8V5YtOpJB4jqmky1BOLJnRVd6.VvnaOlTW1l9LXJmrwnFDjf2");
		user.setPhoneNumber("(111)-111-1111");
		user.setRole("CUSTOMER");
		
		Geographic geographic = new Geographic();
		
		geographic.setZipcode(380015);
		geographic.setCountry("India");
		geographic.setState("Gujarat");
		geographic.setCity("Ahmedabad");
		
		when(geographicRepository.findByZipcode(anyLong())).thenReturn(geographic);
		
		doNothing().when(geographicRepository).saveGeographic(any(Geographic.class));
		
		doNothing().when(addressRepository).saveAddress(any(Address.class));
		
		doNothing().when(cardRepository).saveCreditCard(any(CreditCard.class));
		
		doNothing().when(usersService).saveUser(any(User.class));
		
		when(userRepository.findById(anyLong())).thenReturn(user);
		
		when(bulkUploadRepository.findLastBulkUpload()).thenReturn(new BulkUpload());
		
		doNothing().when(bulkUploadRepository).saveBulkUpload(any(BulkUpload.class));
		
		PowerMockito.when(CommonUtil.checkEmailPattern(anyString())).thenReturn(true);
		
		PowerMockito.when(CommonUtil.isNotNull(any(File.class))).thenReturn(true);
		
		PowerMockito.when(CommonUtil.isNotNull(anyString())).thenReturn(true);
		
		PowerMockito.when(CommonUtil.toDay()).thenReturn(new Date());
		
		Assert.assertEquals("Total 5 records saved successfully out of 5 records",bulkUploadService.handleBulkUpload(custFileName, custContentType, custInputStream, true, user.getId()));
		
	}
	
	/**
	 * Test upload transaction.
	 */
	@Test
	public void testUploadTransaction(){
		
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
		user.setId(1);
		
		CreditCard creditCard = new CreditCard();
		
		creditCard.setAvailableCreditLimit(15000);
		creditCard.setCardNumber("1234-1234-1234-0008");
		creditCard.setCreditLimit(20000);
		creditCard.setCvv(420);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		creditCard.setLastStatementDate(calendar.getTime());
		creditCard.setStatementDate(new Date());
		creditCard.setNameOnCard("Test1 Test1");
		creditCard.setCardHolderId(1);
		creditCard.setValidFromDate(new Date());
		creditCard.setValidToDate(new Date());
		creditCard.setId(1);
		
		
		doNothing().when(cardRepository).saveCreditCard(any(CreditCard.class));
		
		when(cardRepository.findByCardNumber(anyString())).thenReturn(creditCard);
		
		when(userRepository.findById(anyLong())).thenReturn(user);
		
		when(bulkUploadRepository.findLastBulkUpload()).thenReturn(new BulkUpload());
		
		doNothing().when(bulkUploadRepository).saveBulkUpload(any(BulkUpload.class));
		
		doNothing().when(transactionService).saveTransaction(any(Transaction.class));
		
		PowerMockito.when(CommonUtil.getFormatedNumber(anyDouble())).thenReturn(100.12);
		
		PowerMockito.when(CommonUtil.isNotNull(any(File.class))).thenReturn(true);
		
		PowerMockito.when(CommonUtil.isNotNull(any(CreditCard.class))).thenReturn(true);
		
		PowerMockito.when(CommonUtil.isNotNull(anyString())).thenReturn(true);
		
		PowerMockito.when(CommonUtil.isNotNull(anyLong())).thenReturn(true);
		
		PowerMockito.when(CommonUtil.toDay()).thenReturn(new Date());	
		
		Assert.assertEquals("Total 8 records saved successfully out of 8 records",bulkUploadService.handleBulkUpload(transFileName,transContentType,transInputStream, false, user.getId()));
		
	}
}
