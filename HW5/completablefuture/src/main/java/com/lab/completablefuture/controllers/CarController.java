package com.lab.completablefuture.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import com.lab.completablefuture.models.Car;
import com.lab.completablefuture.services.CarService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @RequestMapping (method = RequestMethod.POST, consumes={MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity uploadFile(
            @RequestParam (value = "files") MultipartFile[] files) {
        try {
            for(final MultipartFile file: files) {
                carService.saveCars(file.getInputStream());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    
    private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetCarFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
    
    
//    Here, we are calling Async method 3 times. 
//    The CompletableFuture.allOf() will wait until all 
//    the CompletableFutures have been completed and join() 
//    will combine the results. Note that, this is just for 
//    the demonstration purpose.
    @RequestMapping (method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getAllCars() {
        try {

            CompletableFuture<List<Car>> cars1=carService.getAllCars();
            CompletableFuture<List<Car>> cars2=carService.getAllCars();
            CompletableFuture<List<Car>> cars3=carService.getAllCars();
            CompletableFuture.allOf(cars1, cars2, cars3).join();
            
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
