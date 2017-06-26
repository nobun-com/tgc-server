package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.ClassesTeacher;

public interface ClassesTeacherService { 

	ClassesTeacher findById( Integer id  ) ;

	List<ClassesTeacher> findAll();

	ClassesTeacher save(ClassesTeacher entity);

	ClassesTeacher update(ClassesTeacher entity);

	ClassesTeacher create(ClassesTeacher entity);

	void delete( Integer id );
}
