package com.trade.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CountryReport {
	
	private String country;
	private BigDecimal totalAmountSold;
	private BigDecimal totalAmountBought;
	private long tradeCount;
	
	
	public CountryReport(){
		
	}
	
	public CountryReport(String country, BigDecimal totalAmountSold,
			BigDecimal totalAmountBought, long tradeCount) {
		super();
		this.country = country;
		this.totalAmountSold = totalAmountSold;
		this.totalAmountBought = totalAmountBought;
		this.tradeCount = tradeCount;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public BigDecimal getTotalAmountSold() {
		return totalAmountSold;
	}
	public void setTotalAmountSold(BigDecimal totalAmountSold) {
		this.totalAmountSold = totalAmountSold;
	}
	public BigDecimal getTotalAmountBought() {
		return totalAmountBought;
	}
	public void setTotalAmountBought(BigDecimal totalAmountBought) {
		this.totalAmountBought = totalAmountBought;
	}
	public long getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(long tradeCount) {
		this.tradeCount = tradeCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime
				* result
				+ ((totalAmountBought == null) ? 0 : totalAmountBought
						.hashCode());
		result = prime * result
				+ ((totalAmountSold == null) ? 0 : totalAmountSold.hashCode());
		result = prime * result + (int) (tradeCount ^ (tradeCount >>> 32));
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
		CountryReport other = (CountryReport) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (totalAmountBought == null) {
			if (other.totalAmountBought != null)
				return false;
		} else if (totalAmountBought.compareTo(other.totalAmountBought)!=0)
			return false;
		if (totalAmountSold == null) {
			if (other.totalAmountSold != null)
				return false;
		} else if (totalAmountSold.compareTo(other.totalAmountSold)!=0)
			return false;
		if (tradeCount != other.tradeCount)
			return false;
		return true;
	} 
	
	
	
	

}
