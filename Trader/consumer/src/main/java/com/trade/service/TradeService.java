package com.trade.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.trade.consumer.api.TradeRequest;

/**
 * Handles all data
 * 
 * @author Phil
 *
 */
public interface TradeService {

	long stageTrade(TradeRequest request);

	Future<List<CountryReport>> generateByCountryReport();

}
