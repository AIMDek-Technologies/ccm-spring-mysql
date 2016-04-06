/**
 * 
 */
package com.aimdek.ccm.util;

import static com.aimdek.ccm.util.CCMConstant.DATE_FORMAT;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * The Class CustomDateSerializer.
 *
 * @author aimdek.team
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

   /**
    * Serialize.
    *
    * @param value the value
    * @param gen the gen
    * @param arg2 the arg2
    * @throws IOException Signals that an I/O exception has occurred.
    * @throws JsonProcessingException the json processing exception
    */
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String formattedDate = formatter.format(value);
        gen.writeString(formattedDate);
    }
}
