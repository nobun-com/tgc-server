package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.UserCart;

public interface UserCartService { 

	UserCart findById( Long id  ) ;

	List<UserCart> findAll();

	UserCart save(UserCart entity);

	UserCart update(UserCart entity);

	UserCart create(UserCart entity);

	void delete( Long id );

	List<UserCart> findAllClassInstancesByStudent(Long userId);

	Double getToatlFees(Long userId);

}
