package com.exam.course.services;

import com.exam.course.models.Course;
import com.exam.course.models.User;

public interface CourseService {
    void enroll(User user, Course course);
}
