package com.example.security.services;

import com.example.security.models.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);    
}
