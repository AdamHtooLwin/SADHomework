package com.lab.orm.models;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Benefit {
    
    public Benefit(String title, Set<Employee> emp) {
		super();
		this.title = title;
		this.emp = emp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	
	//Default JPA
	/*
	   OneToMany: LAZY
	   ManyToMany: LAZY
	   ManyToOne: EAGER
       OneToOne: EAGER
	 */
	
	//When you load benefits, it will not load employees by default
	@ManyToMany(mappedBy = "benefits") 
	//let the other side create the association table - employee will be owning side -> EMPLOYEE_BENEFITS
	//Set has better performance than List in many-to-many
	private Set<Employee> emp;
}
