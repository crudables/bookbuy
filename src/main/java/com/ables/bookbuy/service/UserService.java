package com.ables.bookbuy.service;

import java.util.Collection;
import java.util.Optional;

import com.ables.bookbuy.forms.RegisterForm;
import com.ables.bookbuy.models.User;
public interface UserService extends CrudServices<User>{
	Optional<User> findById(Long id);
	Optional<User> findByUsername(String username);
	Collection<User> findByFirstName(String firstName);
	Collection<User> findByLastName(String lastName);

}
