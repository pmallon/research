package com.trade.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Long tradeId;

	@Column(nullable = false)
	private long userId;

	@Column(nullable = false)
	private Currency currencyFrom;

	@Column(nullable = false)
	private Currency currencyTo;

	@Column(nullable = false)
	private BigDecimal amountSell;

	@Column(nullable = false)
	private BigDecimal amountBuy;

	@Column(nullable = false)
	private BigDecimal rate;

	@Column(nullable = false)
	private LocalDateTime timePlaced;

	@Column(nullable = false)
	private String originatingCountry;

	@Lob
	@Column(nullable = false)
	private byte[] checkSum;

	@Column(nullable = false)
	private boolean processed;

	public String checkSumString() {
		StringBuilder builder = new StringBuilder();
		builder.append(userId);
		builder.append(currencyFrom);
		builder.append(currencyTo);
		builder.append(amountSell);
		builder.append(amountBuy);
		builder.append(rate);
		builder.append(timePlaced);
		builder.append(originatingCountry);
		builder.append(processed);
		return builder.toString();
	}

	public BigDecimal getAmountBuy() {
		return amountBuy;
	}

	public BigDecimal getAmountSell() {
		return amountSell;
	}

	public byte[] getCheckSum() {
		return checkSum;
	}

	public Currency getCurrencyFrom() {
		return currencyFrom;
	}

	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public String getOriginatingCountry() {
		return originatingCountry;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public LocalDateTime getTimePlaced() {
		return timePlaced;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public long getUserId() {
		return userId;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setAmountBuy(BigDecimal amountBuy) {
		this.amountBuy = amountBuy;
	}

	public void setAmountSell(BigDecimal amountSell) {
		this.amountSell = amountSell;
	}

	public void setCheckSum(byte[] checkSum) {
		this.checkSum = checkSum;
	}

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setTimePlaced(LocalDateTime timePlaced) {
		this.timePlaced = timePlaced;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
