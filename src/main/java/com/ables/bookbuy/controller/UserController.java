package com.ables.bookbuy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ables.bookbuy.forms.RegisterForm;
//import com.ables.bookbuy.auth.service.SecurityService;
//import com.ables.bookbuy.auth.service.validator.UserValidator;
import com.ables.bookbuy.models.User;
import com.ables.bookbuy.service.UserService;
import com.ables.bookbuy.validator.UserValidator;

@SessionAttributes(value="user")
@Controller
public class UserController {
	@Autowired
	private UserService service;
//	@Autowired
//	private SecurityService securityService;
	@Autowired
	private UserValidator userValidator;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegisterForm(Model model,RegisterForm registerForm){
		System.out.println("Showing reg form");
//		model.addAttribute("user", new User());
		logger.info("showing register form");
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register( RegisterForm registerForm, Model model, BindingResult bind){

	        if (bind.hasErrors()) {
	        	logger.error(bind.toString());
	        	logger.info("There was an error");
	        	model.addAttribute("registerForm", new RegisterForm());
	            return "register";
	        }
	        User user = new User();
	        user.setUsername(registerForm.getUsername());
	        user.setFirstName(registerForm.getFirstName());
	        user.setLastName(registerForm.getLastName());
	        user.setEmail(registerForm.getEmail());
	        user.setPassword(registerForm.getPassword());
	        user.setEnabled(true);
	        service.saveOrUpdate(user);


	        return "registered";
	}
	
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	 public String user(){
		
		 return "user";
	 }
	
	@GetMapping("/upload")
	public String upload(){
		return "upload";
	}
}
