/**
 * 
 */
package com.aimdek.ccm.test.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.repositories.CreditCardRepository;
import com.aimdek.ccm.service.CreditCardService;
import com.aimdek.ccm.service.impl.CreditCardServiceImpl;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class CreditCardServiceImplTest.
 *
 * @author aimdek.team
 */
public class CreditCardServiceImplTest {
	
	/** The credit card service. */
	@InjectMocks
	private CreditCardService creditCardService = new CreditCardServiceImpl(); 
	
	/** The card repository. */
	@Mock
	private CreditCardRepository cardRepository;
	
	/**
	 * Setup.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
	}
	
	/**
	 * Test get credit card from customer id.
	 */
	@Test
	public void testGetCreditCardFromCustomerId(){
		
		long customerId = 1;
		
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
		
		Assert.assertEquals(4,creditCardService.findCreditCardsByCardHolderId(customerId).size());
		
	}
}
