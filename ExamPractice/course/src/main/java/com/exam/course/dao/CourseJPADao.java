package com.exam.course.dao;

import com.exam.course.models.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CourseJPADao extends JpaRepository<Course, Integer>{
    
}
