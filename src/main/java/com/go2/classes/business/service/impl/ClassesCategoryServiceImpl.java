package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.go2.classes.business.service.ClassesCategoryService;
import com.go2.classes.business.service.mapping.ClassesCategoryServiceMapper;
import com.go2.classes.data.repository.jpa.ClassesCategoryJpaRepository;
import com.go2.classes.models.ClassesCategory;
import com.go2.classes.models.jpa.ClassesCategoryEntity;

@Component
@Transactional
public class ClassesCategoryServiceImpl implements ClassesCategoryService {

	@Resource
	private ClassesCategoryJpaRepository classesCategoryJpaRepository;

	@Resource
	private ClassesCategoryServiceMapper classesCategoryServiceMapper;
	
	@Override
	public ClassesCategory findById(Long id) {
		ClassesCategoryEntity classesCategoryEntity = classesCategoryJpaRepository.findOne(id);
		return classesCategoryServiceMapper.mapClassesCategoryEntityToClassesCategory(classesCategoryEntity);
	}

	@Override
	public List<ClassesCategory> findAll() {
		Iterable<ClassesCategoryEntity> entities = classesCategoryJpaRepository.findAll();
		List<ClassesCategory> beans = new ArrayList<ClassesCategory>();
		for(ClassesCategoryEntity classesCategoryEntity : entities) {
			beans.add(classesCategoryServiceMapper.mapClassesCategoryEntityToClassesCategory(classesCategoryEntity));
		}
		return beans;
	}

	@Override
	public ClassesCategory save(ClassesCategory classesCategory) {
		return update(classesCategory) ;
	}

	@Override
	public ClassesCategory create(ClassesCategory classesCategory) {
		ClassesCategoryEntity classesCategoryEntity = null;
		if ( !Objects.isNull(classesCategory.getId()) ) {
			classesCategoryEntity = classesCategoryJpaRepository.findOne(classesCategory.getId());		}
		if ( !Objects.isNull(classesCategoryEntity) ) {
			throw new IllegalStateException("already.exists");
		}
		classesCategoryEntity = new ClassesCategoryEntity();
		classesCategoryServiceMapper.mapClassesCategoryToClassesCategoryEntity(classesCategory, classesCategoryEntity);
		ClassesCategoryEntity classesCategoryEntitySaved = classesCategoryJpaRepository.save(classesCategoryEntity);
		return classesCategoryServiceMapper.mapClassesCategoryEntityToClassesCategory(classesCategoryEntitySaved);
	}

	@Override
	public ClassesCategory update(ClassesCategory classesCategory) {
		ClassesCategoryEntity classesCategoryEntity = classesCategoryJpaRepository.findOne(classesCategory.getId());
		classesCategoryServiceMapper.mapClassesCategoryToClassesCategoryEntity(classesCategory, classesCategoryEntity);
		ClassesCategoryEntity classesCategoryEntitySaved = classesCategoryJpaRepository.save(classesCategoryEntity);
		return classesCategoryServiceMapper.mapClassesCategoryEntityToClassesCategory(classesCategoryEntitySaved);
	}

	@Override
	public void delete(Long id) {
		classesCategoryJpaRepository.delete(id);
	}

	public ClassesCategoryJpaRepository getClassesCategoryJpaRepository() {
		return classesCategoryJpaRepository;
	}

	public void setClassesCategoryJpaRepository(ClassesCategoryJpaRepository classesCategoryJpaRepository) {
		this.classesCategoryJpaRepository = classesCategoryJpaRepository;
	}

	public ClassesCategoryServiceMapper getClassesCategoryServiceMapper() {
		return classesCategoryServiceMapper;
	}

	public void setClassesCategoryServiceMapper(ClassesCategoryServiceMapper classesCategoryServiceMapper) {
		this.classesCategoryServiceMapper = classesCategoryServiceMapper;
	}

	
	/*@Override
	public List<ClassesCategory> getAllClassesCategorysByUser(Long userId) {
		Iterable<ClassesCategoryEntity> entities = classesCategoryJpaRepository.findAllClassesCategorysByUserId(userId);
		List<ClassesCategory> beans = new ArrayList<ClassesCategory>();
		for(ClassesCategoryEntity classesCategoryEntity : entities) {
			beans.add(classesCategoryServiceMapper.mapClassesCategoryEntityToClassesCategory(classesCategoryEntity));
		}
		return beans;
	}*/

}
