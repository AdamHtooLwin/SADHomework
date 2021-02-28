package com.lab.rr.dao;

import com.lab.rr.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
    
}
