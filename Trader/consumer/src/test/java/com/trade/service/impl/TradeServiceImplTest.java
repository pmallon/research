package com.trade.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.trade.consumer.api.TradeRequest;
import com.trade.domain.dao.TradeDao;
import com.trade.domain.entity.Trade;
import com.trade.service.TradeService;

public class TradeServiceImplTest {
	
	private TradeDao tradeDao;
	private TradeServiceImpl tradeService;
	private Trade stageTrade;
	private TradeRequest request;
	private LocalDateTime now;;
	
	@Before()
	public void setup(){
		tradeDao = Mockito.mock(TradeDao.class);
		now = LocalDateTime.now();
		tradeService = new TradeServiceImpl();
		tradeService.tradeDao = tradeDao;
		
		request = new TradeRequest();
		request.setAmountBuy(BigDecimal.TEN);
		request.setAmountSell(BigDecimal.TEN);
		request.setCurrencyFrom(Currency.getInstance("EUR"));
		request.setCurrencyTo(Currency.getInstance("USD"));
		request.setOriginatingCountry("FR");
		request.setRate(new BigDecimal(22.30));
		request.setUserId(23l);
		request.setTimePlaced(now);
		
		stageTrade = new Trade();
		stageTrade.setAmountBuy(BigDecimal.TEN);
		stageTrade.setAmountSell(BigDecimal.TEN);
		stageTrade.setCurrencyFrom(Currency.getInstance("EUR"));
		stageTrade.setCurrencyTo(Currency.getInstance("USD"));
		stageTrade.setOriginatingCountry("FR");
		stageTrade.setRate(new BigDecimal(22.3));
		stageTrade.setTimePlaced(now);		
		stageTrade.setUserId(23l);
			
		
		
	}
	
	@Test
	public void testStageTrade(){
		
		Trade updatedTrade = new Trade();
		updatedTrade.setAmountBuy(BigDecimal.TEN);
		updatedTrade.setAmountSell(BigDecimal.TEN);
		updatedTrade.setCurrencyFrom(Currency.getInstance("EUR"));
		updatedTrade.setCurrencyTo(Currency.getInstance("USD"));
		updatedTrade.setOriginatingCountry("FR");
		updatedTrade.setRate(new BigDecimal(22.30));
		updatedTrade.setTimePlaced(now);
		updatedTrade.setTradeId(23L);
		updatedTrade.setUserId(23l);
		
		Mockito.when(tradeDao.stageTrade(Mockito.eq(stageTrade))).thenReturn(updatedTrade);
		tradeService.stageTrade(request);		
		
	}

}
