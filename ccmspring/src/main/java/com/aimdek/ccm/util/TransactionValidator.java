package com.aimdek.ccm.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aimdek.ccm.document.Transaction;

/**
 * The Class TransactionValidator.
 *
 * @author aimdek.team
 */
@Component
public class TransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// just validate the Transactions instances
		return Transaction.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CCMConstant.FIELDCONSTANT_CUSTOMERNAME, CCMConstant.ADD_TRANSACTION_SELECT_CUSTOMER_VALIDATION_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CCMConstant.FIELD_CONSTANT_CREDIT_CARD_ID, CCMConstant.ADD_TRANSACTION_SELECT_CARD_VALIDATION_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CCMConstant.FIELD_CONSTANT_AMOUNT, CCMConstant.ADD_TRANSACTION_AMOUNT_VALIDATION_MESSAGE_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CCMConstant.FIELD_CONSTANT_TRANSACTION_DATE, CCMConstant.ADD_TRANSACTION_DATE_VALIDATION_MESSAGE_KEY);
		Transaction transaction = (Transaction) target;

		if (transaction.getAmount() < 1) {
			errors.rejectValue(CCMConstant.FIELD_CONSTANT_AMOUNT, CCMConstant.ADD_TRANSACTION_AMOUNT_VALIDATION_MESSAGE_KEY);
		}

	}
}