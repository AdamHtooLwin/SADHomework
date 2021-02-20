package com.example.security.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.example.security.models.Role;
import com.example.security.models.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//MyUserDetailsService
//will get details from this class
//userdetails is interface -> set of functions that need to be implemented -> signature of function
public class UserDetailsImpl implements UserDetails {
    private User user;

    public UserDetailsImpl(User user){
        this.user = user;
    }

    //once we create what authority to give?
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //hashset higher performance than set
        Set <GrantedAuthority> auth = new HashSet<>();
        
        //set authorities based on roles
        //one user have many users
        //auth will have many auth
        //auth.add

        for (Role role: user.getRoles()){
            auth.add(new SimpleGrantedAuthority(role.getName()));
        }

        // return some colleection of authrotiy -> list/set
        return auth;
    }

    public int getID(){
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
