package com.lab.locking.dao;

import javax.persistence.LockModeType;

import com.lab.locking.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductDao extends JpaRepository<Product, Long>{
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Product p where p.id = :id")
	Product findProductForWrite(@Param("id") Long id);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select p from Product p where p.id = :id")
	Product findProductForRead(@Param("id") Long id);
}
