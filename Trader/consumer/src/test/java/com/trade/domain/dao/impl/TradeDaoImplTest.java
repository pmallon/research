package com.trade.domain.dao.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

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
import com.trade.service.CountryReport;

@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)//each test runs against a clean DB
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoTestConfig.class})
public class TradeDaoImplTest {
	
	@Autowired
	TradeDao tradeDao;
	
	
	private Trade createTrade(String countryCode) {
		Trade trade = new Trade();
		trade.setAmountBuy(BigDecimal.TEN);
		trade.setAmountSell(BigDecimal.TEN);
		trade.setCurrencyFrom(Currency.getInstance("EUR"));
		trade.setCurrencyTo(Currency.getInstance("EUR"));
		trade.setOriginatingCountry(countryCode);
		trade.setRate(BigDecimal.TEN);
		trade.setTimePlaced(LocalDateTime.now());
		trade.setUserId(23l);		
		tradeDao.stageTrade(trade);
		return trade;
	}
	
	@Test
	public void testStageTradeAndFetch(){
		Trade trade = createTrade("FR");		
		
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
	
	@Test
	public void getTotalsOneEntry(){
		
		createTrade("FR");
		
		List<CountryReport>	countries = tradeDao.getTotals();
		
		Assert.assertNotNull(countries);
		Assert.assertEquals(1,countries.size());
		Assert.assertTrue(BigDecimal.TEN.compareTo(countries.get(0).getTotalAmountBought())==0);
		Assert.assertTrue(BigDecimal.TEN.compareTo(countries.get(0).getTotalAmountSold())==0);
		Assert.assertEquals(1l, countries.get(0).getTradeCount());
		Assert.assertEquals("FR", countries.get(0).getCountry());
		
	}
	
	@Test
	public void getTotalsMutipleEntries(){
		
		createTrade("FR");		
		createTrade("FR");
		createTrade("IE");
		createTrade("IE");
		createTrade("FR");
		createTrade("IR");
		
		CountryReport frReport = new CountryReport("FR",new BigDecimal(30),new BigDecimal(30),3);
		CountryReport ieReport = new CountryReport("IE",new BigDecimal(20),new BigDecimal(20),2);
		CountryReport irReport = new CountryReport("IR",new BigDecimal(10),new BigDecimal(10),1);
		
		List<CountryReport>	countries = tradeDao.getTotals();
		
		Assert.assertNotNull(countries);
		Assert.assertEquals(3,countries.size());
		Assert.assertTrue(countries.contains(frReport));
		Assert.assertTrue(countries.contains(ieReport));
		Assert.assertTrue(countries.contains(irReport));	
		
		
	}

}
