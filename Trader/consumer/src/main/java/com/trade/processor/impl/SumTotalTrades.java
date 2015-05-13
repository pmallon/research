package com.trade.processor.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;







import com.trade.consumer.api.TradeRequest;
import com.trade.processor.TradeHandler;


/**
 * Sample where we can chain the handlers.
 * @author philip.mallon
 *
 */
@Service()
@Qualifier(value ="tradeHadler1")
public class SumTotalTrades implements TradeHandler{
	
	
	@Autowired(required = false)
	@Qualifier("tradeHadler2")
	TradeHandler tradeHandler;

	@Override
	public void processTrade(TradeRequest request) {
		
		
	}

}
