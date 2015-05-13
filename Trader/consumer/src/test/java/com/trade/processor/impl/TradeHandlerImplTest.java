package com.trade.processor.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.trade.consumer.api.TradeRequest;
import com.trade.processor.TradeHandler;
import com.trade.service.TradeService;

public class TradeHandlerImplTest {
	
	private TradeHandlerImpl handlerImpl;

	private TradeService service;
	
	private TradeRequest request;
	
	@Before()
	public void setup(){
		
		handlerImpl = new TradeHandlerImpl();
		service = Mockito.mock(TradeService.class);
		
		handlerImpl.service = service;
		
		request = new TradeRequest();
		request.setAmountBuy(BigDecimal.TEN);
		request.setAmountSell(BigDecimal.TEN);
		request.setCurrencyFrom(Currency.getInstance("EUR"));
		request.setCurrencyTo(Currency.getInstance("USD"));
		request.setOriginatingCountry("FR");
		request.setRate(new BigDecimal(22.30));
		request.setUserId(23l);
		request.setTimePlaced(LocalDateTime.now());
	}
	
	/**
	 * service.stageTrade(request);		
		if(tradeHandler !=null){
			tradeHandler.processTrade(request);
		}
	 */
	@Test
	public void testProcessWithNoNextLink(){
		
		handlerImpl.processTrade(request);
		
		Mockito.verify(service).stageTrade(Mockito.eq(request));
		
	}
	
	@Test
	public void testProcessWithNextLink(){
		
		TradeHandler nextHandler = Mockito.mock(TradeHandler.class);
		handlerImpl.tradeHandler = nextHandler;
		
		handlerImpl.processTrade(request);
		
		Mockito.verify(service).stageTrade(Mockito.eq(request));
		Mockito.verify(nextHandler).processTrade(Mockito.eq(request));
		
	}
	

}
