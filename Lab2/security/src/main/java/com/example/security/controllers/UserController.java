package com.example.security.controllers;

import java.security.Principal;

import javax.validation.Valid;

import com.example.security.models.Role;
import com.example.security.models.User;
import com.example.security.services.UserService;
import com.example.security.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    // how do i know who is the current user -> who is currently logged in? -> Principal principal
    // who gave this param to method -> dep injection

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

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register.jsp";
    }

    // bindingresult is the object holding the errors after validations
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()){
            return "register.jsp";
        }

        userService.save(user);

        return "login.jsp";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login.jsp";
    }

    @RequestMapping(path = "/logout-success", method = RequestMethod.GET)
    public String logout() {
        return "logout.jsp";
    }
}
