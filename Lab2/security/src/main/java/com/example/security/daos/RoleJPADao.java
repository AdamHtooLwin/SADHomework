package com.example.security.daos;

import com.example.security.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJPADao extends JpaRepository<Role, Integer>{
    
}
