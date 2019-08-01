package com.example.resztki.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Test 2


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("pawel").password("{noop}kasia1987").roles("USER");

    }

    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
        //.antMatchers("/", "/home").access("hasRole('USER')")
        // .antMatchers("/**").hasRole("ADMIN")
//                .antMatchers("/**").permitAll();//authorizeRequests();
        //.antMatchers("/h2-console").permitAll();
        http.authorizeRequests()
                .antMatchers("/**").access("hasRole('USER')")
                .and().httpBasic();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());



        http.csrf().disable();
        http.headers().frameOptions().disable();
    }











}
