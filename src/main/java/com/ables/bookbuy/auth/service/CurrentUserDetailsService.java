package com.ables.bookbuy.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ables.bookbuy.models.User;
import com.ables.bookbuy.service.UserService;


@Service
public class CurrentUserDetailsService implements UserDetailsService{
	   private final UserService userService;

	    @Autowired
	    public CurrentUserDetailsService(UserService userService) {
	        this.userService = userService;
	    }

	    @Override
	    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
	    	System.out.print("calling userdetails");
	        User user = userService.findByUsername(username)
	        		.orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", username)));
	        return new CurrentUser(user);
	    }
}
