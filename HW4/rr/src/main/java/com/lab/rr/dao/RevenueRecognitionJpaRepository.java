package com.lab.rr.dao;

import com.lab.rr.models.RevenueRecognition;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRecognitionJpaRepository extends JpaRepository<RevenueRecognition, Integer> {
    
}
