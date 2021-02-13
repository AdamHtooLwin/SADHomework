package com.example.dp.factory;

public class Member implements Role {
    private String name;

    public Member() {
        super();
    }

    @Override
    public void checkAccess() {
        System.out.println("Granted access level: Member")

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getRole() {
        return "Member";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
}
