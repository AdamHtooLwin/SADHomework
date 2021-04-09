package com.lab.locking.services;

import java.time.LocalTime;

import javax.transaction.Transactional;

import com.lab.locking.dao.ProductDao;
import com.lab.locking.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductDao pDao;

    public void createMockProduct() {
		// creating and persisting an product
		Product pro = new Product();
		pro.setName("Microsoft Word");
		pro.setPrice(499L);
		pDao.save(pro);
	}

    @Transactional
	@Async
	public void readLockTransaction() throws InterruptedException {
			
		System.out.println(LocalTime.now() + " <-- Reading Product entity -->");

		Product product1 = null;
		try {
			product1 = pDao.findProductForRead(1L);
		} catch (Exception e) {
			System.err
					.println(LocalTime.now() + " -- Got exception while " + 
			"acquiring the read lock:\n " + e + " --");
			return;
		}

		System.out.println(LocalTime.now() + " -- Acquired the read lock --");

		System.out.println(LocalTime.now() + " -- Read product: " + product1 + " --");
		
        int sleepTime = 1000;
        System.out.println("Read tx sleeping for " + sleepTime);
		Thread.sleep(sleepTime);


	}

    @Transactional
	@Async
	public void writeLockTransaction() throws InterruptedException {
		
		Thread.sleep(100);
		
		System.out.println(LocalTime.now() + " <-- Writing Product entity -->");

		Product product2 = null;
		try {
			product2 = pDao.findProductForWrite(1L);
		} catch (Exception e) {
			System.err
					.println(LocalTime.now() + " -- Got exception while " + 
			"acquiring the write lock:\n " + e + " --");
			return;
		}

		System.out.println(LocalTime.now() + " -- Acquired write lock --");
		product2.setName("New name");
		pDao.save(product2);

		System.out.println(LocalTime.now() + " -- User 2 updated product: " + product2 
				+ " --");
	}
}
