package com.example.security.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    //why not list
    //set is more effective for m2m
    //list is ok for 12m
    //mapped by - field name in the origin (users) model
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
