package com.trade.processor.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;







import com.trade.consumer.api.TradeRequest;
import com.trade.processor.TradeHandler;

@Service()
@Qualifier(value ="tradeHadler1")
public class SumTotalTrades implements TradeHandler{
	
	private Map<String,BigDecimal> totalAmountBuyCountry = new HashMap<String, BigDecimal>();
	
	@Autowired(required = false)
	@Qualifier("tradeHadler2")
	TradeHandler tradeHandler;

	@Override
	public void processTrade(TradeRequest request) {
		BigDecimal total = totalAmountBuyCountry.get(request.getOriginatingCountry());
		if(total==null){
			total = BigDecimal.ZERO;
		}
		
		total = total.add(request.getAmountBuy());
		
		totalAmountBuyCountry.put(request.getOriginatingCountry(), total);
		
		System.out.println(total.toString());
		// TODO Auto-generated method stub
		
	}

}
