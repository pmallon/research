package com.trade.domain.dao;

import java.util.List;

import com.trade.domain.entity.Trade;
import com.trade.service.CountryReport;

public interface TradeDao {

	Trade findByTradeId(long tradeId);

	void stageTrade(Trade trade);

	public List<CountryReport> getTotals();

}
