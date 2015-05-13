package com.trade.domain.dao.impl;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.trade.domain.dao.TradeDao;
import com.trade.domain.entity.Trade;
import com.trade.service.CountryReport;
import com.trade.service.TamperException;

@Repository
public class TradeDaoImpl implements TradeDao {

	/**
	 * Using SHA-512 to ensure DB integrity ,will impact performance , depending
	 * on how 'secure' access to the DB system this can be removed or use a more
	 * secure hardware encryption.Balance between security risk and performance
	 */
	private static final String HASH_ALOGIRTHM = "SHA-512";

	@Autowired
	private SessionFactory sessionFactory;

	@Value(value = "salt")
	private String salt;

	@Override
	public Trade findByTradeId(long tradeId) {
		Trade trade = (Trade) sessionFactory.getCurrentSession().get(
				Trade.class, tradeId);
		if (trade != null) {
			validateCheckSum(trade);
		}
		return trade;
	}

	@Override
	public Trade stageTrade(Trade trade) {
		if (trade != null && trade.getTradeId() == null) {// ID is set by DB
			updateCheckSum(trade);
			sessionFactory.getCurrentSession().persist(trade);
			return trade;
		} else {
			// This is a code error.
			throw new IllegalArgumentException(
					"Trade must be not be null and ID nust not be initialised");
		}

	}

	void updateCheckSum(Trade trade) {
		try {
			MessageDigest md = MessageDigest.getInstance(HASH_ALOGIRTHM);
			trade.setCheckSum(md.digest((salt + trade.checkSumString())
					.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// Configuration issue
			throw new RuntimeException(e);
		}
	}	

	void validateCheckSum(Trade trade) {
		try {
			MessageDigest md = MessageDigest.getInstance(HASH_ALOGIRTHM);
			byte[] checkSum = md.digest((salt + trade.checkSumString())
					.getBytes());
			if (!Arrays.equals(checkSum, trade.getCheckSum())) {
				throw new TamperException("Trade data ID:"
						+ trade.getTradeId().toString()
						+ " has been modified directly");
			}
		} catch (NoSuchAlgorithmException e) {
			// Configuration issue
			throw new RuntimeException(e);
		}

	}

	public List<CountryReport> getTotals() {
		
		return sessionFactory
				.getCurrentSession()
				.createCriteria(Trade.class)
				.setProjection(	Projections.projectionList()
								.add(Projections.groupProperty("originatingCountry"))
								.add(Projections.property("originatingCountry").as("country"))
								.add(Projections.sum("amountSell").as("totalAmountSold"))
								.add(Projections.sum("amountBuy").as("totalAmountBought"))
								.add(Projections.count("amountBuy").as("tradeCount")))
								.setResultTransformer(new AliasToBeanResultTransformer(CountryReport.class))
								.list();		

	}

}
