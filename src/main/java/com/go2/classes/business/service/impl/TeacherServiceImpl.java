package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.Teacher;
import com.go2.classes.models.jpa.TeacherEntity;
import com.go2.classes.business.service.TeacherService;
import com.go2.classes.business.service.mapping.TeacherServiceMapper;
import com.go2.classes.data.repository.jpa.TeacherJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private TeacherJpaRepository teacherJpaRepository;

	@Resource
	private TeacherServiceMapper teacherServiceMapper;
	
	@Override
	public Teacher findById(Long id) {
		TeacherEntity teacherEntity = teacherJpaRepository.findOne(id);
		if(Objects.isNull(teacherEntity)) {
			throw new IllegalStateException("teacher.not.found");
		}
		return teacherServiceMapper.mapTeacherEntityToTeacher(teacherEntity);
	}

	@Override
	public Teacher findByEmail(String email) {
		TeacherEntity teacherEntity = teacherJpaRepository.findByEmail(email);
		if(Objects.isNull(teacherEntity)) {
			return null;
		}
		return teacherServiceMapper.mapTeacherEntityToTeacher(teacherEntity);
	}


	@Override
	public List<Teacher> findAll() {
		Iterable<TeacherEntity> entities = teacherJpaRepository.findAll();
		List<Teacher> beans = new ArrayList<Teacher>();
		for(TeacherEntity teacherEntity : entities) {
			beans.add(teacherServiceMapper.mapTeacherEntityToTeacher(teacherEntity));
		}
		return beans;
	}

	@Override
	public Teacher save(Teacher teacher) {
		return update(teacher) ;
	}

	@Override
	public Teacher create(Teacher teacher) {
		TeacherEntity teacherEntity = null;
		if(!Objects.isNull(teacher.getId())) {
			teacherEntity = teacherJpaRepository.findOne(teacher.getId());
		}
		if(!Objects.isNull(teacherEntity)) {
			throw new IllegalStateException("teacher.already.exists");
		}
		teacherEntity = new TeacherEntity();
		teacherServiceMapper.mapTeacherToTeacherEntity(teacher, teacherEntity);
		TeacherEntity teacherEntitySaved = teacherJpaRepository.save(teacherEntity);
		return teacherServiceMapper.mapTeacherEntityToTeacher(teacherEntitySaved);
	}

	@Override
	public Teacher update(Teacher teacher) {
		TeacherEntity teacherEntity = null;
		if(!Objects.isNull(teacher.getId())) {
			teacherEntity = teacherJpaRepository.findOne(teacher.getId());
		}
		if(Objects.isNull(teacherEntity)) {
			throw new IllegalStateException("teacher.not.found");
		}
		teacherServiceMapper.mapTeacherToTeacherEntity(teacher, teacherEntity);
		TeacherEntity teacherEntitySaved = teacherJpaRepository.save(teacherEntity);
		return teacherServiceMapper.mapTeacherEntityToTeacher(teacherEntitySaved);
	}

	@Override
	public void delete(Long id) {
		if(Objects.isNull(id)) {
			throw new IllegalStateException("teacher.not.found");
		}
		teacherJpaRepository.delete(id);
	}

	public TeacherJpaRepository getTeacherJpaRepository() {
		return teacherJpaRepository;
	}

	public void setTeacherJpaRepository(TeacherJpaRepository teacherJpaRepository) {
		this.teacherJpaRepository = teacherJpaRepository;
	}

	public TeacherServiceMapper getTeacherServiceMapper() {
		return teacherServiceMapper;
	}

	public void setTeacherServiceMapper(TeacherServiceMapper teacherServiceMapper) {
		this.teacherServiceMapper = teacherServiceMapper;
	}

	@Override
	public Integer getTeachersCount() {
		return teacherJpaRepository.getTeachersCount();
	}

}
