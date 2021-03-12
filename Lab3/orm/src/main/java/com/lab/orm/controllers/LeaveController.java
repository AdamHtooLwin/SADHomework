package com.lab.orm.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import com.lab.orm.models.AnnualLeave;
import com.lab.orm.models.Employee;
import com.lab.orm.models.Leave;
import com.lab.orm.models.LeaveType;
import com.lab.orm.models.SickLeave;
import com.lab.orm.models.User;
import com.lab.orm.repo.LeaveRepo;
import com.lab.orm.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LeaveController {
    @Autowired
    UserService userService;

    @Autowired
    LeaveRepo leaveRepo;

    @GetMapping("/")
    public ModelAndView home(Principal principal) { 
        ModelAndView mv = new ModelAndView("home.jsp");

        User user = userService.findByUsername(principal.getName());
        mv.addObject("user", user);

        Set<Leave> leaves = user.getEmp().getLeaves();
        mv.addObject("leaves", leaves);

        return mv;
   }

   @PostMapping("/leaves")
    public String addLeaves(Principal principal, @RequestParam(name = "start") String start, 
                            @RequestParam(name = "end") String end,
                            @RequestParam(name = "type") String typeStr,
                            @RequestParam(name = "remarks") String remarks
                            ) { 

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);

        LeaveType type = LeaveType.valueOf(typeStr);

        Employee employee = userService.findByUsername(principal.getName()).getEmp();

        switch (type) {
            case SICK:
                System.out.println("Here");
                SickLeave sl = new SickLeave(employee, false, "Flu", startDate, endDate);
                leaveRepo.save(sl);
            case ANNUAL:
                AnnualLeave al = new AnnualLeave(employee, false, "Kids affairs", startDate, endDate);
                leaveRepo.save(al);    
        }

        return "redirect:/";
   }
}
