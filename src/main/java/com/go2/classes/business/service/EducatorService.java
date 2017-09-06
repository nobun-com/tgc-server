package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Educator;

public interface EducatorService { 

	Educator findById( Long id  ) ;

	List<Educator> findAll();

	Educator save(Educator entity);

	Educator update(Educator entity);

	Educator create(Educator entity);

	void delete( Long id );

	Educator findByEmail(String email);
	
	Integer getEducatorsCount();
}
