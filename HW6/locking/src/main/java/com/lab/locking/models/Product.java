package com.lab.locking.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    //	@Version annotation -> column in the 
    //	JPA takes care of incrementing the version as well 
	
	@Version
	private Long version;
	
	private String name;
	
	private Long price;
}
