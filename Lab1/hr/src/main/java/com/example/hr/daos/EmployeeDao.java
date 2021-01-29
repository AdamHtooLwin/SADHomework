package com.example.hr.daos;

import com.example.hr.models.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    
}
