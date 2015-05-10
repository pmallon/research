package com.trade.consumer.api.format;

import java.io.IOException;
import java.util.Currency;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonCurrencyDeserializer extends JsonDeserializer<Currency> {

	@Override
	public Currency deserialize(JsonParser jsonparser,
			DeserializationContext deserializationcontext) throws IOException,
			JsonProcessingException {

		String currencyCode = jsonparser.getText();
		try{
			return  Currency.getInstance(currencyCode);
		}catch(IllegalArgumentException e){
			//Currency Code Invalid
			return null;
		}
		

	}

}