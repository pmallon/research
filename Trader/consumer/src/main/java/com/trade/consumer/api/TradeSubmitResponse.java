package com.trade.consumer.api;

public final class TradeSubmitResponse {

	private final StatusCode status;
	private final String description;

	public TradeSubmitResponse(StatusCode status ,String description) {
		this.status = status;
		this.description = description;
	}

	public StatusCode getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}
	
	

}
