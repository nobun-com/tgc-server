package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.TeacherClasses;
import com.go2.classes.models.jpa.TeacherClassesEntity;
import com.go2.classes.business.service.TeacherClassesService;
import com.go2.classes.business.service.mapping.TeacherClassesServiceMapper;
import com.go2.classes.data.repository.jpa.TeacherClassesJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TeacherClassesServiceImpl implements TeacherClassesService {

	@Resource
	private TeacherClassesJpaRepository teacherClassesJpaRepository;

	@Resource
	private TeacherClassesServiceMapper teacherClassesServiceMapper;
	
	@Override
	public TeacherClasses findById(Integer id) {
		TeacherClassesEntity teacherClassesEntity = teacherClassesJpaRepository.findOne(id);
		return teacherClassesServiceMapper.mapTeacherClassesEntityToTeacherClasses(teacherClassesEntity);
	}

	@Override
	public List<TeacherClasses> findAll() {
		Iterable<TeacherClassesEntity> entities = teacherClassesJpaRepository.findAll();
		List<TeacherClasses> beans = new ArrayList<TeacherClasses>();
		for(TeacherClassesEntity teacherClassesEntity : entities) {
			beans.add(teacherClassesServiceMapper.mapTeacherClassesEntityToTeacherClasses(teacherClassesEntity));
		}
		return beans;
	}

	@Override
	public TeacherClasses save(TeacherClasses teacherClasses) {
		return update(teacherClasses) ;
	}

	@Override
	public TeacherClasses create(TeacherClasses teacherClasses) {
		TeacherClassesEntity teacherClassesEntity = teacherClassesJpaRepository.findOne(teacherClasses.getId());
		if( teacherClassesEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		teacherClassesEntity = new TeacherClassesEntity();
		teacherClassesServiceMapper.mapTeacherClassesToTeacherClassesEntity(teacherClasses, teacherClassesEntity);
		TeacherClassesEntity teacherClassesEntitySaved = teacherClassesJpaRepository.save(teacherClassesEntity);
		return teacherClassesServiceMapper.mapTeacherClassesEntityToTeacherClasses(teacherClassesEntitySaved);
	}

	@Override
	public TeacherClasses update(TeacherClasses teacherClasses) {
		TeacherClassesEntity teacherClassesEntity = teacherClassesJpaRepository.findOne(teacherClasses.getId());
		teacherClassesServiceMapper.mapTeacherClassesToTeacherClassesEntity(teacherClasses, teacherClassesEntity);
		TeacherClassesEntity teacherClassesEntitySaved = teacherClassesJpaRepository.save(teacherClassesEntity);
		return teacherClassesServiceMapper.mapTeacherClassesEntityToTeacherClasses(teacherClassesEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		teacherClassesJpaRepository.delete(id);
	}

	public TeacherClassesJpaRepository getTeacherClassesJpaRepository() {
		return teacherClassesJpaRepository;
	}

	public void setTeacherClassesJpaRepository(TeacherClassesJpaRepository teacherClassesJpaRepository) {
		this.teacherClassesJpaRepository = teacherClassesJpaRepository;
	}

	public TeacherClassesServiceMapper getTeacherClassesServiceMapper() {
		return teacherClassesServiceMapper;
	}

	public void setTeacherClassesServiceMapper(TeacherClassesServiceMapper teacherClassesServiceMapper) {
		this.teacherClassesServiceMapper = teacherClassesServiceMapper;
	}

}
