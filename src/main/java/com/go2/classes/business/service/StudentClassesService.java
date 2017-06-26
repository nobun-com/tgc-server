package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.StudentClasses;

public interface StudentClassesService { 

	StudentClasses findById( Integer id  ) ;

	List<StudentClasses> findAll();

	StudentClasses save(StudentClasses entity);

	StudentClasses update(StudentClasses entity);

	StudentClasses create(StudentClasses entity);

	void delete( Integer id );
}
