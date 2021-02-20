package com.example.security.daos;

import com.example.security.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//people can access this information via restful api
//but i dont want any controllers
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserJPADao extends JpaRepository<User, Integer>{
    //usee jpa automatic association function
    //will not automatically give but need to define
    User findByUsername(String username);


    // why interface => just extend jparepository -> nice functions
    // interface allows easy swap out for different functions
    //save() -> one way see below
}

// public interface
// {
//     save -> different way
// }
