package com.exam.course.services;

import java.util.HashSet;
import java.util.Set;

import com.exam.course.dao.UserJPADao;
import com.exam.course.models.Course;
import com.exam.course.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private UserJPADao userDao;

    @Override
    public void enroll(User user, Course course) {
        Set<Course> courses = user.getCourses();
        courses.add(course);
        userDao.save(user);       
    }
    
}
