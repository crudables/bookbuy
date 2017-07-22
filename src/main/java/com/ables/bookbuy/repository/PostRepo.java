package com.ables.bookbuy.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ables.bookbuy.models.Post;
import com.ables.bookbuy.models.User;



public interface PostRepo extends JpaRepository<Post, Long> {
	List<Post> findByUser(User user);
	
	
	
	
	
	
	
	
	

}
