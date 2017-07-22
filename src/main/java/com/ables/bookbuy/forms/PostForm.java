package com.ables.bookbuy.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
@Component
public class PostForm {
	
	@NotBlank
 	private String post;
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
}
