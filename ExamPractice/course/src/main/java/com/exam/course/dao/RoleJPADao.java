package com.exam.course.dao;

import com.exam.course.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJPADao extends JpaRepository<Role, Integer>{
    
}
