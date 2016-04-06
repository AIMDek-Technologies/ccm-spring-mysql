/**
 * 
 */
package com.aimdek.ccm.service.impl;

import static com.aimdek.ccm.util.CCMConstant.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class StatementServiceImpl.
 *
 * @author aimdek.team
 */
@Service(value = "statementService")
public class StatementServiceImpl implements StatementService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(StatementServiceImpl.class);

	/** The credit card repository. */
	@Autowired
	private CreditCardRepository cardRepository;

	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;

	/** The statement repository. */
	@Autowired
	private StatementRepository statementRepository;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/** The geographic repository. */
	@Autowired
	private GeographicRepository geographicRepository;

	/** The amazon repository service. */
	private AmazonRepositoryService amazonRepositoryService;

	/**
	 * Instantiates a new statement service impl.
	 */
	public StatementServiceImpl() {

	}

	/**
	 * Instantiates a new statement service impl.
	 *
	 * @param amazonRepositoryService
	 *            the amazon repository service
	 */
	@Autowired(required = false)
	public StatementServiceImpl(@Qualifier("ammazonRepositoryArgService") AmazonRepositoryService amazonRepositoryService) {
		this.amazonRepositoryService = amazonRepositoryService;
	}

	/**
	 * {@inheritDoc}
	 */
	public void generateMonthlyStatement() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		Calendar calendarStart = Calendar.getInstance();
		calendarStart.add(Calendar.DAY_OF_MONTH, -1);
		calendarStart.set(Calendar.HOUR_OF_DAY, 0);
		calendarStart.set(Calendar.MINUTE, 0);
		calendarStart.set(Calendar.SECOND, 0);

		Date statementEndDate = calendar.getTime();

		Date statementStartDate = calendarStart.getTime();

		// List of credit card whose statement date is one day before the
		// current date
		List<CreditCard> creditCardList = cardRepository.findByStatementDateBetween(statementStartDate, statementEndDate);

		if (!creditCardList.isEmpty()) {
			for (CreditCard creditCard : creditCardList) {

				Statement currentStatement = new Statement();

				List<Transaction> transactionList;
				if (CommonUtil.isNotNull(creditCard.getLastStatementDate())) {
					transactionList = new ArrayList<Transaction>();
					transactionList = transactionRepository.findTransactionsByStatementDateAndCreditCardId(creditCard.getLastStatementDate(), creditCard.getStatementDate(), creditCard.getId());
					currentStatement.setFromDate(creditCard.getStatementDate());
					currentStatement.setToDate(statementEndDate);
				} else {
					transactionList = new ArrayList<Transaction>();
					transactionList = transactionRepository.findTransactionsByStatementDateAndCreditCardId(creditCard.getCreatedAt(), creditCard.getStatementDate(), creditCard.getId());
					currentStatement.setFromDate(creditCard.getCreatedAt());
					currentStatement.setToDate(statementEndDate);
				}

				List<TransactionReportDto> transactionReportDtoList = new ArrayList<TransactionReportDto>();
				// Loop through transaction to find amount due
				if (!transactionList.isEmpty()) {
					double amountDue = 0;
					transactionReportDtoList.add(0, new TransactionReportDto());
					for (Transaction transaction : transactionList) {

						TransactionReportDto transactionReportDto = new TransactionReportDto();
						transactionReportDto.setAmount(transaction.getAmount());
						transactionReportDto.setDescription(transaction.getDescription());
						String transactionDate = CommonUtil.getFormattedDate(transaction.getTransactionDate());
						transactionReportDto.setTransactionDate(transactionDate);
						transactionReportDtoList.add(transactionReportDto);

						amountDue = amountDue + transaction.getAmount();
					}
					currentStatement.setAmountDue(amountDue);
					// Due is after 15 days of the statementDate
					calendar = Calendar.getInstance();
					calendar.setTime(CommonUtil.toDay());
					calendar.add(Calendar.DATE, 15);
					calendar.set(Calendar.HOUR_OF_DAY, 23);
					calendar.set(Calendar.MINUTE, 59);
					calendar.set(Calendar.MINUTE, 59);

					currentStatement.setDueDate(calendar.getTime());

					currentStatement.setCreditCardId(creditCard.getId());
					currentStatement.setCardNumber(creditCard.getCardNumber());
					currentStatement.setStatementDate(CommonUtil.toDay());
					currentStatement.setStatementId(nextStatementId());

					User user = userRepository.findById(creditCard.getCardHolderId());
					Address address = addressRepository.findByUserId(creditCard.getCardHolderId());
					Geographic geographic = geographicRepository.findById(address.getGeoId());

					String fullAddress = address.getHouseName() + SPACE + address.getArea() + SPACE + geographic.getState() + SPACE + geographic.getCountry() + SPACE + geographic.getZipcode();

					String statementDate = CommonUtil.getFormattedDate(currentStatement.getFromDate()) + DASH + CommonUtil.getFormattedDate(currentStatement.getToDate());

					String url = generatePdfReport(transactionReportDtoList, creditCard.getCardNumber(), user.getId(), user.getFirstName() + SPACE + user.getLastName(),
							creditCard.getAvailableCreditLimit(), currentStatement.getAmountDue(), currentStatement.getDueDate(), statementDate, fullAddress);

					if (CommonUtil.isNotNull(url)) {
						currentStatement.setDocumentReference(url);
					}

					statementRepository.saveStatement((currentStatement));

					// Update last and next statement Date in credit card table
					creditCard.setLastStatementDate(CommonUtil.toDay());
					calculateStatementDate(creditCard);
					cardRepository.saveCreditCard((creditCard));
				}
			}
		}
	}


	/**
	 * Calculate statement date.
	 *
	 * @param creditCard
	 *            the credit card
	 */
	private void calculateStatementDate(CreditCard creditCard) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(CommonUtil.toDay());
		calendar.add(Calendar.MONTH, 1);
		creditCard.setStatementDate(calendar.getTime());
	}

	/**
	 * {@inheritDoc}
	 */
	public long nextStatementId() {

		long statementId = 0;

		Statement statement = statementRepository.findLastStatement();

		if (CommonUtil.isNotNull(statement)) {
			statementId = statement.getStatementId() + 1;
		} else {
			statementId = DEFAULT_STATEMENT_ID;
		}
		return statementId;

	}

	/**
	 * Generate pdf report.
	 *
	 * @param transactionList
	 *            the transaction list
	 * @param cardNumber
	 *            the card number
	 * @param userId
	 *            the user id
	 * @param name
	 *            the name
	 * @param creditAvailable
	 *            the credit available
	 * @param amountDue
	 *            the amount due
	 * @param dueDate
	 *            the due date
	 * @param statementDate
	 *            the statement date
	 * @param address
	 *            the address
	 * @return the string
	 */
	public String generatePdfReport(List<TransactionReportDto> transactionList, String cardNumber, long userId, String name, double creditAvailable, double amountDue, Date dueDate,
			String statementDate, String address) {

		Map<String, Object> reportParameters = new HashMap<String, Object>();

		reportParameters.put(REPORT_PARAM_CARDNUMBER, cardNumber);
		reportParameters.put(REPORT_PARAM_NAME, name);
		String dueDateString = CommonUtil.getFormattedDate(dueDate);
		reportParameters.put(REPORT_PARAM_PAYMENTDATE, dueDateString);
		reportParameters.put(REPORT_PARAM_PAYMENTDUE, String.valueOf(amountDue) + SPACE + INDIAN_RUPEES);
		reportParameters.put(REPORT_PARAM_CREDITAVAILABLE, String.valueOf(creditAvailable) + SPACE + INDIAN_RUPEES);
		reportParameters.put(REPORT_PARAM_ADDRESS, address);
		reportParameters.put(REPORT_PARAM_STATEMENTDATE, statementDate);

		JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(transactionList);
		String sourceFileName = REPORT_RESOURCE_FOLDER_PATH + STATEMENT_JASPER_FILE_NAME;

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream(sourceFileName), reportParameters, beanCollectionDataSource);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			JRPdfExporter exporterPDF = new JRPdfExporter();
			exporterPDF.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			exporterPDF.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, os);
			exporterPDF.exportReport();

			InputStream is = new ByteArrayInputStream(os.toByteArray());
			File tempMonthStatementFile = null;
			OutputStream output = null;
			try {
				tempMonthStatementFile = File.createTempFile(STATEMENT_KEY, PDF_FILE_EXTENTION);
				output = new FileOutputStream(tempMonthStatementFile);
				IOUtils.copy(is, output);
				String filePath = AMAZON_S3_FILE_UPLOAD_PATH + AMAZON_S3_FILE_UPLOAD_STATEMENT_PATH;
				filePath = filePath.replace(FILE_PATH_PARAMETER_USERID, String.valueOf(userId));
				filePath = filePath.replace(FILE_PATH_PARAMETER_CREDIT_CARD_NUMBER, cardNumber);
				filePath = filePath + FORWARD_SLASH + STATEMENT_KEY + new Date().getTime() + PDF_FILE_EXTENTION;
				// Upload file to amazon
				amazonRepositoryService.uploadFileToAmazonS3Server(tempMonthStatementFile, filePath);

				is.close();
				output.close();

				return filePath;
			} catch (IOException e) {
				LOGGER.error("============Error while creating Statement PDF==================", e);
			} finally {
				if (CommonUtil.isNotNull(tempMonthStatementFile)) {
					tempMonthStatementFile.delete();
				}
			}
		} catch (JRException e) {
			LOGGER.error("============Error while creating Statement Report==================", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Statement> getStatements(int start, int end, String sortField, String sortOrder, Map<String, Object> filters, long userId) {

		List<Statement> statementList = statementRepository.getStatements(start, end, sortField, sortOrder, filters, userId);

		return statementList;
	}

	/**
	 * {@inheritDoc}
	 */
	public long getStatementsCount(Map<String, Object> filters, long userId) {

		return statementRepository.getStatementsCount(filters, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getAssignedUrl(String key) {
		return amazonRepositoryService.generateSignedUrl(key);
	}

	/**
	 * {@inheritDoc}
	 */
	public Statement findStatementByStatementId(long id) {
		return statementRepository.findByStatementId(id);
	}
}
