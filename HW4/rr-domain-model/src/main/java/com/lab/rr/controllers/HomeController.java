package com.lab.rr.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lab.rr.dao.ContractJpaRepository;
import com.lab.rr.dao.ProductJpaRepository;
import com.lab.rr.models.Contract;
import com.lab.rr.models.Product;
import com.lab.rr.services.RevenueRecognitionFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
	RevenueRecognitionFacade script;

	@Autowired
	ProductJpaRepository productRepo;

	@Autowired
	ContractJpaRepository contractRepo;

	@GetMapping(path = "/")
	public ModelAndView home() {
		List<Product> products = productRepo.findAll();

		Map<String, Object> allObjectsMap = new HashMap<String, Object>();
		allObjectsMap.put("products", products);

		ModelAndView mv = new ModelAndView();
		mv.addAllObjects(allObjectsMap);

		mv.setViewName("home.jsp");
		return mv;
	}

	@GetMapping(path = "/check")
	public ModelAndView calculateRevenueRecognition() {
		List<Contract> contracts = contractRepo.findAll();
		
		System.out.println(contracts.toString());
		
		Map<String, Object> allObjectsMap = new HashMap<String, Object>();
		allObjectsMap.put("contracts", contracts);
		
		ModelAndView mv = new ModelAndView();
		mv.addAllObjects(allObjectsMap);

		mv.setViewName("checkrr.jsp");
		return mv;
	}	

}
