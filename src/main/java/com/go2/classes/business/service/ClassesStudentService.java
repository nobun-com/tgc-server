package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.ClassesStudent;

public interface ClassesStudentService { 

	ClassesStudent findById( Integer id  ) ;

	List<ClassesStudent> findAll();

	ClassesStudent save(ClassesStudent entity);

	ClassesStudent update(ClassesStudent entity);

	ClassesStudent create(ClassesStudent entity);

	void delete( Integer id );
}
