package com.example.mvcrest.dao;

import java.util.List;

import com.example.mvcrest.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// type of pk
public interface UserDao extends CrudRepository<User, Integer> {
    // if i just create this dao, it wont do much because we need to write a lot of queries for save read delete update
    // we dont want to do these boiler plate codes

    // this is done! by extending crudrepo it comes with all common db functions like save update and delete
    // who creates crudrepo -> spring team

    // one limitation of crud repo is it does not support json/xml and Hibernate
    // extends JpaRepository

    // crud repo seems nice!
    // but what if i want to write soem custom query?
    // how?


    // use auto query by fields
    // by default, it only supports findByPrimaryKey, findByUid
    // findbyField

    List<User> findByNationality(String nationality);
    List<User> findByUidGreaterThan(int uid);

    //custom query
    // you need to map this query to a dao.functioname

    // comes from spring jpa
    @Query("from User where nationality = ?1 order by name ")
    List<User> findByNationalitySorted(String nationality);
}
