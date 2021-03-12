package com.lab.orm.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean 
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	//authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	//authorization
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //disable so that h2 works
        .authorizeRequests()
        .antMatchers("/h2-console/**", "/login", "/register").permitAll() // if u put this at bottom none of these pages will work since bottom line will take over
        .antMatchers("/admin/**", "/employees/**/delete", "/users/create").hasRole("ADMIN")
        .antMatchers("/**").hasAnyRole("ADMIN", "USER")
        .and()
        .formLogin().permitAll()
        .defaultSuccessUrl("/", true)
        .and()
        .logout().invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/logout-success").permitAll();

        //because h2 is a frame
        http.headers().frameOptions().disable();
    }
		
}
