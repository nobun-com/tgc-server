package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Child;

public interface ChildService { 

	Child findById( Long id  ) ;

	List<Child> findAll();

	Child save(Child entity);

	Child update(Child entity);

	Child create(Child entity);

	void delete( Long id );
	
	List<Child> getAllChildsByUser(Long userId);

}
