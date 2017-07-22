package com.ables.bookbuy.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ables.bookbuy.models.Comment;
import com.ables.bookbuy.models.Post;
import com.ables.bookbuy.models.User;

public interface CommentRepo extends JpaRepository<Comment, Long>{
List<Comment> findByUser(User user);
List<Comment> findByPost(Post post);
List<Comment> findByDateCreated(LocalDateTime dateCreated);
}
