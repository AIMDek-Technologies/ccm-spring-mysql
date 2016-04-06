/**
 * 
 */
package com.aimdek.ccm.util;

import static com.aimdek.ccm.util.CCMConstant.DATE_FORMAT;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * The Class CustomDateDeserializer.
 *
 * @author aimdek.team
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CustomDateDeserializer.class);

	/**
	 * Deserialize.
	 *
	 * @param jp the jp
	 * @param ctxt the ctxt
	 * @return the date
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JsonProcessingException the json processing exception
	 */
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		try {
			return formatter.parse(jp.getText().toString());
		} catch (ParseException e) {
			LOGGER.error("Error while deserialize staring date.", e);
		}
		return new Date();
	}

}
