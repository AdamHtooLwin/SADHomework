package com.lab.rr.dao;

import com.lab.rr.models.Contract;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractJpaRepository extends JpaRepository<Contract, Integer> {
    
}
