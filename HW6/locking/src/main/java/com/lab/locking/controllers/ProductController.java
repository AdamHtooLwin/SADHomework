package com.lab.locking.controllers;

import com.lab.locking.dao.ProductDao;
import com.lab.locking.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
	private ProductDao pDao;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product findOne(@PathVariable Long id) {
		return pDao.findById(id).orElse(null);
	}
	
	@PutMapping
	public Product put(@RequestBody Product product) {
		return pDao.save(product);
	}
	
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return pDao.save(product);
	}
}
