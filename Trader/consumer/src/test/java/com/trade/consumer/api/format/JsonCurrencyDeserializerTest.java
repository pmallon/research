package com.trade.consumer.api.format;


import java.io.IOException;
import java.util.Currency;

import org.junit.*;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonParser;
public class JsonCurrencyDeserializerTest {
	
	
	private JsonCurrencyDeserializer deserializer;
	
	@Before
	public void setup(){
		deserializer = new JsonCurrencyDeserializer();
	}
	
	
	@Test
	public void testValidCurrency() throws IOException{
		
		JsonParser parser = Mockito.mock(JsonParser.class);

		Mockito.when(parser.getText()).thenReturn("EUR");
		
		Currency currency = deserializer.deserialize(parser, null);
		
		Assert.assertNotNull(currency);
	}
	

	@Test
	public void testInValidCurrency() throws IOException{
		
		JsonParser parser = Mockito.mock(JsonParser.class);

		Mockito.when(parser.getText()).thenReturn("GBR");
		
		Currency currency = deserializer.deserialize(parser, null);
		
		Assert.assertNull(currency);
	}

}
