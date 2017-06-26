package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Classes;

public interface ClassesService { 

	Classes findById( Long id  ) ;

	List<Classes> findAll();

	Classes save(Classes entity);

	Classes update(Classes entity);

	Classes create(Classes entity);

	void delete( Long id );

	List<Classes> getAllClassesByCenter(Long centerId);
}
