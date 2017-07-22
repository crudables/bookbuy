package com.ables.bookbuy.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ables.bookbuy.forms.CommentForm;
import com.ables.bookbuy.forms.PostForm;
import com.ables.bookbuy.models.Comment;
import com.ables.bookbuy.models.Post;
import com.ables.bookbuy.service.PostService;
import com.ables.bookbuy.service.UserService;

@SessionAttributes(value="user")
@Controller
public class Postcontroller {

@Autowired
PostService impl;
@Autowired
UserService userService;
Comment postComment;
Comment com;
private static Logger logger = LoggerFactory.getLogger(Postcontroller.class);


@RequestMapping("/posts/view/{id}")
public String view(@PathVariable("id") Long id, Model model){
	System.out.println("comment request got here");
	Post post = impl.findById(id).get();
	
	model.addAttribute("post", post);
	return "posts/view";
}

@RequestMapping(value="/posts/comment", method=RequestMethod.POST)
public String commentForPost(@Valid CommentForm comment,  Model model, BindingResult bind ){
	System.out.println("we out here");	
	Post post = impl.findById(comment.getSomeId()).get();
	com = new Comment(comment.getBody());
	com.setPost(post);
	post.getComments().add(com);
	impl.saveOrUpdate(post);
	 
	postComment = new Comment();
	model.addAttribute("allpost", impl.listAll());
	model.addAttribute("postComment", postComment);
	return "redirect:/";
}

@RequestMapping(method=RequestMethod.POST, value="/post")
public String post(@Valid PostForm postForm, BindingResult bind, Model model){
	System.out.println("Post Received "+postForm.getPost());
//	System.out.println("user ius "+principal.getName());
	List<Post> allpost =  (List<Post>) impl.listAll();
	Post post = new Post(postForm.getPost());
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	post.setUser(userService.findByUsername(auth.getName()).get());
	impl.saveOrUpdate(post);
	allpost.add(post);
	allpost.sort((a,b) ->b.getDateCreated().compareTo(a.getDateCreated()));
	postComment = new Comment();
	model.addAttribute("postComment", postComment);
	logger.info(post.getDateCreated().toString()+" is date created");
	System.out.println("recieved post request");
	return "redirect:/";
}
 
}
