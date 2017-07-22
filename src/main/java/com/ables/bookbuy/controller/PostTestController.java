package com.ables.bookbuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ables.bookbuy.forms.PostForm;
import com.ables.bookbuy.models.Post;


@Controller
public class PostTestController {
	
	@RequestMapping(value="/post", method=RequestMethod.GET)
	public String show(Model model, PostForm postForm){
		
		return "testpost";
	}
	
//	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String post(PostForm postForm, Model model, BindingResult bind){
		
		System.out.println("request received "+postForm.getPost());
		 postForm = new PostForm();
		 model.addAttribute("PostForm", postForm);
		
		return "testpost";
	}
}
