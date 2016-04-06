package com.aimdek.ccm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.User;
import com.aimdek.ccm.dto.CustomerDataDTO;
import com.aimdek.ccm.service.UserService;

/**
 * The Class CustomerValidator.
 *
 * @author aimdek.team
 */
@Component
public class CustomerValidator implements Validator {
	
	/** The user service. */
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		// just validate the Customer instances
		return CustomerDataDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.firstName", CCMConstant.ADD_CUSTOMER_BASIC_DETAIL_FIRSTNAME_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.lastName", CCMConstant.ADD_CUSTOMER_BASIC_DETAIL_LASTNAME_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.email", CCMConstant.ADD_CUSTOMER_BASIC_DETAIL_EMAIL_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.phoneNumber", CCMConstant.ADD_CUSTOMER_BASIC_DETAIL_PHONE_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.houseName", CCMConstant.ADD_CUSTOMER_ADDRESS_DETAIL_HOUSENUMBER_MESSAGE_KEY);

		CustomerDataDTO customerDataDTO = (CustomerDataDTO) target;
		User user = customerDataDTO.getUser();
		CreditCard creditCard = ((CustomerDataDTO) target).getCreditCard();
		String cvv = String.valueOf(creditCard.getCvv());

		if (CommonUtil.isNotNull(user.getEmail()) && !CommonUtil.checkEmailPattern(user.getEmail())) {
			errors.rejectValue("user.email", CCMConstant.ADD_CUSTOMER_BASIC_DETAIL_EMAIL_INVALID_MESSAGE_KEY);
		}
		if (CommonUtil.isNotNull(user.getEmail())) {
			boolean isEmailDuplicate = userService.checkDuplicateEmailAddress(user);
			if (isEmailDuplicate) {
				errors.rejectValue("user.email", CCMConstant.ADD_CUSTOMER_BASIC_DETAIL_DUPLICATE_EMAIL_MESSAGE_KEY);
			}
		}
		if (CommonUtil.isNull(user.getBirthDate())) {
			errors.rejectValue("user.birthDate", CCMConstant.ADD_CUSTOMER_BASIC_DETAIL_BIRTHDATE_MESSAGE_KEY);
		}
		if (CommonUtil.isNull(customerDataDTO.getZipCode())) {
			errors.rejectValue("zipCode", CCMConstant.ADD_CUSTOMER_GEOGRAPHIC_DETAIL_MESSAGE_KEY);
		} else if (customerDataDTO.getZipCode().length() != 6) {
			errors.rejectValue("zipCode", CCMConstant.ADD_CUSTOMER_GEOGRAPHIC_DETAIL_ZIP_LENGTH_MESSAGE_KEY);
		}

		validateCreditCard(errors, user, creditCard, cvv);
	}

	/**
	 * @param errors
	 * @param user
	 * @param creditCard
	 * @param cvv
	 */
	private void validateCreditCard(Errors errors, User user, CreditCard creditCard, String cvv) {
		if (user.getCustomerId() == 0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard.cardNumber", CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_CARDNUMBER_MESSAGE_KEY);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard.nameOnCard", CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_NAME_MESSAGE_KEY);

			if (creditCard.getValidFromDate() == null) {
				errors.rejectValue("creditCard.validFromDate", CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_VALIDFROM_MESSAGE_KEY);
			}
			if (creditCard.getValidToDate() == null) {
				errors.rejectValue("creditCard.validToDate", CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_VALIDTO_MESSAGE_KEY);
			}
			if (creditCard.getCvv() == 0) {
				errors.rejectValue("creditCard.cvv", CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_CVV_MESSAGE_KEY);
			} else if (creditCard.getCvv() != 0 && cvv.length() != 3) {
				errors.rejectValue("creditCard.cvv", CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_CVV__LENGTH_MESSAGE_KEY);
			}
		}
	}
}