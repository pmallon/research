package com.trade.consumer;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trade.consumer.api.StatusCode;
import com.trade.consumer.api.TradeRequest;
import com.trade.consumer.api.TradeSubmitResponse;
import com.trade.consumer.api.format.CountryCodeUtil;
import com.trade.processor.TradeHandler;
import com.trade.service.TamperException;

/**
 * Endpoint for trade requests.Is responsible for consumer the message and
 * validating.
 * 
 * @author Phil
 *
 */
@RestController
public class TraderController {

	@Autowired()
	@Qualifier("tradeHadler")
	TradeHandler tradeHandler;
	
	//Move to Spring config
	private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	
	
	/**
	 * Validates the request  , then hands of to asynchronous
	 * processing system.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/submitTrade", method = RequestMethod.POST)
	public TradeSubmitResponse submitTrade(@RequestBody TradeRequest request) {
		TradeSubmitResponse response = validateTrade(request);
		if (response == null) {			
			try{
				
				tradeHandler.processTrade(request);
				response = new TradeSubmitResponse(StatusCode.COMPLETE,
						"Trade request received");
			} 
			catch (Exception e) {// Catch All to ensure a clean error response
			
				return new TradeSubmitResponse(StatusCode.SYSTEM_ERROR,
						"System Failure , Please try again");
				
				//Log Error
			}
		}

		return response;
	}

	/**
	 * Perform business and security validation.
	 * 
	 * @param request
	 * @return
	 */
	private TradeSubmitResponse validateTrade(TradeRequest request) {

		TradeSubmitResponse response = null;
		
		Validator validator = validatorFactory.getValidator();

		Set<ConstraintViolation<TradeRequest>> violations = validator
				.validate(request);

		if (!violations.isEmpty()) {

			StringBuilder builder = new StringBuilder();
			for (ConstraintViolation<TradeRequest> violation : violations) {
				builder.append(violation.getPropertyPath().toString());
				builder.append(" - ");
				builder.append(violation.getMessage());
				builder.append("|");
			}

			response = new TradeSubmitResponse(StatusCode.INVALID_REQUEST,
					builder.toString());
		}
		
		if(!CountryCodeUtil.isValidISOCountry(request.getOriginatingCountry())){
			response = new TradeSubmitResponse(StatusCode.INVALID_REQUEST,
					"Country code not valid");
		}

		/**
		 * In combination with using HTTPS and certs the below application level
		 * security could be added.
		 * 
		 */

		/*
		 * Similar to tradeDao , a checkSum could be added to in-bound message
		 * by client to help prevent data being tampered with.Would require
		 * shared secret between the systems to use as salt
		 */

		/*
		 * To help prevent replay attacks a requestId ( which is part of the
		 * generation of checksum and unique by client system , e.g. userId )
		 * could be added to in-bound message , and system compares it to last
		 * message received and rejects if not matched.
		 */

		return response;

	}

}
