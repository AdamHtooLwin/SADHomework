package com.example.hr.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.hr.daos.EmployeeDao;
import com.example.hr.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

        // HashMap<Employee, Integer> result= new HashMap<Employee, Integer>();
        List<EmployeeData> result = new ArrayList<EmployeeData>();
        for (Employee e : elist){
            int netValue = e.getNetValue();
            
            EmployeeData eData = new EmployeeData();
            eData.setEmployee(e);
            eData.setNetValue(netValue);

            result.add(eData);
        }

        mv.addObject("result", result);

        return mv;

    }

    @RequestMapping(path = "/employees/edit", method = RequestMethod.POST)
    public String saveOrUpdateUser(@RequestBody Employee employee){
        eDao.save(employee);
        return "home.jsp";
    }

    @RequestMapping(path = "/employees/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam int id){
        Employee employee = eDao.getOne(id);
        eDao.delete(employee);
        return "home.jsp";
    }

    // public class in terms of security any issues?
    public class EmployeeData {
        Employee employee;
        int netValue;

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }

        public int getNetValue() {
            return netValue;
        }

        public void setNetValue(int netValue) {
            this.netValue = netValue;
        }
    }

    private int getNetValue(int salary, int value){
        return salary - value;
    }
}
