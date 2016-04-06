/**
 * 
 */
package com.aimdek.ccm.test.service;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.repositories.TransactionRepository;
import com.aimdek.ccm.service.TransactionService;
import com.aimdek.ccm.service.impl.TransactionServiceImpl;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class TransactionServiceImplTest.
 *
 * @author aimdek.team
 */
public class TransactionServiceImplTest {
	
	/** The transaction service. */
	@InjectMocks
	public TransactionService transactionService = new TransactionServiceImpl();
	
	/** The transaction repository. */
	@Mock
	public TransactionRepository transactionRepository;
	
	/** The transaction service test. */
	@Mock
	public TransactionService transactionServiceTest;
	
	/** The transaction. */
	public Transaction transaction = new Transaction();
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		
		transaction.setAmount(1200);
		transaction.setBalance(23800);
		transaction.setCardNumber("1234-1234-1234-0008");
		transaction.setCreditCardId(1);
		transaction.setCustomerName("Test1 Test1");
		transaction.setDescription("Test1");
		transaction.setTransactionDate(CommonUtil.toDay());
		transaction.setTransactionId(1001);
		transaction.setId(1);
		
	}
	
	/**
	 * Test save card transactions without id.
	 */
	@Test
	public void testSaveCardTransactionsWithoutId(){
		
		doNothing().when(transactionRepository).saveTransaction(transaction);
		
		when(transactionRepository.findLastTransaction()).thenReturn(transaction);
		
		transactionService.saveTransaction(transaction);
		
		verify(transactionRepository,times(0)).findLastTransaction();
		
	}
	
	
	/**
	 * Test save card transactions with id.
	 */
	@Test
	public void testSaveCardTransactionsWithId(){
		
		
		transaction.setTransactionId(0);
		
		when(transactionRepository.save(transaction)).thenReturn(transaction);
		
		when(transactionRepository.findLastTransaction()).thenReturn(transaction);
		
		transactionService.saveTransaction(transaction);
		
		verify(transactionRepository,times(1)).findLastTransaction();
		
	}
	
	/**
	 * Test remove transaction.
	 */
	@Test
	public void testRemoveTransaction(){
		
		when(transactionRepository.findById(anyLong())).thenReturn(transaction);
		
		doNothing().when(transactionRepository).delete(transaction);
		
		transactionService.removeTransaction(anyLong());
		
		verify(transactionRepository,times(1)).delete(transaction);
		
	}
	
	/**
	 * Test remove transaction null.
	 */
	@Test
	public void testRemoveTransactionNull(){
		
		when(transactionRepository.findById(anyLong())).thenReturn(null);
		
		doNothing().when(transactionRepository).delete(transaction);
		
		transactionService.removeTransaction(anyLong());
		
		verify(transactionRepository,times(0)).delete(transaction);
		
	}
	
	/**
	 * Test next transaction id.
	 */
	@Test
	public void testNextTransactionId(){
		
		when(transactionRepository.findLastTransaction()).thenReturn(transaction);
		
		Assert.assertEquals(1002,transactionService.nextTransactionId());
	}
	
	/**
	 * Test next transaction id null.
	 */
	@Test
	public void testNextTransactionIdNull(){
		
		when(transactionRepository.findLastTransaction()).thenReturn(null);
		
		Assert.assertEquals(1000,transactionService.nextTransactionId());
	}
}
