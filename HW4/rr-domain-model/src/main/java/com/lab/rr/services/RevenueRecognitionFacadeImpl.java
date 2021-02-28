package com.lab.rr.services;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

import com.lab.rr.dao.ContractJpaRepository;
import com.lab.rr.dao.ProductJpaRepository;
import com.lab.rr.models.Contract;
import com.lab.rr.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevenueRecognitionFacadeImpl implements RevenueRecognitionFacade{

    private RevenueRecognitionService recognitionService;
	private ContractJpaRepository contractRepository;
	private ProductJpaRepository productRepository;

	@Autowired
	public RevenueRecognitionFacadeImpl(RevenueRecognitionService recognitionService, 
			ContractJpaRepository contractRepository,
			ProductJpaRepository productRepository) {
		this.recognitionService = recognitionService;
		this.contractRepository = contractRepository;
		this.productRepository = productRepository;
	}

	@Override
	public MonetaryAmount recognizedRevenue(int contractId, LocalDate asOf) {
		return recognitionService.recognizedRevenue(contractId, asOf);
	}

	@Override
	public void calculateRevenueRecognitions(int contractId) {
		recognitionService.calculateRevenueRecognitions(contractId);
		
	}

	@Override
	public int insertContractInformation(int productId, MonetaryAmount contractPrice, 
			LocalDate dateSigned) {
		Product product = productRepository.getOne(productId);
		Contract contract = new Contract(product, contractPrice, dateSigned);
		contractRepository.save(contract);
		return contract.getId();
	}
	
	@Override
	public int insertProductInformation(String name, String type) {
		Product product = new Product(name, type);
		productRepository.save(product);
		return product.getId();
	}
}
