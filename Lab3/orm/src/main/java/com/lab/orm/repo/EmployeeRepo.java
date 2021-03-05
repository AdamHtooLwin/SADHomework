package com.lab.orm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.orm.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
}
