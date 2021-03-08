package com.exam.course.security;

import com.exam.course.dao.UserJPADao;
import com.exam.course.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//provides service
//toSecurityConfig
//for getting some user detail info
@Service
// what is @Service, how is it different from @Component or @Controller
// @Component is most basic thing -> can do Autowired
// @Controller - @Component + some additional controller capabilities like mvc
// @Service - @Component + some additional service capabilities -> ?
public class MyUserDetailService implements UserDetailsService {
    // only repo -> can be autowired
    @Autowired
    private UserJPADao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new UserDetailsImpl(user);
    }
    
    
}
