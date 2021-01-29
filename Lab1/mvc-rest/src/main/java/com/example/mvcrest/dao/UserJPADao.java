package com.example.mvcrest.dao;

import com.example.mvcrest.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPADao extends JpaRepository<User,  Integer> {
    
}
