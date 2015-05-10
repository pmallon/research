package com.trade.service;

/**
 * This security exception indicate data has been tampered with.
 * 
 * @author Phil
 *
 */
public class TamperException extends RuntimeException {

	public TamperException(String description) {
		super(description);
	}

}
