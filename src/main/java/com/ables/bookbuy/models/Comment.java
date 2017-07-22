package com.ables.bookbuy.models;


import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Comment extends AbstractDomainClass{

	@NotBlank
	private String comment;
	private Long someId;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Post post;

	public Comment(){}
	public Comment(String comment){
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getSomeId() {
		return someId;
	}

	public void setSomeId(Long someId) {
		this.someId = someId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Comment other = (Comment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [body=" + comment + "]";
	}

}
