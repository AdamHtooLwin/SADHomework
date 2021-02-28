package com.lab.rr.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.money.MonetaryAmount;

import com.lab.rr.helpers.DollarHelper;
import com.lab.rr.services.RevenueRecognitionFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DomainModelController {

	@Autowired
	RevenueRecognitionFacade facade;
	
	@Autowired
	DollarHelper dollarHelper;

	@GetMapping(path = "/addContract")
	//Use LocalDate to replace the old Date which uses Java 8 API
	public String addContract(@RequestParam int pid, @RequestParam int price, 
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
	LocalDate dateSigned ) {

		//convert to monetary
		MonetaryAmount priceConverted = dollarHelper.dollars(price);
		
		//create contract
		int contractId = facade.insertContractInformation(pid, priceConverted, dateSigned);
	
		//insert revenue recognitions based on type
		facade.calculateRevenueRecognitions(contractId);
		
		return "redirect:/check";
	}
	
	@GetMapping(path = "/checkRecognizedRevenue")
	@ResponseBody
	//Use LocalDate to replace the old Date which uses Java 8 API
	public ModelAndView checkRecognizedRevenue(@RequestParam int cid, 
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
	LocalDate asOf ) {

		//Check recognized revenue
		MonetaryAmount recognizedRevenue = facade.recognizedRevenue(cid, asOf);
		BigDecimal revenue = recognizedRevenue.getNumber().numberValue(BigDecimal.class)
		.setScale(recognizedRevenue.getCurrency().getDefaultFractionDigits());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("revenue", revenue);
		map.put("date", asOf);
		ModelAndView mv = new ModelAndView("showrr.jsp", "data", map);
		
		return mv;
	}
    
}
