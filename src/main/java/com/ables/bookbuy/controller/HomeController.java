package com.ables.bookbuy.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ables.bookbuy.forms.CommentForm;
import com.ables.bookbuy.forms.PostForm;
//import com.ables.bookbuy.models.Comment;
import com.ables.bookbuy.models.Post;
import com.ables.bookbuy.models.User;
import com.ables.bookbuy.service.PostServiceImpl;
import com.ables.bookbuy.service.UserService;

@SessionAttributes(value="user")
@Controller
public class HomeController {
	@Autowired
	PostServiceImpl imp;
	
	@Autowired
	UserService userService;
	@Autowired
	CommentForm comment;
	@Autowired
	PostForm postForm;
	
	User user;
	
	
	Post post;
	Logger logger = Logger.getLogger(getClass());
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(Model model){
		
		
		List<Post> allPost = imp.listAll();
		allPost.sort((a,b)->b.getDateCreated().compareTo(a.getDateCreated()));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken))
		user = userService.findByUsername(auth.getName()).get();
		model.addAttribute("allpost", allPost);
		
		model.addAttribute("user", user);
		model.addAttribute("comment",comment);
		model.addAttribute("postForm", postForm);
		return "index";
	}
	
}
