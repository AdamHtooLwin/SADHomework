package com.example.mvcrest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

// model is basically a code repr. of your db tables/sc

@Entity
// Annotations
// letting Spring Boot to automatically create a db table based on the fields
public class User {
    
    @Id
    private int uid;
    private String name;
    private String nationality;

    // getters and setters
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
    }
    
    
	@Override
	public String toString() {
		return "User [name=" + name + ", nationality=" + nationality + ", uid=" + uid + "]";
	}

    // toString - used for returning java objects
    // Override -> replace old function with new function
}
