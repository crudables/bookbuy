package com.ables.bookbuy.models;

import java.util.List;

import javax.persistence.*;


import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Post extends AbstractDomainClass{
	@NotBlank
	@Column(name="post")
	private String post;
	@ManyToOne
	private User user;
	
	
	public Post(){}
	public Post(String post){
		this.post = post;
	}
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments;

	
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment){
		if(!this.comments.contains(comment)){
			this.comments.add(comment);
		}
	}
	
	public void removeComment(Comment comment){
		this.comments.remove(comment);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [body=" + post + "]";
	}

	
}
