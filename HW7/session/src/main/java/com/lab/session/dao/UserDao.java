package com.lab.session.dao;

import com.lab.session.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
}
