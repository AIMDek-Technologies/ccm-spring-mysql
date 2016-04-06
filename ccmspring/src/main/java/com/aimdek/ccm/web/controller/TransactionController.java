package com.aimdek.ccm.web.controller;

import static com.aimdek.ccm.util.CCMConstant.DATE_FORMAT;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.dto.DataTableDTO;
import com.aimdek.ccm.service.CreditCardService;
import com.aimdek.ccm.service.TransactionService;
import com.aimdek.ccm.service.UserService;
import com.aimdek.ccm.util.CCMConstant;
import com.aimdek.ccm.util.CommonUtil;
import com.aimdek.ccm.util.TransactionValidator;

/**
 * The Class TransactionController.
 *
 * @author aimdek.team
 */
@Controller
@SessionAttributes("transactionForm")
@RequestMapping(value = "transactions")
public class TransactionController {

	/** The Constant PER_PAGE_OBJECT. */
	public static final int PER_PAGE_OBJECT = 10;

	/** The column name. */
	private String COLUMN_NAME;

	/** The direction. */
	private String DIRECTION;

	/** The initial. */
	private int INITIAL;

	/** The record size. */
	private int RECORD_SIZE;

	/** The description search term. */
	private String CUSTOMER_NAME_SEARCH_TERM, CARD_NUMBER_SEARCH_TERM, DESCRIPTION_SEARCH_TERM;

	/** The transactions. */
	private List<Transaction> transactions = new ArrayList<Transaction>();

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/** The credit card service. */
	@Autowired
	private CreditCardService creditCardService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The transaction validator. */
	@Autowired
	private TransactionValidator transactionValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * Display all customers.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String displayAllCustomers(ModelMap model) {
		model.put("page", "transactions");
		return "transactionList";
	}

	/**
	 * Gets the credit cards.
	 *
	 * @param id
	 *            the id
	 * @return the credit cards
	 */
	@RequestMapping(value = "getCreditCards", produces = "application/json")
	public @ResponseBody List<CreditCard> getCreditCards(@RequestParam(value = "customerId", required = true) long id) {
		return creditCardService.findCreditCardsByCardHolderId(id);
	}

	/**
	 * Addtransaction.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "addTransaction", method = RequestMethod.GET)
	public String addtransaction(ModelMap model) {
		model.put("transactionForm", new Transaction());
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User ccmUser = userService.findUserById(Long.parseLong(user.getUsername()));
		List<User> customerList = new ArrayList<User>();
		if (CommonUtil.isNotNull(ccmUser) && ccmUser.getRole().equals(CCMConstant.ROLE_CUSTOMER)) {
			customerList.add(ccmUser);
		}else {
			customerList = userService.getCustomers();
		}
		model.put("customerList", customerList);
		return "addtransaction";
	}

	/**
	 * Save transaction.
	 *
	 * @param transaction
	 *            the transactions
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param modelMap
	 *            the model map
	 * @return the string
	 */
	@RequestMapping(value = "saveTransaction", method = RequestMethod.POST)
	public String saveTransaction(@ModelAttribute(value = "transactionForm") Transaction transaction, BindingResult result, SessionStatus status, ModelMap modelMap) {
		transactionValidator.validate(transaction, result);

		// Check validation errors
		if (result.hasErrors()) {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User ccmUser = userService.findUserById(Long.parseLong(user.getUsername()));
			List<User> customerList = new ArrayList<User>();
			if (CommonUtil.isNotNull(ccmUser) && ccmUser.getRole().equals(CCMConstant.ROLE_CUSTOMER)) {
				customerList.add(ccmUser);
			}else {
				customerList = userService.getCustomers();
			}
			modelMap.put("customerList", customerList);
			modelMap.put("error", CCMConstant.TRUE);
			return "addtransaction";
		}

		User ccmUser = userService.findUserById(Long.parseLong(transaction.getCustomerName()));
		transaction.setCustomerName(ccmUser.getFullName());

		double formattedAmount = CommonUtil.getFormatedNumber(transaction.getAmount());
		transaction.setAmount(formattedAmount);

		CreditCard creditCard = creditCardService.findCreditCardById(transaction.getCreditCardId());

		double balance = CommonUtil.getFormatedNumber(creditCard.getAvailableCreditLimit() - formattedAmount);
		transaction.setBalance(balance);
		transaction.setTransactionId(transactionService.nextTransactionId());
		transaction.setCardNumber(creditCard.getCardNumber());
		transaction.setUserId(ccmUser.getId());

		transactionService.saveTransaction(transaction);

		creditCard.setAvailableCreditLimit(balance);
		creditCardService.saveCreditCard(creditCard);
		modelMap.put("result", "success");
		status.setComplete();
		return "addtransaction";

	}

	/**
	 * Fetch transaction.
	 *
	 * @param request
	 *            the request
	 * @return the data table dto
	 */
	@RequestMapping(value = "filter", method = RequestMethod.GET)
	public @ResponseBody DataTableDTO fetchTransaction(HttpServletRequest request) {

		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String[] columnNames = { "transactionId", "transactionDate", "customerName", "cardNumber", "amount", "balance", "description" };

		int listDisplayAmount = PER_PAGE_OBJECT;
		int start = 0;
		int column = 0;
		String dir = "ASC";
		String pageNo = request.getParameter(CCMConstant.DATATABLE_CONSTANT_IDISPLAYSTART);
		String pageSize = request.getParameter(CCMConstant.DATATABLE_CONSTANT_IDISPLAYLENGTH);
		String colIndex = request.getParameter(CCMConstant.DATATABLE_CONSTANT_ISORTCOL_0);
		String sortDirection = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSORTDIR_0);
		int sEcho = 0;
		if (request.getParameter(CCMConstant.DATATABLE_CONSTANT_SECHO) != null) {
			sEcho = Integer.parseInt(request.getParameter(CCMConstant.DATATABLE_CONSTANT_SECHO));
		}
		if (CommonUtil.isNotNull(pageNo)) {
			start = Integer.parseInt(pageNo);
			if (start < 0) {
				start = 0;
			}
		}
		if (CommonUtil.isNotNull(pageSize)) {
			listDisplayAmount = Integer.parseInt(pageSize);
			if (listDisplayAmount < 10 || listDisplayAmount > 50) {
				listDisplayAmount = 10;
			}
		}
		if (CommonUtil.isNotNull(colIndex)) {
			column = Integer.parseInt(colIndex);
			if (column < 0 || column > 5)
				column = 0;
		}
		if (CommonUtil.isNotNull(sortDirection) && !("asc").equals(sortDirection)) {
			dir = "DESC";
		}

		String colName = columnNames[column];
		int totalRecords = -1;
		totalRecords = (int) transactionService.getTransactionsCount(new HashMap<String, Object>(), Long.parseLong(user.getUsername()));

		CUSTOMER_NAME_SEARCH_TERM = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSEARCH_2);
		CARD_NUMBER_SEARCH_TERM = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSEARCH_3);
		DESCRIPTION_SEARCH_TERM = request.getParameter(CCMConstant.DATATABLE_CONSTANT_SSEARCH_6);
		COLUMN_NAME = colName;
		DIRECTION = dir;
		INITIAL = start;
		RECORD_SIZE = INITIAL + listDisplayAmount;
		Map<String, Object> map = new HashMap<String, Object>();

		if (CommonUtil.isNotNull(CUSTOMER_NAME_SEARCH_TERM)) {
			map.put(CCMConstant.FIELDCONSTANT_CUSTOMERNAME, CUSTOMER_NAME_SEARCH_TERM);
		}
		if (CommonUtil.isNotNull(CARD_NUMBER_SEARCH_TERM)) {
			map.put(CCMConstant.FIELDCONSTANT_CARDNUMBER, CARD_NUMBER_SEARCH_TERM);
		}
		if (CommonUtil.isNotNull(DESCRIPTION_SEARCH_TERM)) {
			map.put("description", DESCRIPTION_SEARCH_TERM);
		}
		transactions = transactionService.getTransactions(INITIAL, RECORD_SIZE, COLUMN_NAME, DIRECTION, map, Long.parseLong(user.getUsername()));
		DataTableDTO dataDto = setDataTableDTO(start, sEcho, totalRecords, map, Long.parseLong(user.getUsername()));
		return dataDto;
	}

	/**
	 * Sets the data table dto.
	 *
	 * @param start
	 *            the start
	 * @param sEcho
	 *            the s echo
	 * @param totalRecords
	 *            the total records
	 * @param map
	 *            the map
	 * @return the data table dto
	 */
	private DataTableDTO setDataTableDTO(int start, int sEcho, int totalRecords, Map<String, Object> map, long userId) {
		DataTableDTO dataDto = new DataTableDTO();
		dataDto.setDraw(start);
		dataDto.setRecordsFiltered((int) transactionService.getTransactionsCount(map, userId));
		dataDto.setRecordsTotal(totalRecords);
		dataDto.setData(transactions.toArray());
		dataDto.setsEcho(sEcho);
		return dataDto;
	}
}
