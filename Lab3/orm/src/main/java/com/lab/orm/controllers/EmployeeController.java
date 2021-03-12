package com.lab.orm.controllers;

import java.security.Principal;

import com.lab.orm.models.Employee;
import com.lab.orm.models.User;
import com.lab.orm.repo.EmployeeRepo;
import com.lab.orm.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable int id) {    	
    	Employee emp = empRepo.findById(id).orElse(null);
        return emp;
       
   }

}
