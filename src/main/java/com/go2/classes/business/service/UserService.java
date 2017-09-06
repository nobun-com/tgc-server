package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.User;

public interface UserService { 

	User findById( Long id  ) ;

	List<User> findAll();

	User save(User entity);

	User update(User entity);

	User create(User entity);

	void delete( Long id );

	User findByEmail(String email);
}
