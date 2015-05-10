package com.trade.consumer.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trade.consumer.api.format.JsonDateDeserializer;
import com.trade.consumer.api.format.JsonCurrencyDeserializer;

/**
 * Submission request to trade system
 * 
 * JSON Sample {"userId": "134256", "currencyFrom": "EUR", "currencyTo": "GBP",
 * "amountSell": 1000, "amountBuy": 747.10, "rate": 0.7471, "timePlaced" :
 * "24-JAN-15 10:27:44", "originatingCountry" : "FR"}
 * 
 * @author Phil
 *
 */
public class TradeRequest {

	
	@NotNull()
	private Long userId;
	
	@NotNull(message = "currencyFrom must not be null and valid ISO currency code.")
	private Currency currencyFrom;
	
	@NotNull(message = "currencyTo must not be null and valid ISO currency code.")
	private Currency currencyTo;
	
	@NotNull()
	private BigDecimal amountSell;
	
	@NotNull()
	private BigDecimal amountBuy;
	
	@NotNull()
	private BigDecimal rate;	
	
	@NotNull(message = "timePlaced must not be null and must comply with Date format dd-MMM-uu HH:mm:ss.")
	private LocalDateTime timePlaced;
	
	@NotNull()
	@Size( min =2 , max = 2 , message="originatingCountry must be 2 chars length")
	private String originatingCountry;
	

	public TradeRequest() {

	}

	
	public BigDecimal getAmountBuy() {
		return amountBuy;
	}

	public BigDecimal getAmountSell() {
		return amountSell;
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

	public Long getUserId() {
		return userId;
	}
	
	public void setAmountBuy(BigDecimal amountBuy) {
		this.amountBuy = amountBuy;
	}
	
	public void setAmountSell(BigDecimal amountSell) {
		this.amountSell = amountSell;
	}
	
	@JsonDeserialize(using = JsonCurrencyDeserializer.class)
	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	
	@JsonDeserialize(using = JsonCurrencyDeserializer.class)
	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}
	
	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}
	
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setTimePlaced(LocalDateTime timePlaced) {
		this.timePlaced = timePlaced;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeRequest [userId=");
		builder.append(userId);
		builder.append(", currencyFrom=");
		builder.append(currencyFrom);
		builder.append(", currencyTo=");
		builder.append(currencyTo);
		builder.append(", amountSell=");
		builder.append(amountSell);
		builder.append(", amountBuy=");
		builder.append(amountBuy);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", timePlaced=");
		builder.append(timePlaced);
		builder.append(", originatingCountry=");
		builder.append(originatingCountry);
		builder.append("]");
		return builder.toString();
	}

}
