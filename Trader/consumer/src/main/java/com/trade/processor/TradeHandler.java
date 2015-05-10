package com.trade.processor;

import com.trade.consumer.api.TradeRequest;

/**
 * Responsible for processing the request.
 * Assumption: Any technical failures ( DB etc) can be reported later.If client needs real time 
 * non business error handling staging must done within controller.
 * @author Phil
 *
 */
public interface TradeHandler {

	public void processTrade(TradeRequest request);

}
