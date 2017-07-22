package com.ables.bookbuy.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ables.bookbuy.forms.RegisterForm;
import com.ables.bookbuy.models.Role;
import com.ables.bookbuy.models.User;
import com.ables.bookbuy.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	BCryptPasswordEncoder bcrypt;
	static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}


	@Override
	public List<User> findByFirstName(String fullName) {
		// TODO Auto-generated method stub
		return userRepo.findByFirstName(fullName);
	}

	@Override
	public User saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		
		if (user.getId() == null){
			user.setPassword(bcrypt.encode(user.getPassword()));
			user.setRole(Role.ADMIN);
		
		}
		userRepo.save(user);
		return user;
		
	}

	@Override
	public List<User> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return userRepo.findByLastName(lastName);
	}

	@Override
	public List<?> listAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll(new Sort("username"));
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		userRepo.delete(id);
	}


	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(userRepo.findOne(id));
	}


	

	

}
