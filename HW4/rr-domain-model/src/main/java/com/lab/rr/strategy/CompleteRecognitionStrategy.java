package com.lab.rr.strategy;

import com.lab.rr.models.Contract;

public class CompleteRecognitionStrategy implements RecognitionStrategy {

    @Override
    public void calculateRevenueRecognitions(Contract contract) {
        contract.addRevenueRecognition(contract.getRevenue(), contract.getDateSigned());
    }
    
}
