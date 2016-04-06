/**
 * 
 */
package com.aimdek.ccm.service.impl;

import static com.aimdek.ccm.util.CCMConstant.AMAZON_S3_FILE_UPLOAD_PATH;
import static com.aimdek.ccm.util.CCMConstant.AMAZON_S3_FILE_UPLOAD_PROFILE_PIC_PATH;
import static com.aimdek.ccm.util.CCMConstant.BLANK;
import static com.aimdek.ccm.util.CCMConstant.CUSTOMER_DEFAULT_ID;
import static com.aimdek.ccm.util.CCMConstant.DOT;
import static com.aimdek.ccm.util.CCMConstant.FALSE;
import static com.aimdek.ccm.util.CCMConstant.FILE_PATH_PARAMETER_USERID;
import static com.aimdek.ccm.util.CCMConstant.FORWARD_SLASH;
import static com.aimdek.ccm.util.CCMConstant.PROFILE_KEY;
import static com.aimdek.ccm.util.CCMConstant.TRUE;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aimdek.ccm.amazons3.service.AmazonRepositoryService;
import com.aimdek.ccm.custom.dto.CustomerDto;
import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.Transaction;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.repositories.AddressRepository;
import com.aimdek.ccm.repositories.CreditCardRepository;
import com.aimdek.ccm.repositories.TransactionRepository;
import com.aimdek.ccm.repositories.UserRepository;
import com.aimdek.ccm.service.UserService;
import com.aimdek.ccm.util.CommonUtil;

/**
 * The Class UserServiceImpl.
 *
 * @author aimdek.team
 */
@Service("userService")
public class UserServiceImpl implements UserService, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6086967374175603568L;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The card repository. */
	@Autowired
	private CreditCardRepository cardRepository;

	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/** The amazon repository service. */
	private AmazonRepositoryService amazonRepositoryService;

	/**
	 * Instantiates a new user service impl.
	 *
	 * @param amazonRepositoryService
	 *            the amazon repository service
	 */
	@Autowired(required = false)
	public UserServiceImpl(@Qualifier("ammazonRepositoryArgService") AmazonRepositoryService amazonRepositoryService) {
		this.amazonRepositoryService = amazonRepositoryService;
	}

	/**
	 * Instantiates a new user service impl.
	 */
	public UserServiceImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	public User findUserById(long Id) {
		return userRepository.findById(Id);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveUser(User user) {

		if (user.getCustomerId() < 1) {
			user.setCustomerId(nextCustomerId());
		}
		userRepository.saveUser(user);

	}

	/**
	 * {@inheritDoc}
	 */
	public List<CustomerDto> getCustomers(int start, int end, String sortField, String sortOrder, Map<String, Object> filters, boolean creditCardReqired) {

		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		List<User> userList = userRepository.getCustomers(start, end, sortField, sortOrder, filters);

		if (CommonUtil.isNotNull(userList) && !userList.isEmpty()) {
			for (User user : userList) {
				if (CommonUtil.isNotNull(user.getId())) {
					CustomerDto customerDto = new CustomerDto();
					customerDto.setGeneratedId(user.getId());
					customerDto.setCustomerId(user.getCustomerId());
					customerDto.setFirstName(user.getFirstName());
					customerDto.setLastName(user.getLastName());
					customerDto.setEmail(user.getEmail());
					customerDto.setPhoneNumber(user.getPhoneNumber());
					if (creditCardReqired) {
						createCustomerViewDetails(user, customerDto, customerDtoList);
					}else {
						customerDtoList.add(customerDto);
					}
					
				}
			}
		}
		return customerDtoList;
	}

	/**
	 * Creates the customer view details.
	 *
	 * @param user
	 *            the user
	 * @param customerDto
	 *            void
	 * @param customerDtoList
	 *            the customer dto list
	 */
	private void createCustomerViewDetails(User user, CustomerDto customerDto, List<CustomerDto> customerDtoList) {

		List<CreditCard> creditCardList = cardRepository.findByCardHolderId(user.getId());

		if (!creditCardList.isEmpty()) {
			customerDto.setCreditCardList(creditCardList);
			customerDtoList.add(customerDto);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public long getCustomersCount(Map<String, Object> filters) {
		return userRepository.getCustomersCount(filters);
	}

	/**
	 * {@inheritDoc}
	 */
	public long nextCustomerId() {

		long customerId = 0;

		User user = userRepository.findLastCustomer();
		if (CommonUtil.isNotNull(user)) {
			customerId = user.getCustomerId() + 1;
		} else {
			customerId = CUSTOMER_DEFAULT_ID;
		}
		return customerId;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> getCustomers() {

		List<User> userList = userRepository.getCustomers();

		return userList;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeCustomer(long customerId) {

		User user = userRepository.findById(customerId);

		if (CommonUtil.isNotNull(user)) {

			List<CreditCard> creditCardList = cardRepository.findByCardHolderId(customerId);

			if (!creditCardList.isEmpty()) {

				for (CreditCard creditCard : creditCardList) {
					// Delete customer transaction first
					if (CommonUtil.isNotNull(creditCard)) {
						List<Transaction> transactionList = transactionRepository.findByCreditCardId(creditCard.getId());

						if (!transactionList.isEmpty()) {
							for (Transaction transaction : transactionList) {
								transactionRepository.delete(transaction);
							}

						}
						// Delete customer credit card second
						cardRepository.delete(creditCard);
					}
				}
			}

			Address address = addressRepository.findByUserId(customerId);

			if (CommonUtil.isNotNull(address)) {
				// Delete customer address
				addressRepository.delete(address);
			}
			// At last delete user
			userRepository.delete(user);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public String uploadProfilePicture(long customerId, File file) {
		String fileExtension = FilenameUtils.getExtension(file.getName());
		String filePath = AMAZON_S3_FILE_UPLOAD_PATH + AMAZON_S3_FILE_UPLOAD_PROFILE_PIC_PATH;
		filePath = filePath.replace(FILE_PATH_PARAMETER_USERID, String.valueOf(customerId));
		filePath = filePath + FORWARD_SLASH + PROFILE_KEY + new Date().getTime() + DOT + fileExtension;

		amazonRepositoryService.uploadFileToAmazonS3Server(file, filePath);
		return filePath;
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
	public void deleteFileFromStore(String key) {
		amazonRepositoryService.deleteFile(key);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean checkDuplicateEmailAddress(User user) {
		String oldEmail = BLANK;
		if (CommonUtil.isNotNull(user.getId())) {
			User oldUser = this.findUserById(user.getId());
			oldEmail = oldUser.getEmail();
		}
		if (CommonUtil.isNotNull(user.getEmail()) && !user.getEmail().equalsIgnoreCase(oldEmail)) {
			User newUsers = this.findUserByEmail(user.getEmail());
			if (CommonUtil.isNotNull(newUsers)) {
				return TRUE;
			}
		}
		return FALSE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateCustomerCreditCardFlag(long customerId, boolean hasCreditCard) {
		User user = this.findUserById(customerId);
		if (CommonUtil.isNotNull(user)) {
			user.setHasCreditCard(hasCreditCard);
			this.saveUser(user);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String generateSecuredPassword(String password) {
		if (CommonUtil.isNull(password)) {
			password = BLANK;
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
}
