package com.ables.bookbuy.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class CommentForm {
	@NotBlank
	String body;
	Long someId;
	public Long getSomeId() {
		return someId;
	}

	public void setSomeId(Long someId) {
		this.someId = someId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	

}
