package com.trade.processor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.trade.consumer.api.TradeRequest;
import com.trade.processor.TradeHandler;
import com.trade.service.TradeService;

@Service()
@Qualifier(value ="tradeHadler")
public class TradeHandlerImpl implements TradeHandler {
	
	@Autowired
	TradeService service;
	
	@Autowired(required = false)
	@Qualifier("tradeHadler1")
	TradeHandler tradeHandler;//Link to a possible next handler to allow additional processes to be added later
	
	
	@Override
	@Async(value = "processTradeExecutor")
	public void processTrade(TradeRequest request) {
		service.stageTrade(request);		
		if(tradeHandler !=null){
			tradeHandler.processTrade(request);
		}
	}

}
