package com.aimdek.ccm.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aimdek.ccm.document.CreditCard;

/**
 * The Class CreditCardValidator implement spring validator interface and validate customer credit card.
 *
 * @author aimdek.team
 */
@Component
public class CreditCardValidator implements Validator {

	/**
	 * Supports.
	 *
	 * @param clazz the clazz
	 * @return true, if successful
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// just validate the CreditCard instances
		return CreditCard.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CCMConstant.FIELDCONSTANT_CARDHOLDERID, CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_ADD_CARD__CUSTOMER_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CCMConstant.FIELDCONSTANT_CARDNUMBER, CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_CARDNUMBER_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CCMConstant.FIELDCONSTANT_NAME_ON_CARD, CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_NAME_MESSAGE_KEY);

		CreditCard creditCard = (CreditCard) target;
		String cvv = String.valueOf(creditCard.getCvv());
		if (creditCard.getCvv() == 0) {
			errors.rejectValue(CCMConstant.FIELDCONSTANT_CVV, CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_CVV_MESSAGE_KEY);
		} else if (cvv.length() != 3) {
			errors.rejectValue(CCMConstant.FIELDCONSTANT_CVV, CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_CVV__LENGTH_MESSAGE_KEY);
		} 
		if (CommonUtil.isNull(creditCard.getValidFromDate())) {
			errors.rejectValue(CCMConstant.FIELDCONSTANT_VALID_FROM_DATE, CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_VALIDFROM_MESSAGE_KEY);
		}
		if (CommonUtil.isNull(creditCard.getValidToDate())) {
			errors.rejectValue(CCMConstant.FIELDCONSTANT_VALID_TO_DATE, CCMConstant.ADD_CUSTOMER_CREDITCARD_DETAIL_VALIDTO_MESSAGE_KEY);
		}
	}
}