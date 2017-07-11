package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.ClassesCategory;

public interface ClassesCategoryService { 

	ClassesCategory findById( Long id  ) ;

	List<ClassesCategory> findAll();

	ClassesCategory save(ClassesCategory entity);

	ClassesCategory update(ClassesCategory entity);

	ClassesCategory create(ClassesCategory entity);

	void delete( Long id );

}
