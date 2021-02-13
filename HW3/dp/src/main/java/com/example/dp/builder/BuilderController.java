package com.example.dp.builder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/builder")
public class BuilderController {
    
    @GetMapping
    public String getUser1(){
        User u = new User.UserBuilder()
                .email("test@ait.asia")
                .name("John Smith")
                .nationality("Hong Kong")
                .uid(999).build();

        System.out.println(u.toString());
        return "Created!";
    }
}
