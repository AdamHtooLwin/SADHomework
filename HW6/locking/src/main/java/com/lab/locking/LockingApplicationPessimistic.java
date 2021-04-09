package com.lab.locking;

import java.io.IOException;

import com.lab.locking.services.ProductService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LockingApplicationPessimistic {
    
    public static void main(String[] args) throws IOException, InterruptedException {
		ConfigurableApplicationContext applicationContext = 
				SpringApplication.run(LockingApplicationPessimistic.class, args);
		ProductService ps = applicationContext.getBean(ProductService.class);
		
		ps.createMockProduct();
		ps.writeLockTransaction();
		ps.readLockTransaction();
   
	}
}
