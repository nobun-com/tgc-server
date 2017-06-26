package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Student;

public interface StudentService { 

	Student findById( Long id  ) ;

	List<Student> findAll();

	Student save(Student entity);

	Student update(Student entity);

	Student create(Student entity);

	void delete( Long id );

	Student findByEmail(String email);
}
