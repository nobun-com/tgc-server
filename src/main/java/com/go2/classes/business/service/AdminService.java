package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Admin;

public interface AdminService { 

	Admin findById( Long id  ) ;

	List<Admin> findAll();

	Admin save(Admin entity);

	Admin update(Admin entity);

	Admin create(Admin entity);

	void delete( Long id );

	Admin findByEmail(String email);
}
