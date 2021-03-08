package com.exam.course.controllers;

import java.security.Principal;
import java.text.ParseException;

import com.exam.course.dao.CourseJPADao;
import com.exam.course.models.Course;
import com.exam.course.models.Discipline;
import com.exam.course.models.User;
import com.exam.course.services.CourseService;
import com.exam.course.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseJPADao courseDao;

    @Autowired
    private UserService userService;
    
    @GetMapping("addCourse")
    public String addCourse() {
        return "addCourse.jsp";
    }

    @GetMapping("course/{id}/enroll")
    public String enrollCourse(@PathVariable("id") int id, Principal principal) {
        Course course = courseDao.getOne(id);        
        User user = userService.findByUsername(principal.getName());

        courseService.enroll(user, course);

        return "/";
    }

    @PostMapping("courses") 
    public String uploadVideo(@RequestParam(name = "title") String title, 
                                @RequestParam(name = "discipline") String discipline,
                                @RequestParam(name = "dateOffered") String dateOffered,
                                @RequestParam(name = "type") String type
                            ) 
                            throws ParseException {
        
        Course course = Course.builder()
                        .name(title)
                        .discipline(Discipline.valueOf(discipline))
                        .build();

        System.out.println(course);

        return "redirect:/";
    }
}
