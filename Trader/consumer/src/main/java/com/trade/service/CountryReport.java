package com.trade.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CountryReport {
	
	private BigDecimal totalAmountSold;
	private BigDecimal totalAmountBought;
	private BigDecimal biggestTrade;
	private LocalDateTime lastTrade;
	
	
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
	public BigDecimal getBiggestTrade() {
		return biggestTrade;
	}
	public void setBiggestTrade(BigDecimal biggestTrade) {
		this.biggestTrade = biggestTrade;
	}
	public LocalDateTime getLastTrade() {
		return lastTrade;
	}
	public void setLastTrade(LocalDateTime lastTrade) {
		this.lastTrade = lastTrade;
	}
	
	

}
