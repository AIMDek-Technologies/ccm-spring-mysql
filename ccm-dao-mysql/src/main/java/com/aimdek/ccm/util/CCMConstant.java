package com.aimdek.ccm.util;

/**
 * The Class CCMConstant.
 * 
 * @author aimdek.team
 */
public final class CCMConstant {

	/** The Constant COLLECTION_NAME_USER. */
	public static final String COLLECTION_NAME_USER = "ccm_users";

	/** The Constant COLLECTION_NAME_ADDRESS. */
	public static final String COLLECTION_NAME_ADDRESS = "ccm_address";

	/** The Constant COLLECTION_NAME_CREDITCARD. */
	public static final String COLLECTION_NAME_CREDITCARD = "ccm_credit_card";

	/** The Constant COLLECTION_NAME_TRANSACTIONS. */
	public static final String COLLECTION_NAME_TRANSACTIONS = "ccm_transactions";

	/** The Constant COLLECTION_NAME_GEOGRAPHIC. */
	public static final String COLLECTION_NAME_GEOGRAPHIC = "ccm_geographic";

	/** The Constant COLLECTION_NAME_STATEMENT. */
	public static final String COLLECTION_NAME_STATEMENT = "ccm_statements";

	/** The Constant COLLECTION_NAME_BULKUPLOAD. */
	public static final String COLLECTION_NAME_BULKUPLOAD = "ccm_bulkupload";

	/** The Constant LOGIN_MESSAGE_KEY. */
	public static final String LOGIN_MESSAGE_KEY = "login.message";

	/** The Constant LOGIN_EMAIL_MESSAGE_KEY. */
	public static final String LOGIN_EMAIL_MESSAGE_KEY = "login.email.message";

	/** The Constant LOGIN_PWD_MESSAGE_KEY. */
	public static final String LOGIN_PWD_MESSAGE_KEY = "login.pwd.message";

	/** The Constant LOGIN_INVALID_MESSAGE_KEY. */
	public static final String LOGIN_INVALID_MESSAGE_KEY = "login.invalid.message";

	/** The Constant AUTHENTICATED_SESSION. */
	public static final String AUTHENTICATED_SESSION = "AUTHENTICATED SESSION";

	/** The Constant LOGOUT_URL. */
	public static final String LOGOUT_URL = "/ccm/login";

	/** The Constant SPRING_LOGOUT_URL. */
	public static final String SPRING_LOGOUT_URL = "logout";

	/** The Constant HOME_URL. */
	public static final String HOME_URL = "/ccm/index";

	/** The Constant ROLE_HELPDESK. */
	public static final String ROLE_HELPDESK = "HELPDESK";

	/** The Constant ROLE_CUSTOMER. */
	public static final String ROLE_CUSTOMER = "CUSTOMER";

	/** The Constant ADD_CUSTOMER_BASIC_DETAIL_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_BASIC_DETAIL_MESSAGE_KEY = "customer.validation.basic.details";

	/** The Constant ADD_CUSTOMER_BASIC_DETAIL_FIRSTNAME_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_BASIC_DETAIL_FIRSTNAME_MESSAGE_KEY = "customer.validation.basic.details.firstname";

	/** The Constant ADD_CUSTOMER_BASIC_DETAIL_LASTNAME_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_BASIC_DETAIL_LASTNAME_MESSAGE_KEY = "customer.validation.basic.details.lastname";

	/** The Constant ADD_CUSTOMER_BASIC_DETAIL_BIRTHDATE_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_BASIC_DETAIL_BIRTHDATE_MESSAGE_KEY = "customer.validation.basic.details.birthdate";

	/** The Constant ADD_CUSTOMER_BASIC_DETAIL_EMAIL_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_BASIC_DETAIL_EMAIL_MESSAGE_KEY = "customer.validation.basic.details.email";

	/** The Constant ADD_CUSTOMER_BASIC_DETAIL_EMAIL_INVALID_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_BASIC_DETAIL_EMAIL_INVALID_MESSAGE_KEY = "customer.validation.basic.details.email.invalid";

	/** The Constant ADD_CUSTOMER_BASIC_DETAIL_DUPLICATE_EMAIL_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_BASIC_DETAIL_DUPLICATE_EMAIL_MESSAGE_KEY = "customer.validation.basic.details.duplicate.email";

	/** The Constant ADD_CUSTOMER_BASIC_DETAIL_PHONE_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_BASIC_DETAIL_PHONE_MESSAGE_KEY = "customer.validation.basic.details.phone";

	/** The Constant ADD_CUSTOMER_ADDRESS_DETAIL_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_ADDRESS_DETAIL_MESSAGE_KEY = "customer.validation.address.details";

	/** The Constant ADD_CUSTOMER_ADDRESS_DETAIL_HOUSENUMBER_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_ADDRESS_DETAIL_HOUSENUMBER_MESSAGE_KEY = "customer.validation.address.details.housenumber";

	/** The Constant ADD_CUSTOMER_GEOGRAPHIC_DETAIL_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_GEOGRAPHIC_DETAIL_MESSAGE_KEY = "customer.validation.geographic.details";

	/** The Constant ADD_CUSTOMER_GEOGRAPHIC_DETAIL_ZIP_LENGTH_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_GEOGRAPHIC_DETAIL_ZIP_LENGTH_MESSAGE_KEY = "customer.validation.geographic.zip.length";

	/** The Constant ADD_CUSTOMER_CREDITCARD_DETAIL_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_CREDITCARD_DETAIL_MESSAGE_KEY = "customer.validation.creditcard.details";

	/** The Constant ADD_CUSTOMER_CREDITCARD_DETAIL_CARDNUMBER_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_CREDITCARD_DETAIL_CARDNUMBER_MESSAGE_KEY = "customer.validation.creditcard.details.cardnumber";

	/** The Constant ADD_CUSTOMER_CREDITCARD_DETAIL_NAME_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_CREDITCARD_DETAIL_NAME_MESSAGE_KEY = "customer.validation.creditcard.details.nameoncard";

	/** The Constant ADD_CUSTOMER_CREDITCARD_DETAIL_CVV_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_CREDITCARD_DETAIL_CVV_MESSAGE_KEY = "customer.validation.creditcard.details.cvv";

	/** The Constant ADD_CUSTOMER_CREDITCARD_DETAIL_CVV__LENGTH_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_CREDITCARD_DETAIL_CVV__LENGTH_MESSAGE_KEY = "customer.validation.creditcard.details.cvv.length";

	/** The Constant ADD_CUSTOMER_CREDITCARD_DETAIL_VALIDFROM_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_CREDITCARD_DETAIL_VALIDFROM_MESSAGE_KEY = "customer.validation.creditcard.details.validFrom";

	/** The Constant ADD_CUSTOMER_CREDITCARD_DETAIL_VALIDTO_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_CREDITCARD_DETAIL_VALIDTO_MESSAGE_KEY = "customer.validation.creditcard.details.validTo";

	/**
	 * The Constant
	 * ADD_CUSTOMER_CREDITCARD_DETAIL_ADD_CARD__CUSTOMER_MESSAGE_KEY.
	 */
	public static final String ADD_CUSTOMER_CREDITCARD_DETAIL_ADD_CARD__CUSTOMER_MESSAGE_KEY = "customer.validation.card.select.customer";

	/** The Constant INTIAL_CREDIT_LIMIT. */
	public static final double INTIAL_CREDIT_LIMIT = 20000.00;

	/** The Constant MASTER_COUNTRY. */
	public static final String MASTER_COUNTRY = "country";

	/** The Constant MASTER_STATE. */
	public static final String MASTER_STATE = "state";

	/** The Constant MASTER_CITY. */
	public static final String MASTER_CITY = "city";

	/** The Constant ADD_CUSTOMER_SUCCESS_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_SUCCESS_MESSAGE_KEY = "customer.success.add.customer";

	/** The Constant SORT_ORDER_ASCENDING. */
	public static final String SORT_ORDER_ASCENDING = "ASC";

	/** The Constant FILTER_REGEX_OPTION. */
	public static final String FILTER_REGEX_OPTION = "i";

	/** The Constant FIELDCONSTANT_ROWINDEX. */
	public static final String FIELDCONSTANT_ROWINDEX = "rowIndex";

	/** The Constant FIELDCONSTANT_CARDNUMBER. */
	public static final String FIELDCONSTANT_CARDNUMBER = "cardNumber";
	
	/** The Constant FIELD_CONSTANT_DESCRIPTION. */
	public static final String FIELD_CONSTANT_DESCRIPTION = "description";

	/** The Constant FIELDCONSTANT_CREDITLIMIT. */
	public static final String FIELDCONSTANT_CREDITLIMIT = "creditlimit";

	/** The Constant FIELDCONSTANT_BALANCE. */
	public static final String FIELDCONSTANT_BALANCE = "availableCreditLimit";

	/** The Constant FIELDCONSTANT_CARDHOLDERID. */
	public static final String FIELDCONSTANT_CARDHOLDERID = "cardHolderId";

	/** The Constant FIELDCONSTANT_USER_ROLE. */
	public static final String FIELDCONSTANT_USER_ROLE = "role";

	/** The Constant FIELDCONSTANT_FIRSTNAME. */
	public static final String FIELDCONSTANT_FIRSTNAME = "firstName";

	/** The Constant FIELDCONSTANT_LASTNAME. */
	public static final String FIELDCONSTANT_LASTNAME = "lastName";

	/** The Constant FIELDCONSTANT_EMAIL. */
	public static final String FIELDCONSTANT_EMAIL = "email";

	/** The Constant FIELDCONSTANT_PHONE. */
	public static final String FIELDCONSTANT_PHONE = "phoneNumber";

	/** The Constant FIELDCONSTANT_COUNTRY. */
	public static final String FIELDCONSTANT_COUNTRY = "country";

	/** The Constant FIELDCONSTANT_STATE. */
	public static final String FIELDCONSTANT_STATE = "state";

	/** The Constant FIELDCONSTANT_ZIPCODE. */
	public static final String FIELDCONSTANT_ZIPCODE = "zipcode";

	/** The Constant FIELDCONSTANT_USERID. */
	public static final String FIELDCONSTANT_USERID = "userId";

	/** The Constant FIELD_CONSTANT_ID. */
	public static final String FIELD_CONSTANT_ID = "id";

	/** The Constant FIELD_CONSTANT_STATEMENT_ID. */
	public static final String FIELD_CONSTANT_STATEMENT_ID = "statementId";

	/** The Constant FIELD_CONSTANT_HAS_CREDIT_CARD. */
	public static final String FIELD_CONSTANT_HAS_CREDIT_CARD = "hasCreditCard";

	/** The Constant SELECT_ACTION_EDIT_KEY. */
	public static final String SELECT_ACTION_EDIT_KEY = "select.action.edit";

	/** The Constant SELECT_ACTION_DELETE_KEY. */
	public static final String SELECT_ACTION_DELETE_KEY = "select.action.delete";

	/** The Constant CUSTOMER_DELTE_CREDI_CARD_INFO_KEY. */
	public static final String CUSTOMER_DELTE_CREDI_CARD_INFO_KEY = "customer.delete.credit.card.info.message";

	/** The Constant EDIT_CUSTOMER_SUCCESS_MESSAGE_KEY. */
	public static final String EDIT_CUSTOMER_SUCCESS_MESSAGE_KEY = "customer.success.edit.customer";

	/** The Constant ADD_CUSTOMER_CARD_SUCCESS_MESSAGE_KEY. */
	public static final String ADD_CUSTOMER_CARD_SUCCESS_MESSAGE_KEY = "customer.success.add.customer.card";

	/** The Constant EDIT_CUSTOMER_CARD_SUCCESS_MESSAGE_KEY. */
	public static final String EDIT_CUSTOMER_CARD_SUCCESS_MESSAGE_KEY = "customer.success.edit.customer.card";

	/** The Constant CUSTOMER_DELETE_SUCCESS_MESSAGE_KEY. */
	public static final String CUSTOMER_DELETE_SUCCESS_MESSAGE_KEY = "customer.delete.info.message";

	/** The Constant CUSTOMER_DELETE_WARN_MESSAGE_KEY. */
	public static final String CUSTOMER_DELETE_WARN_MESSAGE_KEY = "customer.delete.warn.message";

	/** The Constant FIELDCONSTANT_CUSTOMERNAME. */
	public static final String FIELDCONSTANT_CUSTOMERNAME = "customerName";

	/** The Constant FIELD_CONSTANT_TRANSACTION_ID. */
	public static final String FIELD_CONSTANT_TRANSACTION_ID = "transactionId";

	/** The Constant FIELD_CONSTANT_TRANSACTION_DATE. */
	public static final String FIELD_CONSTANT_TRANSACTION_DATE = "transactionDate";

	/** The Constant TRANSACTION_DEFAULT_ID. */
	public static final long TRANSACTION_DEFAULT_ID = 1000;

	/** The Constant CUSTOMER_DEFAULT_ID. */
	public static final long CUSTOMER_DEFAULT_ID = 1000;

	/** The Constant UPLOAD_DEFAULT_ID. */
	public static final long UPLOAD_DEFAULT_ID = 1000;

	/** The Constant LIMIT_INFO_MSG. */
	public static final String LIMIT_INFO_MSG = "transaction.dialog.limit.info.msg";

	/** The Constant ADD_TRANSACTION_SELECT_CUSTOMER_VALIDATION_MESSAGE_KEY. */
	public static final String ADD_TRANSACTION_SELECT_CUSTOMER_VALIDATION_MESSAGE_KEY = "transaction.validation.transaction.select.customer";

	/** The Constant ADD_TRANSACTION_SELECT_CARD_VALIDATION_MESSAGE_KEY. */
	public static final String ADD_TRANSACTION_SELECT_CARD_VALIDATION_MESSAGE_KEY = "transaction.validation.transaction.select.credit.card";

	/** The Constant ADD_TRANSACTION_AMOUNT_VALIDATION_MESSAGE_KEY. */
	public static final String ADD_TRANSACTION_AMOUNT_VALIDATION_MESSAGE_KEY = "transaction.validation.transaction.amount";

	/** The Constant ADD_TRANSACTION_DATE_VALIDATION_MESSAGE_KEY. */
	public static final String ADD_TRANSACTION_DATE_VALIDATION_MESSAGE_KEY = "transaction.validation.transaction.date";

	/** The Constant ADD_TRANSACTION_SUCCESS_MESSAGE_KEY. */
	public static final String ADD_TRANSACTION_SUCCESS_MESSAGE_KEY = "transaction.add.success.msg";

	/** The Constant AWS_BUCKET_NAME. */
	public static final String AWS_BUCKET_NAME = "aimdek-proto";

	/** The Constant FIELD_CONSTANT_STATEMENT_DATE. */
	public static final String FIELD_CONSTANT_STATEMENT_DATE = "statementDate";

	/** The Constant FIELD_CONSTANT_CREATED_AT. */
	public static final String FIELD_CONSTANT_CREATED_AT = "createdAt";

	/** The Constant REPORT_RESOURCE_FOLDER_PATH. */
	public static final String REPORT_RESOURCE_FOLDER_PATH = "/reports-config/";

	/** The Constant STATEMENT_JASPER_FILE_NAME. */
	public static final String STATEMENT_JASPER_FILE_NAME = "statement.jasper";

	/** The Constant PDF_FILE_EXTENTION. */
	public static final String PDF_FILE_EXTENTION = ".pdf";

	/** The Constant AMAZON_S3_FILE_UPLOAD_PATH. */
	public static final String AMAZON_S3_FILE_UPLOAD_PATH = "jsf-mongo/user/[userId]/";

	/** The Constant AMAZON_S3_FILE_UPLOAD_STATEMENT_PATH. */
	public static final String AMAZON_S3_FILE_UPLOAD_STATEMENT_PATH = "statement/[creditCardNumber]";

	/** The Constant FILE_PATH_PARAMETER_USERID. */
	public static final String FILE_PATH_PARAMETER_USERID = "[userId]";

	/** The Constant FILE_PATH_PARAMETER_CREDIT_CARD_NUMBER. */
	public static final String FILE_PATH_PARAMETER_CREDIT_CARD_NUMBER = "[creditCardNumber]";

	/** The Constant AMAZON_S3_FILE_UPLOAD_PROFILE_PIC_PATH. */
	public static final String AMAZON_S3_FILE_UPLOAD_PROFILE_PIC_PATH = "profile_pic";

	/** The Constant TWO_DECIMAL_PLACE_FORMAT. */
	public static final String TWO_DECIMAL_PLACE_FORMAT = "#.00";

	/** The Constant DEFAULT_STATEMENT_ID. */
	public static final long DEFAULT_STATEMENT_ID = 1000;

	/** The Constant STATEMENT_KEY. */
	public static final String STATEMENT_KEY = "statement_";

	/** The Constant PROFILE_KEY. */
	public static final String PROFILE_KEY = "profile_";

	/** The Constant EXTENSION_JPG. */
	public static final String EXTENSION_JPG = "jpg";

	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "dd/MM/yyyy";

	/** The Constant REPORT_PARAM_CARDNUMBER. */
	public static final String REPORT_PARAM_CARDNUMBER = "cardNumber";

	/** The Constant REPORT_PARAM_NAME. */
	public static final String REPORT_PARAM_NAME = "name";

	/** The Constant REPORT_PARAM_PAYMENTDATE. */
	public static final String REPORT_PARAM_PAYMENTDATE = "paymentDate";

	/** The Constant REPORT_PARAM_PAYMENTDUE. */
	public static final String REPORT_PARAM_PAYMENTDUE = "paymentDue";

	/** The Constant REPORT_PARAM_CREDITAVAILABLE. */
	public static final String REPORT_PARAM_CREDITAVAILABLE = "creditAvailable";

	/** The Constant REPORT_PARAM_ADDRESS. */
	public static final String REPORT_PARAM_ADDRESS = "address";

	/** The Constant REPORT_PARAM_STATEMENTDATE. */
	public static final String REPORT_PARAM_STATEMENTDATE = "statementDate";

	/** The Constant PDF_CONTENT_TYPE. */
	public static final String PDF_CONTENT_TYPE = "application/pdf";

	/** The Constant DOWNLOAD_STATEMENT_NAME. */
	public static final String DOWNLOAD_STATEMENT_NAME = "e-statement";

	/** The Constant BULKUPLOAD_TEMP_FILE_NAME. */
	public static final String BULKUPLOAD_TEMP_FILE_NAME = "bulkUpload_";

	/** The Constant CSV_FILE_EXTENTION. */
	public static final String CSV_FILE_EXTENTION = ".csv";

	/** The Constant CSV_FILE_CONTENT_TYPE. */
	public static final String CSV_FILE_CONTENT_TYPE = "text/csv";

	/** The Constant EXCEL_FILE_CONTENT_TYPE. */
	public static final String EXCEL_FILE_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/** The Constant EXCEL_FILE_EXTENTION. */
	public static final String EXCEL_FILE_EXTENTION = ".xlsx";

	/** The Constant MS_EXCEL_FILE_CONTENT_TYPE. */
	public static final String MS_EXCEL_FILE_CONTENT_TYPE = "application/vnd.ms-excel";

	/** The Constant MS_EXCEL_FILE_EXTENTION. */
	public static final String MS_EXCEL_FILE_EXTENTION = ".xls";

	/**
	 * The Enum STATE.
	 */
	public static enum State {

		/** The initial. */
		INITIAL,
		/** The pending. */
		PENDING,
		/** The committed. */
		COMMITTED,
		/** The done. */
		DONE,
		/** The canceling. */
		CANCELING,
		/** The cancelled. */
		CANCELLED
	}

	/** The bulk upload success msg. */
	public static final String BULK_UPLOAD_SUCCESS_MSG = "Total [successRecords] records saved successfully out of [totalRecords] records";

	/** The bulk upload type customer. */
	public static final String BULK_UPLOAD_TYPE_CUSTOMER = "Customer";

	/** The bulk upload type transaction. */
	public static final String BULK_UPLOAD_TYPE_TRANSACTION = "Transaction";

	/** The temp folder path. */
	public static final String TEMP_FOLDER_PATH = "/temp-images/";

	/** The resource image folder. */
	public static final String RESOURCE_IMAGE_FOLDER = "/resources/images";

	/** The Constant REDIS_PROPERTY_HOST_KEY. */
	public static final String REDIS_PROPERTY_HOST_KEY = "${redis.host}";

	/** The Constant REDIS_PROPERTY_PORT_KEY. */
	public static final String REDIS_PROPERTY_PORT_KEY = "${redis.port}";

	/**
	 * Instantiates a new CCM constant.
	 */
	private CCMConstant() {

	}

	/** The Constant CONSTANT_TYPE. */
	public static final String CONSTANT_TYPE = "type";

	/** The Constant LOGIN_PASSWORD_MESSAGE_KEY. */
	public static final String LOGIN_PASSWORD_MESSAGE_KEY = "login.password.message";

	/** The Constant USER_DEFAULT_PASSWORD. */
	public static final String USER_DEFAULT_PASSWORD = "test123$";

	/** The Constant FIELDCONSTANT_NAME_ON_CARD. */
	public static final String FIELDCONSTANT_NAME_ON_CARD = "nameOnCard";

	/** The Constant FIELDCONSTANT_CVV. */
	public static final String FIELDCONSTANT_CVV = "cvv";

	/** The Constant FIELDCONSTANT_VALID_FROM_DATE. */
	public static final String FIELDCONSTANT_VALID_FROM_DATE = "validFromDate";

	/** The Constant FIELDCONSTANT_VALID_TO_DATE. */
	public static final String FIELDCONSTANT_VALID_TO_DATE = "validToDate";

	/** The Constant FIELD_CONSTANT_CREDIT_CARD_ID. */
	public static final String FIELD_CONSTANT_CREDIT_CARD_ID = "creditCardId";

	/** The Constant FIELD_CONSTANT_AMOUNT. */
	public static final String FIELD_CONSTANT_AMOUNT = "amount";

	/** The Constant DATATABLE_CONSTANT_IDISPLAYSTART. */
	// Constant used for jquery datatables
	public static final String DATATABLE_CONSTANT_IDISPLAYSTART = "iDisplayStart";

	/** The Constant DATATABLE_CONSTANT_IDISPLAYLENGTH. */
	public static final String DATATABLE_CONSTANT_IDISPLAYLENGTH = "iDisplayLength";

	/** The Constant DATATABLE_CONSTANT_ISORTCOL_0. */
	public static final String DATATABLE_CONSTANT_ISORTCOL_0 = "iSortCol_0";

	/** The Constant DATATABLE_CONSTANT_SSORTDIR_0. */
	public static final String DATATABLE_CONSTANT_SSORTDIR_0 = "sSortDir_0";

	/** The Constant DATATABLE_CONSTANT_SECHO. */
	public static final String DATATABLE_CONSTANT_SECHO = "sEcho";

	/** The Constant DATATABLE_CONSTANT_SSEARCH_1. */
	public static final String DATATABLE_CONSTANT_SSEARCH_1 = "sSearch_1";

	/** The Constant DATATABLE_CONSTANT_SSEARCH_2. */
	public static final String DATATABLE_CONSTANT_SSEARCH_2 = "sSearch_2";

	/** The Constant DATATABLE_CONSTANT_SSEARCH_3. */
	public static final String DATATABLE_CONSTANT_SSEARCH_3 = "sSearch_3";

	/** The Constant DATATABLE_CONSTANT_SSEARCH_4. */
	public static final String DATATABLE_CONSTANT_SSEARCH_4 = "sSearch_4";

	/** The Constant DATATABLE_CONSTANT_SSEARCH_5. */
	public static final String DATATABLE_CONSTANT_SSEARCH_5 = "sSearch_5";

	/** The Constant DATATABLE_CONSTANT_SSEARCH_6. */
	public static final String DATATABLE_CONSTANT_SSEARCH_6 = "sSearch_6";

	// Common constant.

	/** The Constant BLANK. */
	public static final String BLANK = "";

	/** The Constant TRUE. */
	public static final boolean TRUE = true;

	/** The Constant PIPE. */
	public static final String PIPE = "|";

	/** The Constant COLON. */
	public static final String COLON = ":";

	/** The Constant DOT. */
	public static final String DOT = ".";

	/** The Constant COMMA. */
	public static final String COMMA = ",";

	/** The Constant FALSE. */
	public static final boolean FALSE = false;

	/** The Constant DASH. */
	public static final String DASH = "-";

	/** The Constant SPACE. */
	public static final char SPACE = ' ';

	/** The Constant LOWER_CASE_N. */
	public static final char LOWER_CASE_N = 'n';

	/** The Constant LOWER_CASE_U. */
	public static final char LOWER_CASE_U = 'u';

	/** The Constant LOWER_CASE_L. */
	public static final char LOWER_CASE_L = 'l';

	/** The Constant EMAIL_PATTERN. */
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";

	/** The Constant INDIAN_RUPEES. */
	public static final String INDIAN_RUPEES = "RS";

	/** The Constant FORWARD_SLASH. */
	public static final String FORWARD_SLASH = "/";

	/** The Constant BACK_SLASH. */
	public static final String BACK_SLASH = "\\";
	
	/** The Constant OPEN_ROUND_BRACKET. */
	public static final String OPEN_ROUND_BRACKET = "(";

	/** The Constant CLOSE_ROUND_BRACKET. */
	public static final String CLOSE_ROUND_BRACKET = ")";

	/** The Constant ZERO. */
	public static final int ZERO = 0;

	/** The Constant ONE. */
	public static final int ONE = 1;

	/** The Constant TWO. */
	public static final int TWO = 2;

	/** The Constant THREE. */
	public static final int THREE = 3;

	/** The Constant FOUR. */
	public static final int FOUR = 4;

	/** The Constant TWENTY. */
	public static final int TWENTY = 20;

	/** The Constant THOUSAND. */
	public static final int THOUSAND = 1000;

	/** The Constant SUCCESS_RECORDS. */
	public static final String SUCCESS_RECORDS = "[successRecords]";

	/** The Constant TOTAL_RECORDS. */
	public static final String TOTAL_RECORDS = "[totalRecords]";

	/** The Constant EMAIL_TEMPLATE_PATH. */
	public static final String EMAIL_TEMPLATE_PATH = "velocity/customer_registration_email_template.vm";

	/** The Constant CUSTOMER_REGISTRATION_EMAIL_SUBJECT. */
	public static final String CUSTOMER_REGISTRATION_EMAIL_SUBJECT = "Customer Registration";

	/** The Constant TEXT_HTML. */
	public static final String TEXT_HTML = "text/html";

	/** The Constant AMAZON_PRESIGNED_URL_EXPIRATION_INTERVAL. */
	public static final int AMAZON_PRESIGNED_URL_EXPIRATION_INTERVAL = 20;

	/** The Constant UTF8. */
	public static final String UTF8 = "UTF-8";

	/** The Constant MODULO. */
	public static final String MODULO = "%";

	// Customer bulk upload file constant.

	/** The Constant COLUMN_FIRST_NAME. */
	public static final int COLUMN_FIRST_NAME = 0;

	/** The Constant COLUMN_LAST_NAME. */
	public static final int COLUMN_LAST_NAME = 1;

	/** The Constant COLUMN_EMAIL. */
	public static final int COLUMN_EMAIL = 2;

	/** The Constant COLUMN_BIRTH_DATE. */
	public static final int COLUMN_BIRTH_DATE = 3;

	/** The Constant COLUMN_PHONE_NUMBER. */
	public static final int COLUMN_PHONE_NUMBER = 4;

	/** The Constant COLUMN_PASSWORD. */
	public static final int COLUMN_PASSWORD = 5;

	/** The Constant COLUMN_HOUSE_NAME. */
	public static final int COLUMN_HOUSE_NAME = 6;

	/** The Constant COLUMN_STREET. */
	public static final int COLUMN_STREET = 7;

	/** The Constant COLUMN_AREA. */
	public static final int COLUMN_AREA = 8;

	/** The Constant COLUMN_CITY. */
	public static final int COLUMN_CITY = 9;

	/** The Constant COLUMN_STATE. */
	public static final int COLUMN_STATE = 10;

	/** The Constant COLUMN_COUNTRY. */
	public static final int COLUMN_COUNTRY = 11;

	/** The Constant COLUMN_ZIPCODE. */
	public static final int COLUMN_ZIPCODE = 12;

	/** The Constant COLUMN_CARD_NUMBER. */
	public static final int COLUMN_CARD_NUMBER = 13;

	/** The Constant COLUMN_VALID_FROM_DATE. */
	public static final int COLUMN_VALID_FROM_DATE = 14;

	/** The Constant COLUMN_VALID_TO_DATE. */
	public static final int COLUMN_VALID_TO_DATE = 15;

	/** The Constant COLUMN_CVV. */
	public static final int COLUMN_CVV = 16;

	/** The Constant COLUMN_NAME_ON_CARD. */
	public static final int COLUMN_NAME_ON_CARD = 17;

	/** The Constant COLUMN_CREDIT_LIMIT. */
	public static final int COLUMN_CREDIT_LIMIT = 18;

	/** The Constant COLUMN_ROYALTY_POINTS. */
	public static final int COLUMN_ROYALTY_POINTS = 19;

	// Transaction bulk upload file constant.

	/** The Constant COLUMN_CREDIT_CARD_NUMBER. */
	public static final int COLUMN_CREDIT_CARD_NUMBER = 0;

	/** The Constant COLUMN_TRANSACTION_AMOUNT. */
	public static final int COLUMN_TRANSACTION_AMOUNT = 1;

	/** The Constant COLUMN_TRANSACTION_DATE. */
	public static final int COLUMN_TRANSACTION_DATE = 2;

	/** The Constant COLUMN_DESCRIPTION. */
	public static final int COLUMN_DESCRIPTION = 3;

}
