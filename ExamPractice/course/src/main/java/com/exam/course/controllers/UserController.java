package com.exam.course.controllers;

import java.security.Principal;

import com.exam.course.models.Role;
import com.exam.course.models.User;
import com.exam.course.services.UserService;
import com.exam.course.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(path = "/home")
    public ModelAndView userDashboard(Principal principal) {
        ModelAndView mv = new ModelAndView("home.jsp");
        User u = userService.findByUsername(principal.getName());
        mv.addObject("user", u);

        // simple demo how roles can be used
        for (Role role: u.getRoles()) {
            if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
                System.out.println("Current user is " + role.getName());
                mv.addObject("admin_flag", true);
            }
            if (role.getName().equalsIgnoreCase("ROLE_USER")) {
                System.out.println("Current user is " + role.getName());
            }
            if (role.getName().equalsIgnoreCase("ROLE_PREMIUM_USER")) {
                System.out.println("Current user is " + role.getName());
            }
        }

        return mv;
    }
}
