package com.exam.course.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.Set;

import com.exam.course.dao.CourseJPADao;
import com.exam.course.models.Course;
import com.exam.course.models.Discipline;
import com.exam.course.models.User;
import com.exam.course.services.CourseService;
import com.exam.course.services.EmailService;
import com.exam.course.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseJPADao courseDao;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
    
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
    @GetMapping("course/{id}/sendEmail")
    public ModelAndView sendEmailPrep(@PathVariable("id") int id, Principal principal) {
        ModelAndView mv = new ModelAndView("/sendEmail.jsp");
        Course course = courseDao.getOne(id);
        mv.addObject("course", course);

        return mv;
    }

    @PostMapping("course/{id}/sendEmail")
    public String sendEmail(@PathVariable("id") int id, @RequestParam(name = "message") String message) {
        Course course = courseDao.getOne(id);
        Set<User> users = course.getUsers();
        
        for (User user: users) {
            SimpleMailMessage emailMsg = new SimpleMailMessage();
            emailMsg.setTo(user.getEmail());
            emailMsg.setText(message);
            emailMsg.setSubject("Registration successful!");
            emailMsg.setFrom("admin@random.asia");

            emailService.sendEmail(emailMsg);
        }

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
