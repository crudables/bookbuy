package com.ables.bookbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.ables.bookbuy.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login( ){
		return "login";
	}

}
