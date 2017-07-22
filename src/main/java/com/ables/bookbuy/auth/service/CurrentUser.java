package com.ables.bookbuy.auth.service;

import org.springframework.security.core.authority.AuthorityUtils;

import com.ables.bookbuy.models.Role;
import com.ables.bookbuy.models.User;



public class CurrentUser extends org.springframework.security.core.userdetails.User {

	 private User user;

	    public CurrentUser(User user) {
	        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
	        this.user = user;
	    }

	    public User getUser() {
	        return user;
	    }

	    public Long getId() {
	        return user.getId();
	    }

	    public Role getRole() {
	        return user.getRole();
	    }
}
