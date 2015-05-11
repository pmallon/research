package com.trade.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trade.service.CountryReport;
import com.trade.service.TradeService;

@Controller
public class ReportController {
	
	@Autowired()	
	TradeService tradeService;
	
	@RequestMapping("/report")
    public String generateReport(Model model) {   
        
        
		/*
		 * Using Future to allow for multiple queries to run in parallel 
		 */
        Future<List<CountryReport>> byCountryReport = tradeService.generateByCountryReport();
       
        try {
			model.addAttribute("countries", byCountryReport.get());
		} catch (InterruptedException e) {
			model.addAttribute("countries",new ArrayList<CountryReport>());
			//Log error
		} catch (ExecutionException e) {
			model.addAttribute("countries",new ArrayList<CountryReport>());
			//Log error
		}
        
        return "report";
    }


}
