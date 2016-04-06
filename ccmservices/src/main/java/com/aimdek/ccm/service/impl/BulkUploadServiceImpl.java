package com.aimdek.ccm.service.impl;

import static com.aimdek.ccm.util.CCMConstant.BULKUPLOAD_TEMP_FILE_NAME;
import static com.aimdek.ccm.util.CCMConstant.BULK_UPLOAD_SUCCESS_MSG;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_AREA;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_BIRTH_DATE;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_CARD_NUMBER;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_CITY;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_COUNTRY;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_CREDIT_CARD_NUMBER;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_CREDIT_LIMIT;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_CVV;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_DESCRIPTION;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_EMAIL;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_FIRST_NAME;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_HOUSE_NAME;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_LAST_NAME;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_NAME_ON_CARD;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_PASSWORD;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_PHONE_NUMBER;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_ROYALTY_POINTS;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_STATE;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_STREET;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_TRANSACTION_AMOUNT;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_TRANSACTION_DATE;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_VALID_FROM_DATE;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_VALID_TO_DATE;
import static com.aimdek.ccm.util.CCMConstant.COLUMN_ZIPCODE;
import static com.aimdek.ccm.util.CCMConstant.COMMA;
import static com.aimdek.ccm.util.CCMConstant.CSV_FILE_CONTENT_TYPE;
import static com.aimdek.ccm.util.CCMConstant.CSV_FILE_EXTENTION;
import static com.aimdek.ccm.util.CCMConstant.DATE_FORMAT;
import static com.aimdek.ccm.util.CCMConstant.DOT;
import static com.aimdek.ccm.util.CCMConstant.EXCEL_FILE_CONTENT_TYPE;
import static com.aimdek.ccm.util.CCMConstant.EXCEL_FILE_EXTENTION;
import static com.aimdek.ccm.util.CCMConstant.FALSE;
import static com.aimdek.ccm.util.CCMConstant.FOUR;
import static com.aimdek.ccm.util.CCMConstant.MS_EXCEL_FILE_CONTENT_TYPE;
import static com.aimdek.ccm.util.CCMConstant.MS_EXCEL_FILE_EXTENTION;
import static com.aimdek.ccm.util.CCMConstant.ONE;
import static com.aimdek.ccm.util.CCMConstant.ROLE_CUSTOMER;
import static com.aimdek.ccm.util.CCMConstant.SPACE;
import static com.aimdek.ccm.util.CCMConstant.SUCCESS_RECORDS;
import static com.aimdek.ccm.util.CCMConstant.THOUSAND;
import static com.aimdek.ccm.util.CCMConstant.THREE;
import static com.aimdek.ccm.util.CCMConstant.TOTAL_RECORDS;
import static com.aimdek.ccm.util.CCMConstant.TRUE;
import static com.aimdek.ccm.util.CCMConstant.TWENTY;
import static com.aimdek.ccm.util.CCMConstant.TWO;
import static com.aimdek.ccm.util.CCMConstant.UPLOAD_DEFAULT_ID;
import static com.aimdek.ccm.util.CCMConstant.ZERO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.aimdek.ccm.util.CCMConstant;
import com.aimdek.ccm.util.CCMConstant.State;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class BulkUploadServiceImpl.
 * 
 * @author aimdek.team
 */
@Service("bulkUploadService")
public class BulkUploadServiceImpl implements BulkUploadService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BulkUploadServiceImpl.class);

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The user service. */
	@Autowired
	private UserService usersService;

	/** The geographic repository. */
	@Autowired
	private GeographicRepository geographicRepository;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/** The card repository. */
	@Autowired
	private CreditCardRepository cardRepository;

	/** The bulk upload repository. */
	@Autowired
	private BulkUploadRepository bulkUploadRepository;

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/** The calendar. */
	private Calendar calendar;

	/** The date format. */
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * {@inheritDoc}
	 */
	public String handleBulkUpload(String fileName, String contentType, InputStream inputStream, boolean isUser, long userId) {
		String finalMsg = null;
		try {
			File file = null;
			int caseNo = ZERO;
			if (contentType.equals(CSV_FILE_CONTENT_TYPE) || fileName.endsWith(".csv")) {
				file = File.createTempFile(BULKUPLOAD_TEMP_FILE_NAME, CSV_FILE_EXTENTION);
				caseNo = ONE;
			} else if (contentType.equals(EXCEL_FILE_CONTENT_TYPE)) {
				file = File.createTempFile(BULKUPLOAD_TEMP_FILE_NAME, EXCEL_FILE_EXTENTION);
				caseNo = TWO;
			} else if (contentType.equals(MS_EXCEL_FILE_CONTENT_TYPE)) {
				file = File.createTempFile(BULKUPLOAD_TEMP_FILE_NAME, MS_EXCEL_FILE_EXTENTION);
				caseNo = THREE;
			}

			if (CommonUtil.isNotNull(file) && file.exists()) {
				this.calendar = Calendar.getInstance();
				this.calendar.setTime(CommonUtil.toDay());
				long timeTaken = this.calendar.getTimeInMillis();

				if (caseNo == ONE) {
					finalMsg = readCSV(fileName, inputStream, finalMsg, file, timeTaken, isUser, userId);
				} else if (caseNo == TWO) {
					finalMsg = readXLSX(fileName, inputStream, finalMsg, timeTaken, isUser, userId);
				} else if (caseNo == THREE) {
					finalMsg = readXLS(fileName, inputStream, finalMsg, timeTaken, isUser, userId);
				}

			}

		} catch (IOException e) {
			LOGGER.error("Error while handling bulk upload", e);
		}

		return finalMsg;
	}

	/**
	 * Read CSV.
	 * 
	 * @param fileName
	 *            the file name
	 * @param inputStream
	 *            the input stream
	 * @param finalMsg
	 *            the final msg
	 * @param file
	 *            the file
	 * @param timeTaken
	 *            the time taken
	 * @param isUser
	 *            the is user
	 * @return the string
	 * @throws IOException
	 *             String
	 */
	private String readCSV(String fileName, InputStream inputStream, String finalMsg, File file, long timeTaken, boolean isUser, long userId) throws IOException {
		int failureRecord = 0;
		int totalRecords = 0;
		List<Long> pendingTransactions = new ArrayList<Long>();
		List<Long> pendingCustomers = new ArrayList<Long>();
		FileUtils.copyInputStreamToFile(inputStream, file);
		List<String> readLine = FileUtils.readLines(file);
		if (!readLine.isEmpty()) {
			List<String> tempReadLines = readLine.subList(ONE, readLine.size());
			if (CommonUtil.isNotNull(tempReadLines) && !tempReadLines.isEmpty()) {
				totalRecords = tempReadLines.size();
				for (String eachline : tempReadLines) {
					if (CommonUtil.isNotNull(eachline) && eachline.length() > ZERO) {
						String[] details = eachline.split(COMMA);

						if ((details.length > ZERO)) {
							boolean result = FALSE;
							if (isUser && details.length == TWENTY) {
								result = saveUsers(details, pendingCustomers, userId);
							} else {
								if (details.length == FOUR) {
									result = saveTransactions(details, pendingTransactions);
								}
							}
							if (!result) {
								failureRecord += ONE;
							}
						} else {
							failureRecord += ONE;
							continue;
						}
					}

				}

				finalMsg = saveBulkUploadData(fileName, failureRecord, totalRecords, timeTaken, pendingCustomers, pendingTransactions, isUser, userId);
			}
		}
		return finalMsg;
	}

	/**
	 * Read xls.
	 * 
	 * @param fileName
	 *            the file name
	 * @param inputStream
	 *            the uploaded file
	 * @param finalMsg
	 *            the final msg
	 * @param timeTaken
	 *            the time taken
	 * @param isUser
	 *            the is user
	 * @param userId
	 *            the user id
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private String readXLS(String fileName, InputStream inputStream, String finalMsg, long timeTaken, boolean isUser, long userId) throws IOException {

		int failureRecord = 0;
		int totalRecords = 0;
		List<Long> pendingTransactions = new ArrayList<Long>();
		List<Long> pendingCustomers = new ArrayList<Long>();
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(inputStream);
		} catch (InvalidFormatException e) {
			LOGGER.error(e);
		}
		if (CommonUtil.isNotNull(workbook)) {
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			totalRecords = sheet.getPhysicalNumberOfRows() - ONE;
			while (rowIterator.hasNext()) {

				List<String> detailsList = new ArrayList<String>();

				Row row = rowIterator.next();

				if (row.getRowNum() > ZERO) {
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if (DateUtil.isCellDateFormatted(cell)) {
								detailsList.add(String.valueOf(dateFormat.format(cell.getDateCellValue())));
							} else {
								String zipVal = String.valueOf(cell.getNumericCellValue());
								if (zipVal.contains(DOT)) {
									zipVal = zipVal.substring(ZERO, zipVal.indexOf(DOT));
									detailsList.add(zipVal);
								} else {
									detailsList.add(zipVal);
								}
							}
						} else {
							detailsList.add(cell.getStringCellValue());
						}

					}
					String[] details = detailsList.toArray(new String[detailsList.size()]);

					if (CommonUtil.isNotNull(details)) {
						boolean result = FALSE;
						if (isUser && (details.length == TWENTY)) {
							result = saveUsers(details, pendingCustomers, userId);
						} else {
							if (details.length == FOUR) {
								result = saveTransactions(details, pendingTransactions);
							}
						}
						if (!result) {
							failureRecord += ONE;
						}
					} else {
						failureRecord += ONE;
					}
				}

			}

			finalMsg = saveBulkUploadData(fileName, failureRecord, totalRecords, timeTaken, pendingCustomers, pendingTransactions, isUser, userId);
		}
		return finalMsg;
	}

	/**
	 * Read xlsx.
	 * 
	 * @param fileName
	 *            the file name
	 * @param inputStream
	 *            the input stream
	 * @param finalMsg
	 *            the final msg
	 * @param timeTaken
	 *            the time taken
	 * @param isUser
	 *            the is user
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private String readXLSX(String fileName, InputStream inputStream, String finalMsg, long timeTaken, boolean isUser, long userId) throws IOException {

		int failureRecord = 0;
		int totalRecords = 0;
		List<Long> pendingTransactions = new ArrayList<Long>();
		List<Long> pendingCustomers = new ArrayList<Long>();

		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(inputStream);
		} catch (InvalidFormatException e) {
			LOGGER.error(e);
		}
		if (CommonUtil.isNotNull(workbook)) {
			Sheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			totalRecords = sheet.getPhysicalNumberOfRows() - ONE;
			while (rowIterator.hasNext()) {

				List<String> detailsList = new ArrayList<String>();

				Row row = rowIterator.next();

				if (row.getRowNum() > ZERO) {
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if (DateUtil.isCellDateFormatted(cell)) {
								detailsList.add(String.valueOf(dateFormat.format(cell.getDateCellValue())));
							} else {
								String zipVal = String.valueOf(cell.getNumericCellValue());
								if (zipVal.contains(DOT)) {
									zipVal = zipVal.substring(ZERO, zipVal.indexOf(DOT));
									detailsList.add(zipVal);
								} else {
									detailsList.add(zipVal);
								}
							}
						} else {
							detailsList.add(cell.getStringCellValue());
						}

					}
					String[] details = detailsList.toArray(new String[detailsList.size()]);

					if (CommonUtil.isNotNull(details)) {
						boolean result = FALSE;
						if (isUser && (details.length == TWENTY)) {
							result = saveUsers(details, pendingCustomers, userId);
						} else {
							if (details.length == FOUR) {
								result = saveTransactions(details, pendingTransactions);
							}
						}
						if (!result) {
							failureRecord += ONE;
						}
					} else {
						failureRecord += ONE;
					}
				}

			}
			finalMsg = saveBulkUploadData(fileName, failureRecord, totalRecords, timeTaken, pendingCustomers, pendingTransactions, isUser, userId);
		}

		return finalMsg;
	}

	/**
	 * Adds the bulk upload data.
	 * 
	 * @param uploadedFileName
	 *            the uploaded file name
	 * @param failureRecord
	 *            the failure record
	 * @param totalRecords
	 *            the total records
	 * @param timeTaken
	 *            the time taken
	 * @param pendingTransactions
	 *            the pending transactions
	 * @param isUser
	 *            the is user
	 * @return the string
	 */
	private String saveBulkUploadData(String uploadedFileName, int failureRecord, int totalRecords, long timeTaken, List<Long> pendingCustomers, List<Long> pendingTransactions, boolean isUser,
			long userId) {
		String finalMsg;
		BulkUpload bulkUpload = new BulkUpload();
		bulkUpload.setFailureRecords(failureRecord);
		bulkUpload.setTotalRecords(totalRecords);
		bulkUpload.setSuccessRecords(totalRecords - failureRecord);
		bulkUpload.setFileName(uploadedFileName);
		removeCustomers(pendingCustomers);
		removeTransactions(pendingTransactions);
		if (isUser) {
			bulkUpload.setType(CCMConstant.BULK_UPLOAD_TYPE_CUSTOMER);
		} else {
			bulkUpload.setType(CCMConstant.BULK_UPLOAD_TYPE_TRANSACTION);
		}
		if (CommonUtil.isNotNull(userId)) {
			bulkUpload.setCreatedBy(userId);
			User user = userRepository.findById(userId);
			bulkUpload.setUploadedBy(user.getFirstName() + SPACE + user.getLastName());
		}
		bulkUpload.setBulkUploadId(nextBulkUploadId());

		calendar = Calendar.getInstance();
		calendar.setTime(CommonUtil.toDay());
		long finalTime = calendar.getTimeInMillis();
		int seconds = (int) ((finalTime - timeTaken) / THOUSAND);
		bulkUpload.setTimeTaken(seconds);
		bulkUpload.setUploadDate(CommonUtil.toDay());
		this.bulkUploadRepository.save(bulkUpload);

		finalMsg = BULK_UPLOAD_SUCCESS_MSG;

		finalMsg = finalMsg.replace(SUCCESS_RECORDS, String.valueOf(bulkUpload.getSuccessRecords()));

		finalMsg = finalMsg.replace(TOTAL_RECORDS, String.valueOf(bulkUpload.getTotalRecords()));
		return finalMsg;
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
		calendar.add(Calendar.MONTH, ONE);
		creditCard.setStatementDate(calendar.getTime());
	}

	/**
	 * Save users.
	 * 
	 * @param users
	 *            the users
	 * @param pendingCustomers
	 *            the pending customers
	 * @param userId
	 *            the user id
	 * @return true, if successful
	 */
	private boolean saveUsers(String[] users, List<Long> pendingCustomers, long userId) {
		LOGGER.info("Add user details.");
		User user = new User();

		if (CommonUtil.isNotNull(users[COLUMN_FIRST_NAME]))
			user.setFirstName(users[COLUMN_FIRST_NAME]);
		else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_LAST_NAME])) {
			user.setLastName(users[COLUMN_LAST_NAME]);
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_EMAIL]) && CommonUtil.checkEmailPattern(users[COLUMN_EMAIL])) {
			user.setEmail(users[COLUMN_EMAIL]);
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_BIRTH_DATE])) {
			try {
				user.setBirthDate(dateFormat.parse(users[COLUMN_BIRTH_DATE]));
			} catch (ParseException e) {
				LOGGER.error("Error while parsing user birthdate.", e);
				return FALSE;
			}
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_PHONE_NUMBER])) {
			user.setPhoneNumber(users[COLUMN_PHONE_NUMBER]);
		}
		if (CommonUtil.isNotNull(users[COLUMN_PASSWORD])) {
			user.setPassword(usersService.generateSecuredPassword(users[COLUMN_PASSWORD]));
		} else {
			return FALSE;
		}
		user.setCreatedBy(userId);
		user.setRole(ROLE_CUSTOMER);
		user.setState(State.PENDING.toString());

		this.usersService.saveUser(user);
		pendingCustomers.add(user.getId());

		Address address = new Address();

		if (CommonUtil.isNotNull(users[COLUMN_HOUSE_NAME])) {
			address.setHouseName(users[COLUMN_HOUSE_NAME]);
		}

		if (CommonUtil.isNotNull(users[COLUMN_STREET])) {
			address.setStreet(users[COLUMN_STREET]);
		}

		if (CommonUtil.isNotNull(users[COLUMN_AREA])) {
			address.setArea(users[COLUMN_AREA]);
		}

		Geographic geographic = new Geographic();

		if (CommonUtil.isNotNull(users[COLUMN_ZIPCODE])) {
			try {
				geographic.setZipcode(Long.parseLong(users[COLUMN_ZIPCODE]));
			} catch (NumberFormatException numberFormatException) {
				LOGGER.error(numberFormatException);
				return FALSE;
			}
		} else {
			return FALSE;
		}

		Geographic oldGeographic = this.geographicRepository.findByZipcode(geographic.getZipcode());

		if (CommonUtil.isNull(oldGeographic) || CommonUtil.isNull(oldGeographic.getId())) {
			if (CommonUtil.isNotNull(users[COLUMN_CITY])) {
				geographic.setCity(users[COLUMN_CITY]);
			} else {
				return FALSE;
			}
			if (CommonUtil.isNotNull(users[COLUMN_STATE])) {
				geographic.setState(users[COLUMN_STATE]);
			} else {
				return FALSE;
			}
			if (CommonUtil.isNotNull(users[COLUMN_COUNTRY])) {
				geographic.setCountry(users[COLUMN_COUNTRY]);
			} else {
				return FALSE;
			}

			this.geographicRepository.save(geographic);

			address.setGeoId(geographic.getId());
		} else {
			address.setGeoId(oldGeographic.getId());
		}

		address.setUserId(user.getId());
		this.addressRepository.save(address);

		CreditCard creditCard = new CreditCard();

		if (CommonUtil.isNotNull(users[COLUMN_CARD_NUMBER])) {
			creditCard.setCardNumber(users[COLUMN_CARD_NUMBER]);
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_VALID_FROM_DATE])) {
			try {
				creditCard.setValidFromDate(dateFormat.parse(users[COLUMN_VALID_FROM_DATE]));
			} catch (ParseException e) {
				LOGGER.error("Error while parsing valid from date", e);
				return FALSE;
			}
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_VALID_TO_DATE])) {
			try {
				creditCard.setValidToDate(dateFormat.parse(users[COLUMN_VALID_TO_DATE]));
			} catch (ParseException e) {
				LOGGER.error("Error while parsing valid to date", e);
				return FALSE;
			}
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_CVV])) {
			try {
				creditCard.setCvv(Long.parseLong(users[COLUMN_CVV]));
			} catch (NumberFormatException numberFormatException) {
				LOGGER.error(numberFormatException);
				return FALSE;
			}
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_NAME_ON_CARD])) {
			creditCard.setNameOnCard(users[COLUMN_NAME_ON_CARD]);
		}
		if (CommonUtil.isNotNull(users[COLUMN_CREDIT_LIMIT])) {
			try {
				creditCard.setCreditLimit(Double.parseDouble(users[COLUMN_CREDIT_LIMIT]));
			} catch (NumberFormatException numberFormatException) {
				LOGGER.error(numberFormatException);
				return FALSE;
			}
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(users[COLUMN_ROYALTY_POINTS])) {
			try {
				creditCard.setRoyaltyPoints(Long.parseLong(users[COLUMN_ROYALTY_POINTS]));
			} catch (NumberFormatException numberFormatException) {
				LOGGER.error(numberFormatException);
			}
		}

		creditCard.setCardHolderId(user.getId());
		creditCard.setAvailableCreditLimit(creditCard.getCreditLimit());
		creditCard.setCreatedBy(user.getCreatedBy());
		calculateStatementDate(creditCard);
		this.cardRepository.save(creditCard);

		user.setState(State.DONE.toString());
		user.setHasCreditCard(CCMConstant.TRUE);
		this.userRepository.save(user);
		pendingCustomers.remove(user.getId());
		LOGGER.info("User details added successfully.");
		return TRUE;
	}

	/**
	 * Removes the customers.
	 * 
	 * @param pendingCustomers
	 *            the pending customers
	 */
	private void removeCustomers(List<Long> pendingCustomers) {
		if (!pendingCustomers.isEmpty())
			for (Long userId : pendingCustomers) {
				this.usersService.removeCustomer(userId);
			}
	}

	/**
	 * Adds the transaction details.
	 * 
	 * @param transactions
	 *            the transaction details
	 * @param dateFormat
	 *            the date format
	 * @param pendingTransactions
	 *            the pending transactions
	 * @return true, if successful
	 */
	private boolean saveTransactions(String[] transactions, List<Long> pendingTransactions) {
		LOGGER.info("Add Transaction details.");
		Transaction transaction = new Transaction();

		CreditCard creditCard = null;

		if (CommonUtil.isNotNull(transactions[COLUMN_CREDIT_CARD_NUMBER])) {
			creditCard = cardRepository.findByCardNumber(transactions[COLUMN_CREDIT_CARD_NUMBER]);
			if (CommonUtil.isNotNull(creditCard) && CommonUtil.isNotNull(creditCard.getId())) {
				transaction.setCardNumber(transactions[COLUMN_CREDIT_CARD_NUMBER]);
				transaction.setCreditCardId(creditCard.getId());
				User user = userRepository.findById(creditCard.getCardHolderId());
				if (CommonUtil.isNotNull(user)) {
					transaction.setCustomerName(user.getFirstName() + SPACE + user.getLastName());
				}
			} else {
				return FALSE;
			}
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(transactions[COLUMN_TRANSACTION_AMOUNT])) {
			try {
				double amount = CommonUtil.getFormatedNumber(Double.valueOf(transactions[COLUMN_TRANSACTION_AMOUNT]));
				transaction.setAmount(amount);
				if (amount < creditCard.getCreditLimit() && amount < creditCard.getAvailableCreditLimit()) {
					transaction.setBalance(creditCard.getAvailableCreditLimit() - amount);
				} else {
					return FALSE;
				}
			} catch (Exception exception) {
				LOGGER.error(exception);
				return FALSE;
			}
		} else {
			return FALSE;
		}

		if (CommonUtil.isNotNull(transactions[COLUMN_TRANSACTION_DATE])) {
			try {
				transaction.setTransactionDate(dateFormat.parse(transactions[COLUMN_TRANSACTION_DATE]));
			} catch (ParseException e) {
				LOGGER.error("Error while parsing transaction date.", e);
				return FALSE;
			}
		} else {
			return FALSE;
		}
		if (CommonUtil.isNotNull(transactions[COLUMN_DESCRIPTION])) {
			transaction.setDescription(transactions[COLUMN_DESCRIPTION]);
		}

		transaction.setState(CCMConstant.State.PENDING.toString());
		transactionService.saveTransaction(transaction);
		pendingTransactions.add(transaction.getId());
		creditCard.setAvailableCreditLimit(transaction.getBalance());
		cardRepository.save(creditCard);
		transaction.setState(CCMConstant.State.DONE.toString());
		transactionService.saveTransaction(transaction);
		pendingTransactions.remove(transaction.getId());
		LOGGER.info("Transaction details added successfully.");
		return TRUE;
	}

	/**
	 * Removes the transactions.
	 * 
	 * @param pendingTransactions
	 *            the pending transactions
	 */
	private void removeTransactions(List<Long> pendingTransactions) {
		if (!pendingTransactions.isEmpty())
			for (Long transactionId : pendingTransactions) {
				this.transactionService.removeTransaction(transactionId);
			}
	}

	/**
	 * {@inheritDoc}
	 */
	public long nextBulkUploadId() {

		long uploadId = 0;
		BulkUpload bulkUpload = bulkUploadRepository.findLastBulkUpload();

		if (CommonUtil.isNotNull(bulkUpload) && bulkUpload.getBulkUploadId() > ZERO) {
			uploadId = bulkUpload.getBulkUploadId() + ONE;
		} else {
			uploadId = UPLOAD_DEFAULT_ID;
		}
		return uploadId;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BulkUpload> getBulkUploads(int start, int end, String sortField, String sortOrder, Map<String, Object> filters) {
		List<BulkUpload> bulkUploadList = bulkUploadRepository.getBulkUploads(start, end, sortField, sortOrder, filters);
		return bulkUploadList;
	}

	/**
	 * {@inheritDoc}
	 */
	public long getBulkUploadsCount(Map<String, Object> filters) {
		return bulkUploadRepository.getBulkUploadsCount(filters);
	}
}