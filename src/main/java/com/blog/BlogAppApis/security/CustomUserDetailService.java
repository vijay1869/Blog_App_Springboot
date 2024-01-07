package com.blog.BlogAppApis.security;

import com.blog.BlogAppApis.entities.User;
import com.blog.BlogAppApis.exceptions.ResourceNotFoundException;
import com.blog.BlogAppApis.respositories.UserRepo;
import com.blog.BlogAppApis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service//or service?
public class CustomUserDetailService implements UserDetailsService {
    /**
     * userdetailsservice-core interface which stores user specific data
     */
    //when security wants the user it calls this method

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database from  username
        //given username ,we need to fetch all other details and return user details instance
        User user=this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","Email:"+username,0));

        //UserDetails userDetails = new org.springframework.security.core.userdetails.User();

        return user;
        //how to return userdetails object?let user impletment userdetails
    }
}
