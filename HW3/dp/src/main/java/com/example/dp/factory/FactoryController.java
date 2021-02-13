package com.example.dp.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FactoryController {
    private RoleFactory factory;

    @Autowired
    public FactoryController(RoleFactory factory) {
        this.factory = factory;
    }

    @PostMapping("addRole/{type}/{name}")
    public Role addRole(@PathVariable String type, @PathVariable String name) {
        Role role = this.factory.createRole(type);
        role.setName(name);
        role.checkAccess();
        return role;
    }
}
