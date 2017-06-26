package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.ClassesTimetable;

public interface ClassesTimetableService { 

	ClassesTimetable findById( Integer id  ) ;

	List<ClassesTimetable> findAll();

	ClassesTimetable save(ClassesTimetable entity);

	ClassesTimetable update(ClassesTimetable entity);

	ClassesTimetable create(ClassesTimetable entity);

	void delete( Integer id );
}
