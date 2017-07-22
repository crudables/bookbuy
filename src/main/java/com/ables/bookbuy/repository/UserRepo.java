package com.ables.bookbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ables.bookbuy.models.User;


public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	List<User> findByFirstName(String name);
	List<User> findByLastName(String lastName);
	
}
