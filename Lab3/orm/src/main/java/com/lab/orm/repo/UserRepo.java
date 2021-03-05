package com.lab.orm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lab.orm.models.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
}
