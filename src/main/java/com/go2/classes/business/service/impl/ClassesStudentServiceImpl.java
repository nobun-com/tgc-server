package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.ClassesStudent;
import com.go2.classes.models.jpa.ClassesStudentEntity;
import com.go2.classes.business.service.ClassesStudentService;
import com.go2.classes.business.service.mapping.ClassesStudentServiceMapper;
import com.go2.classes.data.repository.jpa.ClassesStudentJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ClassesStudentServiceImpl implements ClassesStudentService {

	@Resource
	private ClassesStudentJpaRepository classesStudentJpaRepository;

	@Resource
	private ClassesStudentServiceMapper classesStudentServiceMapper;
	
	@Override
	public ClassesStudent findById(Integer id) {
		ClassesStudentEntity classesStudentEntity = classesStudentJpaRepository.findOne(id);
		return classesStudentServiceMapper.mapClassesStudentEntityToClassesStudent(classesStudentEntity);
	}

	@Override
	public List<ClassesStudent> findAll() {
		Iterable<ClassesStudentEntity> entities = classesStudentJpaRepository.findAll();
		List<ClassesStudent> beans = new ArrayList<ClassesStudent>();
		for(ClassesStudentEntity classesStudentEntity : entities) {
			beans.add(classesStudentServiceMapper.mapClassesStudentEntityToClassesStudent(classesStudentEntity));
		}
		return beans;
	}

	@Override
	public ClassesStudent save(ClassesStudent classesStudent) {
		return update(classesStudent) ;
	}

	@Override
	public ClassesStudent create(ClassesStudent classesStudent) {
		ClassesStudentEntity classesStudentEntity = classesStudentJpaRepository.findOne(classesStudent.getId());
		if( classesStudentEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		classesStudentEntity = new ClassesStudentEntity();
		classesStudentServiceMapper.mapClassesStudentToClassesStudentEntity(classesStudent, classesStudentEntity);
		ClassesStudentEntity classesStudentEntitySaved = classesStudentJpaRepository.save(classesStudentEntity);
		return classesStudentServiceMapper.mapClassesStudentEntityToClassesStudent(classesStudentEntitySaved);
	}

	@Override
	public ClassesStudent update(ClassesStudent classesStudent) {
		ClassesStudentEntity classesStudentEntity = classesStudentJpaRepository.findOne(classesStudent.getId());
		classesStudentServiceMapper.mapClassesStudentToClassesStudentEntity(classesStudent, classesStudentEntity);
		ClassesStudentEntity classesStudentEntitySaved = classesStudentJpaRepository.save(classesStudentEntity);
		return classesStudentServiceMapper.mapClassesStudentEntityToClassesStudent(classesStudentEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		classesStudentJpaRepository.delete(id);
	}

	public ClassesStudentJpaRepository getClassesStudentJpaRepository() {
		return classesStudentJpaRepository;
	}

	public void setClassesStudentJpaRepository(ClassesStudentJpaRepository classesStudentJpaRepository) {
		this.classesStudentJpaRepository = classesStudentJpaRepository;
	}

	public ClassesStudentServiceMapper getClassesStudentServiceMapper() {
		return classesStudentServiceMapper;
	}

	public void setClassesStudentServiceMapper(ClassesStudentServiceMapper classesStudentServiceMapper) {
		this.classesStudentServiceMapper = classesStudentServiceMapper;
	}

}
