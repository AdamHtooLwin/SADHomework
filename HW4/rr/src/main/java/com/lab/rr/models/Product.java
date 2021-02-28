package com.lab.rr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.lab.rr.strategy.CompleteRecognitionStrategy;
import com.lab.rr.strategy.RecognitionStrategy;
import com.lab.rr.strategy.ThreewayRecognitionStrategy;

import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@ToString
public class Product {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;
	private String type;

	@Transient
	private RecognitionStrategy recognitionStrategy;

	public Product(String name, String type) {
		this.name = name;
		this.type = type;
		initRecognitionStrategy();
	}

	protected void initRecognitionStrategy() {
		if ("WORDPROCESSOR".equals(type)) {
			recognitionStrategy = new CompleteRecognitionStrategy();
		} else if ("SPREADSHEET".equals(type)) {
			recognitionStrategy = new ThreewayRecognitionStrategy(60, 90);
		} else if ("DATABASE".equals(type)) {
			recognitionStrategy = new ThreewayRecognitionStrategy(30, 60);
		} else {
			throw new IllegalArgumentException(
					"Unsupported product type [" + type + "]");
		}
	}

	public void calculateRevenueRecognition(Contract contract) {
		recognitionStrategy.calculateRevenueRecognitions(contract);
	}

	@PostLoad
	protected void onPostLoad() {
		initRecognitionStrategy();
	}

	protected Product() { /* as needed by JPA/ORM */ }
}
