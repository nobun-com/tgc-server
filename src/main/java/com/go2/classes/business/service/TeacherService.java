package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Teacher;

public interface TeacherService { 

	Teacher findById( Long id  ) ;

	List<Teacher> findAll();

	Teacher save(Teacher entity);

	Teacher update(Teacher entity);

	Teacher create(Teacher entity);

	void delete( Long id );

	Teacher findByEmail(String email);
	
	Integer getTeachersCount();
}
