package com.trade.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((amountBuy == null) ? 0 : amountBuy.hashCode());
		result = prime * result
				+ ((amountSell == null) ? 0 : amountSell.hashCode());
		result = prime * result + Arrays.hashCode(checkSum);
		result = prime * result
				+ ((currencyFrom == null) ? 0 : currencyFrom.getCurrencyCode().hashCode());
		result = prime * result
				+ ((currencyTo == null) ? 0 : currencyTo.getCurrencyCode().hashCode());
		result = prime
				* result
				+ ((originatingCountry == null) ? 0 : originatingCountry
						.hashCode());
		result = prime * result + (processed ? 1231 : 1237);
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result
				+ ((timePlaced == null) ? 0 : timePlaced.hashCode());
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (amountBuy == null) {
			if (other.amountBuy != null)
				return false;
		} else if (amountBuy.compareTo(other.amountBuy) != 0)
			return false;
		if (amountSell == null) {
			if (other.amountSell != null)
				return false;
		} else if (amountSell.compareTo(other.amountSell)!=0)
			return false;
		if (!Arrays.equals(checkSum, other.checkSum))
			return false;
		if (currencyFrom == null) {
			if (other.currencyFrom != null)
				return false;
		} else if (!currencyFrom.getCurrencyCode().equals(other.currencyFrom.getCurrencyCode()))
			return false;
		if (currencyTo == null) {
			if (other.currencyTo != null)
				return false;
		} else if (!currencyTo.getCurrencyCode().equals(other.currencyTo.getCurrencyCode()))
			return false;
		if (originatingCountry == null) {
			if (other.originatingCountry != null)
				return false;
		} else if (!originatingCountry.equals(other.originatingCountry))
			return false;
		if (processed != other.processed)
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (rate.compareTo(other.rate)!=0)
			return false;
		if (timePlaced == null) {
			if (other.timePlaced != null)
				return false;
		} else if (!timePlaced.equals(other.timePlaced))
			return false;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

}
