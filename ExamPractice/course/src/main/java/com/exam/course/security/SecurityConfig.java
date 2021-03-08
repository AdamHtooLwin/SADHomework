package com.exam.course.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//for setting up
//authentication and authorization
//authentication-login/logout
//authorization - who can access what page

@Configuration // this file will be run at
@EnableWebSecurity
// extends -> works because default behavior is still there
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService uds;

    // just one password encoder for whole application
    // singleton
    // @Component -> class level
    @Bean // method level
    // controller, component, service all are singletons
    // entity is not a singleton, thats why we can make users
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //ovverride two functions
    //1st function - authentication
    @Override
    // create this builder created when started
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //what are the userDetailsService
        //what are the password encoder
        auth.userDetailsService(uds).passwordEncoder(bCryptPasswordEncoder());
    }

    //2nd function - authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //h2-console, /login, /register -anyone
        //admin/* - admin - ROLE_ADMIN
        // /** - ROLE_USER, ROLE_ADMIN
        // premium/* - ROLE_ADMIN,ROLE_PREMIUM_USER
        http.csrf().disable() //disable so that h2 works
        .authorizeRequests()
        .antMatchers("/h2-console/**", "/login", "/register").permitAll() // if u put this at bottom none of these pages will work since bottom line will take over
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/**").hasAnyRole("ADMIN", "USER")
        .and()
        .formLogin().permitAll()
        .defaultSuccessUrl("/home", true)
        .and()
        .logout().invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/logout-success").permitAll();

        //because h2 is a frame
        http.headers().frameOptions().disable();
    }   

}
