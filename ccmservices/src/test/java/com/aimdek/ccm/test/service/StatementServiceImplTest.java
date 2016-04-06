package com.aimdek.ccm.test.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import com.aimdek.ccm.amazons3.service.AmazonRepositoryService;
import com.aimdek.ccm.custom.dto.TransactionReportDto;
import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.Geographic;
import com.aimdek.ccm.document.Statement;
import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.AddressRepository;
import com.aimdek.ccm.repositories.CreditCardRepository;
import com.aimdek.ccm.repositories.GeographicRepository;
import com.aimdek.ccm.repositories.StatementRepository;
import com.aimdek.ccm.repositories.TransactionRepository;
import com.aimdek.ccm.repositories.UserRepository;
import com.aimdek.ccm.service.StatementService;
import com.aimdek.ccm.service.impl.StatementServiceImpl;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class StatementServiceImplTest.
 * @author 	aimdek.team
 */
public class StatementServiceImplTest {
	
	/** The statement service. */
	@InjectMocks
	private StatementService statementService = new StatementServiceImpl();
	
	/** The card repository. */
	@Mock
	public CreditCardRepository cardRepository;
	
	/** The transaction repository. */
	@Mock
	public TransactionRepository transactionRepository;
	
	/** The statement repository. */
	@Mock
	public StatementRepository statementRepository;
	
	/** The user repository. */
	@Mock
	public UserRepository userRepository;
	
	/** The address repository. */
	@Mock
	public AddressRepository addressRepository;
	
	
	/** The geographic repository. */
	@Mock
	public GeographicRepository geographicRepository;
	
	/** The amazon repository service. */
	@Mock
	public AmazonRepositoryService amazonRepositoryService;
	
	/** The statement service impl. */
	@Mock
	public StatementServiceImpl statementServiceImpl;
	
	/**
	 * Setup.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		PowerMockito.mockStatic(CommonUtil.class);
	}
	
	/**
	 * Test generate pdf statement.
	 */
	@Ignore
	public void testGeneratePDFStatement(){
		
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
		
		when(cardRepository.findByStatementDateBetween(any(Date.class),any(Date.class))).thenReturn(creditcardList);
		
		List<Transaction> transactionList = new ArrayList<Transaction>();
		
		Transaction transaction1 = new Transaction();
		transaction1.setAmount(200);
		transaction1.setBalance(24800);
		transaction1.setCardNumber("1234-1234-1234-0008");
		transaction1.setCreditCardId(4);
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
		transaction2.setCreditCardId(5);
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
		transaction3.setCreditCardId(6);
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
		transaction4.setCreditCardId(6);
		transaction4.setCustomerName("Test3 Test3");
		transaction4.setDescription("Test5");
		transaction4.setTransactionDate(CommonUtil.toDay());
		transaction4.setTransactionId(1005);
		transaction4.setId(4);
		transactionList.add(transaction4);
		
		when(transactionRepository.findTransactionsByStatementDateAndCreditCardId(any(Date.class), any(Date.class),anyLong())).thenReturn(transactionList);
		
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
		user.setId(1);
		
		when(userRepository.findById(anyLong())).thenReturn(user);
		
		Address address = new Address();
		address.setArea("testArea");
		address.setGeoId(1);
		address.setHouseName("TestHome");
		address.setStreet("TestStreet");
		address.setUserId(user.getId());
		address.setId(1);
		
		when(addressRepository.findByUserId(anyLong())).thenReturn(address);
		
		Geographic geographic = new Geographic();
		
		geographic.setZipcode(380015);
		geographic.setCountry("India");
		geographic.setState("Gujarat");
		geographic.setCity("Ahmedabad");
		geographic.setId(1);
		
		when(geographicRepository.findById(anyLong())).thenReturn(geographic);
		
		doNothing().when(statementRepository).save(any(Statement.class));
		
		doNothing().when(cardRepository).save(any(CreditCard.class));
		
		when(statementRepository.findLastStatement()).thenReturn(null);
		
		when(statementServiceImpl.generatePdfReport(anyListOf(TransactionReportDto.class),anyString(),anyLong(),anyString(), anyDouble(),anyDouble(),any(Date.class),anyString(),anyString())).thenReturn("test");
		
		PowerMockito.when(CommonUtil.getResourceFullFilePath((Class<?>) any(),anyString())).thenReturn(getClass().getResource("/statement_test.jasper").getPath());
		
		doNothing().when(amazonRepositoryService).uploadFileToAmazonS3Server(any(File.class),anyString());
		
		statementService.generateMonthlyStatement();
		
		verify(statementRepository,times(creditcardList.size())).save(any(Statement.class));
		
		
	}
	
}
	
