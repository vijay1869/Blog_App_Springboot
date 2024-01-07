package com.blog.BlogAppApis.config;

import com.blog.BlogAppApis.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//most of the things happening are in this file -related to secutiry
//authentication

//extending the class websecurityconfigureradapter it helps us to customize security,how we want to secure our app
//by overriding,customising the methods it has

@Configuration

public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailsService;

//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;
    //Configuring HttpSecurity
//    / extends WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                csrf().
                disable().
                authorizeHttpRequests().
                anyRequest().
                authenticated().
                and().
                httpBasic();

        return http.build();
    }
   //  Authentication
   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

       authProvider.setUserDetailsService(customUserDetailsService);
       authProvider.setPasswordEncoder(passwordEncoder());

       return authProvider;
   }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}


//In basic authentication No html form will be visible
//we will be able to send data in javascript dialogue box
//we can use this in postman also

//the problem with basic authentication is that for accessing any api we need to send name,password everytime
