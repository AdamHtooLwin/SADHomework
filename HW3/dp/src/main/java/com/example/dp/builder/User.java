package com.example.dp.builder;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data //gives setters, getters, toString
public class User {
    
    @Id
    private int uid;
    private String name;
    private String nationality;
    private String email;
}
