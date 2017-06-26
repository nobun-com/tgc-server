package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.TeacherClasses;

public interface TeacherClassesService { 

	TeacherClasses findById( Integer id  ) ;

	List<TeacherClasses> findAll();

	TeacherClasses save(TeacherClasses entity);

	TeacherClasses update(TeacherClasses entity);

	TeacherClasses create(TeacherClasses entity);

	void delete( Integer id );
}
