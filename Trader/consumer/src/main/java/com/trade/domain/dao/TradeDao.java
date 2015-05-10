package com.trade.domain.dao;

import com.trade.domain.entity.Trade;

public interface TradeDao {

	Trade findByTradeId(long tradeId);

	void stageTrade(Trade trade);

	void updateTrade(Trade trade);

}
