package com.example.mvcrest.controller;

import java.util.List;
import java.util.Optional;

import com.example.mvcrest.dao.UserDao;
import com.example.mvcrest.dao.UserJPADao;
import com.example.mvcrest.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// import org.springframework.web.bind.annotation.RestController;
// @RestController -> will not render any interface

// both restful and otherwise
@Controller
public class UserController {
    // Dependency injection
    // inject this variable on runtime
    @Autowired
    UserDao dao; //extends crudrepo -> save

    @Autowired
    UserJPADao jpaDao; // extends jparepo

    // when user goes to http://localhost:8080/, they must be routed to home.jsp

    // call whatever function below if path is this and req is GET
    // @GetMapping("/addUser")
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        // search the webapp directory for home.jsp and render it
        return "home.jsp";
    }

    @RequestMapping(path = "/addUser", method = RequestMethod.GET)
    public String addUser(User user) {
        // we need ot save to the db
        // who save it? dao (data access object)
        // 1. save 
        dao.save(user);
        
        // 2. redirect to home.jsp
        return "home.jsp";
    }
    
    @RequestMapping(path = "/getUser", method = RequestMethod.GET)
    public ModelAndView getUser(@RequestParam int uid){
        ModelAndView mv = new ModelAndView("showUser.jsp");

        // by default, dao.findbyId returns Optional
        // if exists, return user, if not, return nothing
        User u = dao.findById(uid).orElse(new User());

        // add user information to mv
        mv.addObject(u);

        System.out.println((dao.findByNationalitySorted("Hong Kong")));
        System.out.println((dao.findByUidGreaterThan(101)));
        System.out.println((dao.findByNationality("Hong Kong")));

        return mv;

        // return showUser.jsp +User information
    }

    // more restful
    // instead of parambased not effective does not work well across platforms
    // locahost:8080/users -> get all users
    //insteadd of returning JAVA objects, return more genereic format usch as axml or json

    // Part 1: restful calls using dao return as java objects
    @RequestMapping(path="/users", method = RequestMethod.GET)
    @ResponseBody // this anno is for rest methods where view will not be rendered
    public String getUsersREST() {
        return dao.findAll().toString();
    }

    // get one user
    //localhost:8080/user/1
    // uid - pathvariable
    @RequestMapping(path = "/user/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserREST(@PathVariable("uid") int uid) {
        return dao.findById(uid).toString();
    }

    // Part 2: restful calls using jpadao
    // return as JSON/XML
    // return as json/xml

    //get all users
    @RequestMapping(path = "/usersJPA", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<User> getUsersRESTJPA() {
        return jpaDao.findAll();
    }

    // get one user 
    @RequestMapping(path = "/userJPA/{uid}", method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    public Optional<User> getUserRESTJPA(@PathVariable("uid") int uid) {
        return jpaDao.findById(uid);
    }

    // post one user (create)
    @RequestMapping(path = "/userJPA", method = RequestMethod.POST)
    @ResponseBody
    public User postUser(@RequestBody User user){
        jpaDao.save(user);
        return user;
    }

    // put - save or update user
    @RequestMapping(path = "/userJPA", method = RequestMethod.PUT)
    @ResponseBody
    public String saveOrUpdateUser(@RequestBody User user){
        jpaDao.save(user);
        return "Updated!";
    }

    // delete
    @RequestMapping(path = "/userJPA/{uid}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable("uid") int uid){
        User user = jpaDao.getOne(uid);
        jpaDao.delete(user);
        return "Deleted!";
    }
}
