package com.ables.bookbuy.service;


import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ables.bookbuy.models.Post;
import com.ables.bookbuy.models.User;
import com.ables.bookbuy.repository.PostRepo;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostRepo postRepo;
	
	private static Logger logger = LoggerFactory.getLogger("PostServiceImpl");

	@Override
	public List<Post> findByUser(User author) {
		return postRepo.findByUser(author);
	}

	


@Override
public List<Post> listAll() {
	// TODO Auto-generated method stub
	List<Post> posts = postRepo.findAll();
	logger.info("Returned "+posts.size() +" posts");
	return posts;
}

@Override
public Post saveOrUpdate(Post post) {
	// TODO Auto-generated method stub
	return postRepo.save(post);
}



@Override
public void delete(Long id) {
	// TODO Auto-generated method stub
	postRepo.delete(id);
	
}



@Override
public Optional<Post> findById(Long id) {
	// TODO Auto-generated method stub
	return Optional.of(postRepo.findOne(id));
}


}
