package com.lab.orm.models;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class User {
    
    @Id
	private int id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	private String role;
	
	private boolean active;
	
	//Default JPA
	/*
	   OneToMany: LAZY
	   ManyToMany: LAZY
	   ManyToOne: EAGER
       OneToOne: EAGER
	 */
	
    // emp table will have user id as FK
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "emp_id", referencedColumnName = "id")
    @JsonIgnore
    @MapsId
	private Employee emp;

	public User(String username, String password, String role, boolean active, Employee emp) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.active = active;
		this.emp = emp;
	}
}
