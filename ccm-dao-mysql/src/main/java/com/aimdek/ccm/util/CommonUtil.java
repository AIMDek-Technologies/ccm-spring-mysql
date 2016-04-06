/*
 * Copyright (c) 2014-2015 AIMDek Technologies Private Limited. All Rights Reserved.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     AIMDek Technologies Private Limited - initial API and implementation
 */

package com.aimdek.ccm.util;

import static com.aimdek.ccm.util.CCMConstant.BLANK;
import static com.aimdek.ccm.util.CCMConstant.DATE_FORMAT;
import static com.aimdek.ccm.util.CCMConstant.EMAIL_PATTERN;
import static com.aimdek.ccm.util.CCMConstant.LOWER_CASE_L;
import static com.aimdek.ccm.util.CCMConstant.LOWER_CASE_N;
import static com.aimdek.ccm.util.CCMConstant.LOWER_CASE_U;
import static com.aimdek.ccm.util.CCMConstant.SPACE;
import static com.aimdek.ccm.util.CCMConstant.TWO_DECIMAL_PLACE_FORMAT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The Class CommonUtil.
 * 
 * This class marked final to restrict instantiation.
 * 
 * Because it contains the utility method of the system.
 * 
 * @author aimdek.team
 */
public final class CommonUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

	/**
	 * Instantiates a new common util.
	 */
	private CommonUtil() {

	}

	/** The Constant gson. */
	public static final Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

	/**
	 * Checks if is null.
	 *
	 * @param l
	 *            the l
	 * @return true, if is null
	 */
	public static boolean isNull(Long l) {
		if ((l == null) || (l.longValue() == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is null.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if is null
	 */
	public static boolean isNull(Object obj) {
		if (obj instanceof Long) {
			return isNull((Long) obj);
		} else if (obj instanceof String) {
			return isNull((String) obj);
		} else if (obj == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is null.
	 *
	 * @param array
	 *            the array
	 * @return true, if is null
	 */
	public static boolean isNull(Object[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is null.
	 *
	 * @param s
	 *            the s
	 * @return true, if is null
	 */
	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}

		int counter = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SPACE) {
				continue;
			} else if (counter > 3) {
				return false;
			}
			if (counter == 0) {
				if (c != LOWER_CASE_N) {
					return false;
				}
			} else if (counter == 1) {
				if (c != LOWER_CASE_U) {
					return false;
				}
			} else if ((counter == 2) || (counter == 3)) {
				if (c != LOWER_CASE_L) {
					return false;
				}
			}
			counter++;
		}

		if ((counter == 0) || (counter == 4)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is not null.
	 *
	 * @param s
	 *            the s
	 * @return true, if is not null
	 */
	public static boolean isNotNull(String s) {
		return !isNull(s);
	}

	/**
	 * Checks if is not null.
	 *
	 * @param l
	 *            the l
	 * @return true, if is not null
	 */
	public static boolean isNotNull(Long l) {
		return !isNull(l);
	}

	/**
	 * Checks if is not null.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if is not null
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	/**
	 * Checks if is not null.
	 *
	 * @param array
	 *            the array
	 * @return true, if is not null
	 */
	public static boolean isNotNull(Object[] array) {
		return !isNull(array);
	}

	/**
	 * Gets the property value.
	 *
	 * @param fileName
	 *            the file name
	 * @param propertyName
	 *            the property name
	 * @return the property value
	 */
	public static String getPropertyValue(String fileName, String propertyName) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		String value = BLANK;

		try {
			inputStream = new FileInputStream(fileName);
			properties.load(inputStream);
			value = (String) properties.get(propertyName);
		} catch (FileNotFoundException e) {
			LOGGER.error("Property file not found", e);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				LOGGER.error("Error while closing FileInputstream", e);
			}
		}

		return value;
	}

	/**
	 * Check email pattern.
	 *
	 * @param email
	 *            the email
	 * @return true, if successful
	 */
	public static boolean checkEmailPattern(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		boolean matchFound = matcher.matches();
		return matchFound;
	}

	/**
	 * Gets the formated number.
	 *
	 * @param val
	 *            the val
	 * @return the formated number
	 */
	public static double getFormatedNumber(double val) {
		String stringVal = getNumberWithTwoDecimalPlace(val);
		double newVal = Double.parseDouble(stringVal);
		return newVal;
	}

	/**
	 * Gets the number with two decimal place.
	 *
	 * @param number
	 *            the number
	 * @return the number with two decimal place
	 */
	public static String getNumberWithTwoDecimalPlace(double number) {
		DecimalFormat decimalFormat = new DecimalFormat(TWO_DECIMAL_PLACE_FORMAT);
		return decimalFormat.format(number);
	}

	/**
	 * Gets the formatted date.
	 *
	 * @param originalDate
	 *            the original date
	 * @return the formatted date
	 */
	public static String getFormattedDate(Date originalDate) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

		return dateFormat.format(originalDate);
	}

	/**
	 * Gets the resource full file path.
	 *
	 * @param anyClass
	 *            the any class
	 * @param filePath
	 *            the file path
	 * @return the resource full file path
	 */
	public static String getResourceFullFilePath(Class<?> anyClass, String filePath) {

		return anyClass.getResource(filePath).getPath();

	}

	/**
	 * Generate random alphanumeric.
	 *
	 * @return the string
	 */
	public static String generateRandomAlphanumeric() {
		final String securedPwd = RandomStringUtils.randomAlphanumeric(6);
		return securedPwd;
	}

	/**
	 * Gets the gson object.
	 *
	 * @return the gson object
	 */
	public static Gson getGsonObject() {
		return gson;
	}

	/**
	 * To day.
	 *
	 * @return the date
	 */
	public static Date toDay() {
		return new Date();
	}
}
