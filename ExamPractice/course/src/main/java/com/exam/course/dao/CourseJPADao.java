package com.exam.course.dao;

import com.exam.course.models.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJPADao extends JpaRepository<Course, Integer>{
    
}
