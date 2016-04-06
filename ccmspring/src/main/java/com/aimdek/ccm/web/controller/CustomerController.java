package com.aimdek.ccm.web.controller;

import static com.aimdek.ccm.util.CCMConstant.BLANK;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_IDISPLAYLENGTH;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_IDISPLAYSTART;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_ISORTCOL_0;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_SECHO;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_SSEARCH_1;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_SSEARCH_2;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_SSEARCH_3;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_SSEARCH_4;
import static com.aimdek.ccm.util.CCMConstant.DATATABLE_CONSTANT_SSORTDIR_0;
import static com.aimdek.ccm.util.CCMConstant.DATE_FORMAT;
import static com.aimdek.ccm.util.CCMConstant.FALSE;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_EMAIL;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_FIRSTNAME;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_LASTNAME;
import static com.aimdek.ccm.util.CCMConstant.FIELDCONSTANT_PHONE;
import static com.aimdek.ccm.util.CCMConstant.INTIAL_CREDIT_LIMIT;
import static com.aimdek.ccm.util.CCMConstant.MASTER_CITY;
import static com.aimdek.ccm.util.CCMConstant.MASTER_COUNTRY;
import static com.aimdek.ccm.util.CCMConstant.MASTER_STATE;
import static com.aimdek.ccm.util.CCMConstant.PROFILE_KEY;
import static com.aimdek.ccm.util.CCMConstant.ROLE_CUSTOMER;
import static com.aimdek.ccm.util.CCMConstant.TRUE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.aimdek.ccm.custom.dto.CustomerDto;
import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.Geographic;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.dto.CustomerDataDTO;
import com.aimdek.ccm.dto.DataTableDTO;
import com.aimdek.ccm.service.AddressService;
import com.aimdek.ccm.service.CreditCardService;
import com.aimdek.ccm.service.GeographicService;
import com.aimdek.ccm.service.MailSenderService;
import com.aimdek.ccm.service.UserService;
import com.aimdek.ccm.util.CCMConstant.State;
import com.aimdek.ccm.util.CommonUtil;
import com.aimdek.ccm.util.CreditCardValidator;
import com.aimdek.ccm.util.CustomerValidator;

/**
 * The Class CustomerController.
 *
 * @author aimdek.team
 */
@Controller
@SessionAttributes(value = { "customerForm", "creditCardForm" })
@RequestMapping(value = "customer")
public class CustomerController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CustomerController.class);

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

	/** The phone number search term. */
	private String FIRST_NAME_SEARCH_TERM, LAST_NAME_SEARCH_TERM, EMAIL_SEARCH_TERM, PHONE_NUMBER_SEARCH_TERM;

	/** The customer dto list. */
	private List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The address service. */
	@Autowired
	private AddressService addressService;

	/** The credit card service. */
	@Autowired
	private CreditCardService creditCardService;

	/** The geographic service. */
	@Autowired
	private GeographicService geographicService;

	/** The customer validator. */
	@Autowired
	private CustomerValidator customerValidator;

	/** The credit card validator. */
	@Autowired
	private CreditCardValidator creditCardValidator;
	
	@Autowired
	private MailSenderService emailService;

	/**
	 * Gets the customer dto list.
	 *
	 * @return the customerDtoList
	 */
	public List<CustomerDto> getCustomerDtoList() {
		return customerDtoList;
	}

	/**
	 * Sets the customer dto list.
	 *
	 * @param customerDtoList
	 *            the customerDtoList to set
	 */
	public void setCustomerDtoList(List<CustomerDto> customerDtoList) {
		this.customerDtoList = customerDtoList;
	}

	/**
	 * Inits the binder.
	 *
	 * @param webDataBinder
	 *            the web data binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, TRUE));
	}

	/**
	 * Default RequesMapping Method.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String displayAllCustomers(ModelMap model) {
		model.put("page", "customer");
		model.put("customerForm", new CustomerDataDTO());
		return "customerList";
	}

	/**
	 * Fetch customers.
	 *
	 * @param request
	 *            the request
	 * @return the data table dto
	 */
	@RequestMapping(value = "filter", method = RequestMethod.GET)
	public @ResponseBody DataTableDTO fetchCustomers(HttpServletRequest request) {

		String[] columnNames = { "customerId", "firstName", "lastName", "email", "phoneNumber" };

		int listDisplayAmount = PER_PAGE_OBJECT;
		int start = 0;
		int column = 0;
		int sEcho = 0;

		String dir = "ASC";
		String pageNo = request.getParameter(DATATABLE_CONSTANT_IDISPLAYSTART);
		String pageSize = request.getParameter(DATATABLE_CONSTANT_IDISPLAYLENGTH);
		String colIndex = request.getParameter(DATATABLE_CONSTANT_ISORTCOL_0);
		String sortDirection = request.getParameter(DATATABLE_CONSTANT_SSORTDIR_0);

		if (CommonUtil.isNotNull(request.getParameter(DATATABLE_CONSTANT_SECHO))) {
			sEcho = Integer.parseInt(request.getParameter(DATATABLE_CONSTANT_SECHO));
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
		totalRecords = (int) userService.getCustomersCount(new HashMap<String, Object>());

		FIRST_NAME_SEARCH_TERM = request.getParameter(DATATABLE_CONSTANT_SSEARCH_1);
		LAST_NAME_SEARCH_TERM = request.getParameter(DATATABLE_CONSTANT_SSEARCH_2);
		EMAIL_SEARCH_TERM = request.getParameter(DATATABLE_CONSTANT_SSEARCH_3);
		PHONE_NUMBER_SEARCH_TERM = request.getParameter(DATATABLE_CONSTANT_SSEARCH_4);
		COLUMN_NAME = colName;
		DIRECTION = dir;
		INITIAL = start;
		RECORD_SIZE = INITIAL + listDisplayAmount;
		Map<String, Object> map = new HashMap<String, Object>();

		if (CommonUtil.isNotNull(FIRST_NAME_SEARCH_TERM)) {
			map.put(FIELDCONSTANT_FIRSTNAME, FIRST_NAME_SEARCH_TERM);
		}
		if (CommonUtil.isNotNull(LAST_NAME_SEARCH_TERM)) {
			map.put(FIELDCONSTANT_LASTNAME, LAST_NAME_SEARCH_TERM);
		}
		if (CommonUtil.isNotNull(EMAIL_SEARCH_TERM)) {
			map.put(FIELDCONSTANT_EMAIL, EMAIL_SEARCH_TERM);
		}
		if (CommonUtil.isNotNull(PHONE_NUMBER_SEARCH_TERM)) {
			map.put(FIELDCONSTANT_PHONE, PHONE_NUMBER_SEARCH_TERM);
		}

		customerDtoList = userService.getCustomers(INITIAL, RECORD_SIZE, COLUMN_NAME, DIRECTION, map, FALSE);

		DataTableDTO dataDto = setDataTableDTO(start, sEcho, totalRecords, map);
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
	private DataTableDTO setDataTableDTO(int start, int sEcho, int totalRecords, Map<String, Object> map) {
		DataTableDTO dataDto = new DataTableDTO();
		dataDto.setDraw(start);
		dataDto.setRecordsFiltered((int) userService.getCustomersCount(map));
		dataDto.setRecordsTotal(totalRecords);
		dataDto.setData(customerDtoList.toArray());
		dataDto.setsEcho(sEcho);
		return dataDto;
	}

	/**
	 * Adds the customer.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "addCustomer", method = RequestMethod.GET)
	public String addCustomer(ModelMap model) {

		CustomerDataDTO customerDataDTO = new CustomerDataDTO();

		fetchMasterGeoList(customerDataDTO);

		model.put("imagePath", BLANK);
		model.put("customerForm", customerDataDTO);

		return "addCustomer";
	}

	/**
	 * Fetch master geo list.
	 *
	 * @param customerDataDTO
	 *            the customer data dto
	 */
	private void fetchMasterGeoList(CustomerDataDTO customerDataDTO) {
		Map<String, List<String>> masterGeoList = geographicService.getGeographics();
		if (!masterGeoList.isEmpty()) {
			customerDataDTO.setCityList(masterGeoList.get(MASTER_CITY));
			customerDataDTO.setCountryList(masterGeoList.get(MASTER_COUNTRY));
			customerDataDTO.setStateList(masterGeoList.get(MASTER_STATE));
		}
	}

	/**
	 * Save customer.
	 *
	 * @param dataDTO
	 *            the data dto
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param map
	 *            the map
	 * @param httpSession
	 *            the http session
	 * @return the string
	 */
	@RequestMapping(value = { "saveCustomer", "/edit/saveCustomer" }, method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute(value = "customerForm") CustomerDataDTO dataDTO, BindingResult result, SessionStatus status, ModelMap map, HttpSession httpSession) {

		customerValidator.validate(dataDTO, result);
		boolean sendEmail = false;
		// Check validation errors
		if (result.hasErrors()) {
			fetchMasterGeoList(dataDTO);
			if (CommonUtil.isNotNull(dataDTO.getUser()) && dataDTO.getUser().getId() > 0) {
				List<CreditCard> creditCards = creditCardService.findCreditCardsByCardHolderId(dataDTO.getUser().getId());
				dataDTO.setCreditCards(creditCards);
			}
			map.put("customerForm", dataDTO);
			map.put("error", TRUE);
			return "addCustomer";
		}

		long userId = ((User) httpSession.getAttribute("user")).getId();
		User user = dataDTO.getUser();
		Address address = dataDTO.getAddress();
		CreditCard creditCard = dataDTO.getCreditCard();
		Geographic geographic = dataDTO.getGeographic();
		String zipcode = dataDTO.getZipCode();
		MultipartFile multipartFile = dataDTO.getProfilePicture();
		boolean isEdit = false;

		if (CommonUtil.isNotNull(userId)) {
			String readablePassword = BLANK;
			if (CommonUtil.isNull(user.getId())) {
				user.setRole(ROLE_CUSTOMER);
				user.setCreatedBy(userId);
				user.setCustomerId(userService.nextCustomerId());
				readablePassword = CommonUtil.generateRandomAlphanumeric();
				String hashedPassword = userService.generateSecuredPassword(readablePassword);
				user.setPassword(hashedPassword);
				user.setState(State.DONE.toString());
				user.setHasCreditCard(TRUE);
				sendEmail = TRUE;
			} else {
				isEdit = TRUE;
			}
//			userService.saveUser(user);

			if (CommonUtil.isNotNull(multipartFile) && multipartFile.getSize() > 0) {

				if (isEdit && CommonUtil.isNotNull(user.getProfilePicture())) {
					userService.deleteFileFromStore(user.getProfilePicture());
				}

				String filePath = uploadFile(user, multipartFile);
				user.setProfilePicture(filePath);
			}
			userService.saveUser(user);
			saveAddress(dataDTO, user, address, geographic, zipcode);

			if (!isEdit) {
				saveCreditCard(userId, user, creditCard);
			}
			if (sendEmail) {
				emailService.sendCustomerRegistrationEmail(user, readablePassword);
			}

		}
		map.put("result", "success");
		map.put("isEdit", isEdit);
		return "addCustomer";
	}

	/**
	 * Upload file.
	 *
	 * @param user
	 *            the user
	 * @param multipartFile
	 *            the multipart file
	 * @return the string
	 */
	private String uploadFile(User user, MultipartFile multipartFile) {
		File file = null;
		OutputStream output = null;

		String fileName = multipartFile.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf('.'), fileName.length());

		try {
			file = File.createTempFile(PROFILE_KEY, ext);
			output = new FileOutputStream(file);
			IOUtils.copy(multipartFile.getInputStream(), output);
			output.close();
		} catch (IOException e) {
			LOGGER.error(e);
		}

		String filePath = userService.uploadProfilePicture(user.getId(), file);
		return filePath;
	}

	/**
	 * Save credit card.
	 *
	 * @param userId
	 *            the user id
	 * @param user
	 *            the user
	 * @param creditCard
	 *            the credit card
	 */
	private void saveCreditCard(long userId, User user, CreditCard creditCard) {
		creditCard.setCardHolderId(user.getId());
		creditCard.setCreditLimit(INTIAL_CREDIT_LIMIT);
		creditCard.setAvailableCreditLimit(INTIAL_CREDIT_LIMIT);
		// Calculate Statement date
		calculateStatementDate(creditCard);
		if (CommonUtil.isNotNull(user.getId())) {
			creditCard.setCreatedBy(userId);
		}
		creditCardService.saveCreditCard(creditCard);
	}

	/**
	 * Save address.
	 *
	 * @param dataDTO
	 *            the data dto
	 * @param user
	 *            the user
	 * @param address
	 *            the address
	 * @param geographic
	 *            the geographic
	 * @param zipcode
	 *            the zipcode
	 */
	private void saveAddress(CustomerDataDTO dataDTO, User user, Address address, Geographic geographic, String zipcode) {
		Geographic geographic2 = geographicService.findGeographicsByZip(Long.valueOf(dataDTO.getZipCode()));

		if (geographic2 == null) {
			geographic.setZipcode(Long.valueOf(zipcode));
			geographicService.saveGeographic(geographic);
			address.setGeoId(geographic.getId());
		} else {
			address.setGeoId(geographic2.getId());
		}
		address.setUserId(user.getId());
		addressService.saveAddress(address);
	}

	/**
	 * Adds the credit card to customer.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "addCreditCardToCustomer", method = RequestMethod.GET)
	public String addCreditCardToCustomer(ModelMap model, @ModelAttribute(value = "customerForm") CustomerDataDTO customerDataDTO) {
		List<User> userList = userService.getCustomers();
		model.put("userList", userList);
		model.put("creditCardForm", new CreditCard());
		model.put("customerForm", customerDataDTO);
		return "addCreditCardToCustomer";
	}

	/**
	 * Edits the credit card to customer.
	 *
	 * @param model
	 *            the model
	 * @param customerDataDTO
	 *            the customer data dto
	 * @param generatedId
	 *            the generated id
	 * @return the string
	 */
	@RequestMapping(value = "editCreditCard/{generatedId}", method = RequestMethod.GET)
	public String editCreditCardToCustomer(ModelMap model, @ModelAttribute(value = "customerForm") CustomerDataDTO customerDataDTO, @PathVariable Long generatedId) {
		CreditCard creditCard = creditCardService.findCreditCardById(generatedId);
		model.put("creditCardForm", creditCard);
		model.put("customerForm", customerDataDTO);
		return "editCreditCardToCustomer";
	}

	/**
	 * Save credit card.
	 *
	 * @param customerDataDTO
	 *            the customer data dto
	 * @param creditCard
	 *            the credit card
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "saveCreditCard", "/editCreditCard/saveCreditCard" }, method = RequestMethod.POST)
	public String saveCreditCard(@ModelAttribute(value = "customerForm") CustomerDataDTO customerDataDTO, @ModelAttribute(value = "creditCardForm") CreditCard creditCard, BindingResult result,
			SessionStatus status, ModelMap model) {

		creditCardValidator.validate(creditCard, result);
		long id = creditCard.getId();
		String view = "addCreditCardToCustomer";
		boolean isEdit = FALSE;
		if (CommonUtil.isNotNull(id)) {
			view = "editCreditCardToCustomer";
			isEdit = TRUE;
		}

		// Check validation errors
		if (result.hasErrors()) {
			if (!isEdit) {
				List<User> userList = userService.getCustomers();
				model.put("userList", userList);
			}
			model.put("error", TRUE);
			model.put("creditCardForm", creditCard);
			return view;

		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();

		if (!isEdit) {
			creditCard.setCreditLimit(INTIAL_CREDIT_LIMIT);
			creditCard.setAvailableCreditLimit(INTIAL_CREDIT_LIMIT);
			creditCard.setCreatedBy(Long.parseLong(userId));
			calculateStatementDate(creditCard);
			// Set user's has credit card flag to true.
			userService.updateCustomerCreditCardFlag(creditCard.getCardHolderId(), TRUE);
		}

		creditCardService.saveCreditCard(creditCard);
		model.put("result", "success");
		model.put("customerForm", customerDataDTO);
		return view;
	}

	/**
	 * Gets the country list.
	 *
	 * @param zipCode
	 *            the zip code
	 * @return the country list
	 */
	@RequestMapping(value = { "getCountryList", "/edit/getCountryList" }, produces = "application/json")
	public @ResponseBody Geographic getCountryList(@RequestParam(value = "zipCode", required = TRUE) String zipCode) {
		Geographic geographic = new Geographic();
		if (CommonUtil.isNotNull(zipCode)) {
			geographic = geographicService.findGeographicsByZip(Long.valueOf(zipCode));

			if (CommonUtil.isNull(geographic)) {
				geographic = new Geographic();
			}
		}

		return geographic;
	}

	/**
	 * Calculate statement date.
	 *
	 * @param creditCard
	 *            the credit card
	 */
	private void calculateStatementDate(CreditCard creditCard) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(creditCard.getCreatedAt());
		calendar.add(Calendar.MONTH, 1);
		creditCard.setStatementDate(calendar.getTime());
	}

	/**
	 * Edit Customer.
	 *
	 * @param model
	 *            the model
	 * @param generatedId
	 *            the generated id
	 * @return the string
	 */
	@RequestMapping(value = "edit/{generatedId}", method = RequestMethod.GET)
	public String editCustomer(ModelMap model, @PathVariable long generatedId) {
		CustomerDataDTO customerDataDTO = new CustomerDataDTO();
		User user = userService.findUserById(generatedId);
		Address address = addressService.findAddressByUserId(generatedId);
		Geographic geographic = geographicService.findGeographicsById(address.getGeoId());
		List<CreditCard> creditCards = creditCardService.findCreditCardsByCardHolderId(generatedId);

		customerDataDTO.setAddress(address);
		customerDataDTO.setCreditCards(creditCards);
		customerDataDTO.setGeographic(geographic);
		customerDataDTO.setUser(user);
		customerDataDTO.setZipCode(String.valueOf(geographic.getZipcode()));

		fetchMasterGeoList(customerDataDTO);

		if (CommonUtil.isNotNull(user.getProfilePicture())) {
			model.put("imagePath", userService.getAssignedUrl(user.getProfilePicture()));
		}
		model.put("customerForm", customerDataDTO);
		model.put("isEdit", TRUE);
		return "addCustomer";
	}

	/**
	 * Delete customer and also remove all data related to customer(like credit
	 * card details,transactions etc.)
	 *
	 * @param generatedId
	 *            the generated id
	 * @return true, if successful
	 */
	@RequestMapping(value = "deleteCustomer/{generatedId}", method = RequestMethod.GET)
	public @ResponseBody boolean deleteCustomer(@PathVariable long generatedId) {
		userService.removeCustomer(generatedId);
		return TRUE;
	}

	/**
	 * Delete Credit Card.
	 *
	 * @param generatedId
	 *            the generated id
	 * @return true, if successful
	 */
	@RequestMapping(value = "deleteCreditCard/{generatedId}", method = RequestMethod.GET)
	public @ResponseBody boolean deleteCreditCard(@PathVariable long generatedId) {

		CreditCard creditCard = creditCardService.findCreditCardById(generatedId);
		if (CommonUtil.isNotNull(creditCard)) {
			creditCardService.removeCreditCard(creditCard);
			long creditCardCount = creditCardService.getCustomerCreditCardsCount(creditCard.getCardHolderId());
			if (creditCardCount <= 0) {
				userService.updateCustomerCreditCardFlag(creditCard.getCardHolderId(), FALSE);
			}
		}
		return TRUE;
	}
}
