package com.trade.consumer.api.format;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.mockito.Mockito;
import org.junit.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CustomJsonDateDeserializerTest {

	private JsonDateDeserializer deserializer;

	private static final DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("dd-MMM-uu HH:mm:ss");

	@Before
	public void setup() {
		deserializer = new JsonDateDeserializer();
	}

	@Test
	public void testSampleDate() throws JsonProcessingException, IOException {

		JsonParser parser = Mockito.mock(JsonParser.class);

		Mockito.when(parser.getText()).thenReturn("24-JAN-15 10:27:44");

		LocalDateTime date = deserializer.deserialize(parser, null);

		Assert.assertNotNull(date);
		Assert.assertEquals(2015, date.getYear());
		Assert.assertEquals(1, date.getMonthValue());
		Assert.assertEquals(24, date.getDayOfMonth());
		Assert.assertEquals(10, date.getHour());
		Assert.assertEquals(27, date.getMinute());
		Assert.assertEquals(44, date.getSecond());

	}

	@Test
	public void test24HourDate() throws JsonProcessingException, IOException {

		JsonParser parser = Mockito.mock(JsonParser.class);

		Mockito.when(parser.getText()).thenReturn("24-JAN-15 18:27:44");

		LocalDateTime date = deserializer.deserialize(parser, null);

		Assert.assertNotNull(date);
		Assert.assertEquals(2015, date.getYear());
		Assert.assertEquals(1, date.getMonthValue());
		Assert.assertEquals(24, date.getDayOfMonth());
		Assert.assertEquals(18, date.getHour());
		Assert.assertEquals(27, date.getMinute());
		Assert.assertEquals(44, date.getSecond());

	}

	@Test
	public void testInvalidDate() throws JsonProcessingException, IOException {

		JsonParser parser = Mockito.mock(JsonParser.class);

		Mockito.when(parser.getText()).thenReturn("24-01-15 18:27:44");

		LocalDateTime date = deserializer.deserialize(parser, null);
		Assert.assertNull(date);

	}

}
