package com.trade.consumer.api.format;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Format Date to using defined format dd-MMM-yy HH:mm:ss 24-JAN-15 10:27:44
 * 
 * @author Phil
 *
 */
public class JsonDateDeserializer extends JsonDeserializer<LocalDateTime> {

	private static final DateTimeFormatter formatter;
	static {
		formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
				.appendPattern("dd-MMM-uu HH:mm:ss").toFormatter();
	}

	@Override
	public LocalDateTime deserialize(JsonParser jsonparser,
			DeserializationContext deserializationcontext) throws IOException,
			JsonProcessingException {

		String date = jsonparser.getText();
		try {
			return LocalDateTime.parse(date, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}

	}

}