package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.ClassesTimetable;
import com.go2.classes.models.jpa.ClassesTimetableEntity;
import com.go2.classes.business.service.ClassesTimetableService;
import com.go2.classes.business.service.mapping.ClassesTimetableServiceMapper;
import com.go2.classes.data.repository.jpa.ClassesTimetableJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ClassesTimetableServiceImpl implements ClassesTimetableService {

	@Resource
	private ClassesTimetableJpaRepository classesTimetableJpaRepository;

	@Resource
	private ClassesTimetableServiceMapper classesTimetableServiceMapper;
	
	@Override
	public ClassesTimetable findById(Integer id) {
		ClassesTimetableEntity classesTimetableEntity = classesTimetableJpaRepository.findOne(id);
		return classesTimetableServiceMapper.mapClassesTimetableEntityToClassesTimetable(classesTimetableEntity);
	}

	@Override
	public List<ClassesTimetable> findAll() {
		Iterable<ClassesTimetableEntity> entities = classesTimetableJpaRepository.findAll();
		List<ClassesTimetable> beans = new ArrayList<ClassesTimetable>();
		for(ClassesTimetableEntity classesTimetableEntity : entities) {
			beans.add(classesTimetableServiceMapper.mapClassesTimetableEntityToClassesTimetable(classesTimetableEntity));
		}
		return beans;
	}

	@Override
	public ClassesTimetable save(ClassesTimetable classesTimetable) {
		return update(classesTimetable) ;
	}

	@Override
	public ClassesTimetable create(ClassesTimetable classesTimetable) {
		ClassesTimetableEntity classesTimetableEntity = classesTimetableJpaRepository.findOne(classesTimetable.getId());
		if( classesTimetableEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		classesTimetableEntity = new ClassesTimetableEntity();
		classesTimetableServiceMapper.mapClassesTimetableToClassesTimetableEntity(classesTimetable, classesTimetableEntity);
		ClassesTimetableEntity classesTimetableEntitySaved = classesTimetableJpaRepository.save(classesTimetableEntity);
		return classesTimetableServiceMapper.mapClassesTimetableEntityToClassesTimetable(classesTimetableEntitySaved);
	}

	@Override
	public ClassesTimetable update(ClassesTimetable classesTimetable) {
		ClassesTimetableEntity classesTimetableEntity = classesTimetableJpaRepository.findOne(classesTimetable.getId());
		classesTimetableServiceMapper.mapClassesTimetableToClassesTimetableEntity(classesTimetable, classesTimetableEntity);
		ClassesTimetableEntity classesTimetableEntitySaved = classesTimetableJpaRepository.save(classesTimetableEntity);
		return classesTimetableServiceMapper.mapClassesTimetableEntityToClassesTimetable(classesTimetableEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		classesTimetableJpaRepository.delete(id);
	}

	public ClassesTimetableJpaRepository getClassesTimetableJpaRepository() {
		return classesTimetableJpaRepository;
	}

	public void setClassesTimetableJpaRepository(ClassesTimetableJpaRepository classesTimetableJpaRepository) {
		this.classesTimetableJpaRepository = classesTimetableJpaRepository;
	}

	public ClassesTimetableServiceMapper getClassesTimetableServiceMapper() {
		return classesTimetableServiceMapper;
	}

	public void setClassesTimetableServiceMapper(ClassesTimetableServiceMapper classesTimetableServiceMapper) {
		this.classesTimetableServiceMapper = classesTimetableServiceMapper;
	}

}
