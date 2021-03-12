package com.lab.orm.services;

import com.lab.orm.models.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);    
}
