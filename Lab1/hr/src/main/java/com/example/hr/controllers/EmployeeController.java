package com.example.hr.controllers;

import java.util.List;

import com.example.hr.daos.EmployeeDao;
import com.example.hr.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao eDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("home.jsp");

        // by default, dao.findbyId returns Optional
        // if exists, return user, if not, return nothing
        List<Employee> elist = eDao.findAll();

        // add user information to mv
        for (Employee e : elist){
            int netValue = getNetValue(e.getSalary(), e.getValue());
            // nets.add(netValue);
        }

        mv.addObject("elist", elist);
        System.out.println(elist);

        return mv;

    }

    @RequestMapping(path = "/employees/edit", method = RequestMethod.POST)
    public String saveOrUpdateUser(@RequestParam int id){
        Employee employee = eDao.getOne(id);
        eDao.save(employee);
        return "home.jsp";
    }

    @RequestMapping(path = "/employees/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam int id){
        Employee employee = eDao.getOne(id);
        eDao.delete(employee);
        return "home.jsp";
    }

    private int getNetValue(int salary, int value){
        return salary - value;
    }
}
