package com.trade.consumer;

import org.junit.Before;
import org.mockito.Mockito;

import com.trade.processor.TradeHandler;

public class TraderControllerTest {
	
	
	private TraderController controller;
	private TradeHandler tradeHandler;
	
	
	@Before
	public void setup(){
		tradeHandler = Mockito.mock(TradeHandler.class);
		controller = new TraderController();
		controller.tradeHandler = tradeHandler;
	}

}
