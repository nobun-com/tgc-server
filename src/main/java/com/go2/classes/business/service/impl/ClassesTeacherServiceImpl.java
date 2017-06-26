package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.ClassesTeacher;
import com.go2.classes.models.jpa.ClassesTeacherEntity;
import com.go2.classes.business.service.ClassesTeacherService;
import com.go2.classes.business.service.mapping.ClassesTeacherServiceMapper;
import com.go2.classes.data.repository.jpa.ClassesTeacherJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ClassesTeacherServiceImpl implements ClassesTeacherService {

	@Resource
	private ClassesTeacherJpaRepository classesTeacherJpaRepository;

	@Resource
	private ClassesTeacherServiceMapper classesTeacherServiceMapper;
	
	@Override
	public ClassesTeacher findById(Integer id) {
		ClassesTeacherEntity classesTeacherEntity = classesTeacherJpaRepository.findOne(id);
		return classesTeacherServiceMapper.mapClassesTeacherEntityToClassesTeacher(classesTeacherEntity);
	}

	@Override
	public List<ClassesTeacher> findAll() {
		Iterable<ClassesTeacherEntity> entities = classesTeacherJpaRepository.findAll();
		List<ClassesTeacher> beans = new ArrayList<ClassesTeacher>();
		for(ClassesTeacherEntity classesTeacherEntity : entities) {
			beans.add(classesTeacherServiceMapper.mapClassesTeacherEntityToClassesTeacher(classesTeacherEntity));
		}
		return beans;
	}

	@Override
	public ClassesTeacher save(ClassesTeacher classesTeacher) {
		return update(classesTeacher) ;
	}

	@Override
	public ClassesTeacher create(ClassesTeacher classesTeacher) {
		ClassesTeacherEntity classesTeacherEntity = classesTeacherJpaRepository.findOne(classesTeacher.getId());
		if( classesTeacherEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		classesTeacherEntity = new ClassesTeacherEntity();
		classesTeacherServiceMapper.mapClassesTeacherToClassesTeacherEntity(classesTeacher, classesTeacherEntity);
		ClassesTeacherEntity classesTeacherEntitySaved = classesTeacherJpaRepository.save(classesTeacherEntity);
		return classesTeacherServiceMapper.mapClassesTeacherEntityToClassesTeacher(classesTeacherEntitySaved);
	}

	@Override
	public ClassesTeacher update(ClassesTeacher classesTeacher) {
		ClassesTeacherEntity classesTeacherEntity = classesTeacherJpaRepository.findOne(classesTeacher.getId());
		classesTeacherServiceMapper.mapClassesTeacherToClassesTeacherEntity(classesTeacher, classesTeacherEntity);
		ClassesTeacherEntity classesTeacherEntitySaved = classesTeacherJpaRepository.save(classesTeacherEntity);
		return classesTeacherServiceMapper.mapClassesTeacherEntityToClassesTeacher(classesTeacherEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		classesTeacherJpaRepository.delete(id);
	}

	public ClassesTeacherJpaRepository getClassesTeacherJpaRepository() {
		return classesTeacherJpaRepository;
	}

	public void setClassesTeacherJpaRepository(ClassesTeacherJpaRepository classesTeacherJpaRepository) {
		this.classesTeacherJpaRepository = classesTeacherJpaRepository;
	}

	public ClassesTeacherServiceMapper getClassesTeacherServiceMapper() {
		return classesTeacherServiceMapper;
	}

	public void setClassesTeacherServiceMapper(ClassesTeacherServiceMapper classesTeacherServiceMapper) {
		this.classesTeacherServiceMapper = classesTeacherServiceMapper;
	}

}
