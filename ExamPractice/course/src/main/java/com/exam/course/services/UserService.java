package com.exam.course.services;

import com.exam.course.models.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);    
}
