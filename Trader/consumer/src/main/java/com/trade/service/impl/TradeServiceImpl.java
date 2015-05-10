package com.trade.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trade.consumer.api.TradeRequest;
import com.trade.domain.dao.TradeDao;
import com.trade.domain.entity.Trade;
import com.trade.service.CountryReport;
import com.trade.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	TradeDao tradeDao;//package local to allow for testing.
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long stageTrade(TradeRequest request) {
		Trade trade = new Trade();
		try {
			BeanUtils.copyProperties(trade, request);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
		tradeDao.stageTrade(trade);
		
		return trade.getTradeId();

	}


	@Override
	@Async(value = "reportExecutor")
	public Future<Map<String, CountryReport>> generateByCountryReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
