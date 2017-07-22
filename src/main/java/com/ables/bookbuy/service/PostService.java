package com.ables.bookbuy.service;

import java.util.Collection;
import java.util.Optional;

import com.ables.bookbuy.models.Post;
import com.ables.bookbuy.models.User;

public interface PostService extends CrudServices<Post> {
	Optional<Post> findById(Long id);
	public Collection<Post> findByUser(User author);
//	public Collection<Post> findByDate(LocalDateTime date);
//	public Post editPost(Post post);
	
}
