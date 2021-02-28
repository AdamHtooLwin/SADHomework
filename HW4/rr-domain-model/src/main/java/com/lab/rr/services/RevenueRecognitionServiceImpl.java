package com.lab.rr.services;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

import com.lab.rr.dao.ContractJpaRepository;
import com.lab.rr.models.Contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RevenueRecognitionServiceImpl implements RevenueRecognitionService{
    
	private final ContractJpaRepository contractRepository;

	@Autowired
	public RevenueRecognitionServiceImpl(ContractJpaRepository contractRepository) {
		this.contractRepository = contractRepository;
	}

	@Override
	public MonetaryAmount recognizedRevenue(int contractId, LocalDate asOf) {
		Contract contract = contractRepository.getOne(contractId);
		return contract.recognizedRevenue(asOf);
	}

	@Override
	public void calculateRevenueRecognitions(int contractId) {
		Contract contract = contractRepository.getOne(contractId);
		contract.calculateRevenueRecognitions();
		//Does not require .save because @transactional make sure all changes
		//are poppulated after end of transactions
	}
}
