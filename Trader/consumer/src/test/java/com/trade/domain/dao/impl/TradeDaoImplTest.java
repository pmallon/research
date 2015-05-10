package com.trade.domain.dao.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.trade.domain.dao.DaoTestConfig;
import com.trade.domain.dao.TradeDao;
import com.trade.domain.entity.Trade;

@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)//each test runs against a clean DB
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoTestConfig.class})
public class TradeDaoImplTest {
	
	@Autowired
	TradeDao tradeDao;
	
	
	@Test
	public void testStageTradeAndFetch(){
		Trade trade = new Trade();
		trade.setAmountBuy(BigDecimal.TEN);
		trade.setAmountSell(BigDecimal.TEN);
		trade.setCurrencyFrom(Currency.getInstance("EUR"));
		trade.setCurrencyTo(Currency.getInstance("EUR"));
		trade.setOriginatingCountry("FR");
		trade.setRate(BigDecimal.TEN);
		trade.setTimePlaced(LocalDateTime.now());
		trade.setUserId(23l);		
		tradeDao.stageTrade(trade);		
		
		Assert.assertNotNull(trade.getTradeId());
		Assert.assertNotNull(trade.getCheckSum());
		Trade fetchedTrade = tradeDao.findByTradeId(trade.getTradeId());
		
		Assert.assertNotNull(fetchedTrade);	
		
	}
	
	@Test
	public void testStageTradeIdSet(){
		Trade trade = new Trade();
		trade.setAmountBuy(BigDecimal.TEN);
		trade.setAmountSell(BigDecimal.TEN);
		trade.setCurrencyFrom(Currency.getInstance("EUR"));
		trade.setCurrencyTo(Currency.getInstance("EUR"));
		trade.setOriginatingCountry("FR");
		trade.setRate(BigDecimal.TEN);
		trade.setTimePlaced(LocalDateTime.now());
		trade.setUserId(23l);
		trade.setTradeId(45l);
		try{
			tradeDao.stageTrade(trade);	
			Assert.fail("IllegalArgumentException not thrown");
		}catch(IllegalArgumentException e){
			
		}	
		
	}

}
