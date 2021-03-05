package com.lab.orm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.orm.models.Leave;

public interface LeaveRepo extends JpaRepository<Leave, Integer> {
	
}
