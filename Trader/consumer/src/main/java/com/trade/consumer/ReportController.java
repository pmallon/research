package com.trade.consumer;

import java.util.Map;
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
        model.addAttribute("name", "Phil");
        
        
        Future<Map<String,CountryReport>> byCountryReport = tradeService.generateByCountryReport();
        
        
        return "report";
    }


}
