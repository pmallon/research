package com.trade.consumer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.trade.consumer.api.StatusCode;
import com.trade.consumer.api.TradeRequest;
import com.trade.consumer.api.TradeSubmitResponse;
import com.trade.processor.TradeHandler;

public class TraderControllerTest {

	private TraderController controller;
	private TradeHandler tradeHandler;
	
	@Before
	public void setup(){		
		tradeHandler= Mockito.mock(TradeHandler.class);
		controller = new TraderController();
		
		controller.tradeHandler = tradeHandler;		
	}
	
	
	@Test
	public void testValidRequest(){
		TradeRequest request = new TradeRequest();
		request.setAmountBuy(BigDecimal.TEN);
		request.setAmountSell(BigDecimal.TEN);
		request.setCurrencyFrom(Currency.getInstance("EUR"));
		request.setCurrencyTo(Currency.getInstance("USD"));
		request.setOriginatingCountry("FR");
		request.setRate(new BigDecimal(22.30));
		request.setUserId(23l);
		request.setTimePlaced(LocalDateTime.now());
		
		TradeSubmitResponse response = controller.submitTrade(request);		
		Mockito.verify(tradeHandler).processTrade(Mockito.eq(request));
		
		Assert.assertEquals(StatusCode.COMPLETE, response.getStatus());
		
	}
	
	
	@Test
	public void testValidRequestExceptionThrown(){
		TradeRequest request = new TradeRequest();
		request.setAmountBuy(BigDecimal.TEN);
		request.setAmountSell(BigDecimal.TEN);
		request.setCurrencyFrom(Currency.getInstance("EUR"));
		request.setCurrencyTo(Currency.getInstance("USD"));
		request.setOriginatingCountry("FR");
		request.setRate(new BigDecimal(22.30));
		request.setUserId(23l);
		request.setTimePlaced(LocalDateTime.now());
		
		Mockito.doThrow(new RuntimeException()).when(tradeHandler).processTrade(Mockito.eq(request));
				
		TradeSubmitResponse response = controller.submitTrade(request);		
		
		
		Assert.assertEquals(StatusCode.SYSTEM_ERROR, response.getStatus());
		
	}
	
	@Test
	public void testInValidRequestCountry(){
		TradeRequest request = new TradeRequest();
		request.setAmountBuy(BigDecimal.TEN);
		request.setAmountSell(BigDecimal.TEN);
		request.setCurrencyFrom(Currency.getInstance("EUR"));
		request.setCurrencyTo(Currency.getInstance("USD"));
		request.setOriginatingCountry("FP");
		request.setRate(new BigDecimal(22.30));
		request.setUserId(23l);
		request.setTimePlaced(LocalDateTime.now());
		
		TradeSubmitResponse response = controller.submitTrade(request);		
		Mockito.verifyZeroInteractions(tradeHandler);
		
		Assert.assertEquals(StatusCode.INVALID_REQUEST, response.getStatus());
		Assert.assertEquals("Country code not valid", response.getDescription());
		
		
	}
	
	
	
	@Test
	public void testValidBuyAmount(){
		TradeRequest request = new TradeRequest();
		
		request.setAmountSell(BigDecimal.TEN);
		request.setCurrencyFrom(Currency.getInstance("EUR"));
		request.setCurrencyTo(Currency.getInstance("USD"));
		request.setOriginatingCountry("FR");
		request.setRate(new BigDecimal(22.30));
		request.setUserId(23l);
		request.setTimePlaced(LocalDateTime.now());
		
		TradeSubmitResponse response = controller.submitTrade(request);		
		Mockito.verifyZeroInteractions(tradeHandler);
		
		Assert.assertEquals(StatusCode.INVALID_REQUEST, response.getStatus());
		Assert.assertEquals("amountBuy - may not be null|", response.getDescription());
		
	}
	
	@Test
	public void testValidRequestNoTime(){
		TradeRequest request = new TradeRequest();
		request.setAmountBuy(BigDecimal.TEN);
		request.setAmountSell(BigDecimal.TEN);
		request.setCurrencyFrom(Currency.getInstance("EUR"));
		request.setCurrencyTo(Currency.getInstance("USD"));
		request.setOriginatingCountry("FR");
		request.setRate(new BigDecimal(22.30));
		request.setUserId(23l);		
		
		TradeSubmitResponse response = controller.submitTrade(request);		
		Mockito.verifyZeroInteractions(tradeHandler);
		
		Assert.assertEquals(StatusCode.INVALID_REQUEST, response.getStatus());
		Assert.assertEquals("timePlaced - timePlaced must not be null and must comply with Date format dd-MMM-uu HH:mm:ss.|", response.getDescription());	
	}
	
	//ADD Test cases to cover other validations
}
