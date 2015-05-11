package com.trade.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.trade.service.CountryReport;
import com.trade.service.TradeService;

import org.springframework.ui.Model;

public class ReportControllerTest {
	
	private ReportController reportController;
	private TradeService tradeService;
	private Model model;
	private Future future;
	
	@Before
	public void setup(){		
		tradeService = Mockito.mock(TradeService.class);
		model = Mockito.mock(Model.class);
		future = Mockito.mock(Future.class);	
		
		reportController = new ReportController();
		reportController.tradeService = tradeService;
	}
	
	@Test
	public void testReport() throws InterruptedException, ExecutionException{
		
		List<CountryReport> report = new ArrayList<CountryReport>();
		report.add(new CountryReport());
		
		Future<List<CountryReport>> byCountryReport = tradeService.generateByCountryReport();
		Mockito.when(tradeService.generateByCountryReport()).thenReturn(future);
		Mockito.when(future.get()).thenReturn(report);
		
		reportController.generateReport(model);
		
		Mockito.verify(model).addAttribute(Mockito.eq("countries"),Mockito.eq(report));
	}
	
	@Test
	public void testReportInterruptedException() throws InterruptedException, ExecutionException{
		
		List<CountryReport> report = new ArrayList<CountryReport>();
		report.add(new CountryReport());
		
		Future<List<CountryReport>> byCountryReport = tradeService.generateByCountryReport();
		Mockito.when(tradeService.generateByCountryReport()).thenReturn(future);
		Mockito.when(future.get()).thenThrow(new InterruptedException());
		
		reportController.generateReport(model);
		
		Mockito.verify(model).addAttribute(Mockito.eq("countries"),Mockito.isA(List.class));
	}
	
	@Test
	public void testReportExecutionException() throws InterruptedException, ExecutionException{
		
		List<CountryReport> report = new ArrayList<CountryReport>();
		report.add(new CountryReport());
		
		Future<List<CountryReport>> byCountryReport = tradeService.generateByCountryReport();
		Mockito.when(tradeService.generateByCountryReport()).thenReturn(future);
		Mockito.when(future.get()).thenThrow(new ExecutionException(new RuntimeException()));
		
		reportController.generateReport(model);
		
		Mockito.verify(model).addAttribute(Mockito.eq("countries"),Mockito.isA(List.class));
	}

}
