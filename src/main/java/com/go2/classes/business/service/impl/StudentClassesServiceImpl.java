package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.StudentClasses;
import com.go2.classes.models.jpa.StudentClassesEntity;
import com.go2.classes.business.service.StudentClassesService;
import com.go2.classes.business.service.mapping.StudentClassesServiceMapper;
import com.go2.classes.data.repository.jpa.StudentClassesJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class StudentClassesServiceImpl implements StudentClassesService {

	@Resource
	private StudentClassesJpaRepository studentClassesJpaRepository;

	@Resource
	private StudentClassesServiceMapper studentClassesServiceMapper;
	
	@Override
	public StudentClasses findById(Integer id) {
		StudentClassesEntity studentClassesEntity = studentClassesJpaRepository.findOne(id);
		return studentClassesServiceMapper.mapStudentClassesEntityToStudentClasses(studentClassesEntity);
	}

	@Override
	public List<StudentClasses> findAll() {
		Iterable<StudentClassesEntity> entities = studentClassesJpaRepository.findAll();
		List<StudentClasses> beans = new ArrayList<StudentClasses>();
		for(StudentClassesEntity studentClassesEntity : entities) {
			beans.add(studentClassesServiceMapper.mapStudentClassesEntityToStudentClasses(studentClassesEntity));
		}
		return beans;
	}

	@Override
	public StudentClasses save(StudentClasses studentClasses) {
		return update(studentClasses) ;
	}

	@Override
	public StudentClasses create(StudentClasses studentClasses) {
		StudentClassesEntity studentClassesEntity = studentClassesJpaRepository.findOne(studentClasses.getId());
		if( studentClassesEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		studentClassesEntity = new StudentClassesEntity();
		studentClassesServiceMapper.mapStudentClassesToStudentClassesEntity(studentClasses, studentClassesEntity);
		StudentClassesEntity studentClassesEntitySaved = studentClassesJpaRepository.save(studentClassesEntity);
		return studentClassesServiceMapper.mapStudentClassesEntityToStudentClasses(studentClassesEntitySaved);
	}

	@Override
	public StudentClasses update(StudentClasses studentClasses) {
		StudentClassesEntity studentClassesEntity = studentClassesJpaRepository.findOne(studentClasses.getId());
		studentClassesServiceMapper.mapStudentClassesToStudentClassesEntity(studentClasses, studentClassesEntity);
		StudentClassesEntity studentClassesEntitySaved = studentClassesJpaRepository.save(studentClassesEntity);
		return studentClassesServiceMapper.mapStudentClassesEntityToStudentClasses(studentClassesEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		studentClassesJpaRepository.delete(id);
	}

	public StudentClassesJpaRepository getStudentClassesJpaRepository() {
		return studentClassesJpaRepository;
	}

	public void setStudentClassesJpaRepository(StudentClassesJpaRepository studentClassesJpaRepository) {
		this.studentClassesJpaRepository = studentClassesJpaRepository;
	}

	public StudentClassesServiceMapper getStudentClassesServiceMapper() {
		return studentClassesServiceMapper;
	}

	public void setStudentClassesServiceMapper(StudentClassesServiceMapper studentClassesServiceMapper) {
		this.studentClassesServiceMapper = studentClassesServiceMapper;
	}

}
