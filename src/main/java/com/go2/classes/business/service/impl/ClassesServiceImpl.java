package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.Classes;
import com.go2.classes.models.jpa.ClassesCategoryEntity;
import com.go2.classes.models.jpa.ClassesEntity;
import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.impl.helper.ClassMetadataHelper;
import com.go2.classes.business.service.mapping.ClassesServiceMapper;
import com.go2.classes.data.repository.jpa.ClassesCategoryJpaRepository;
import com.go2.classes.data.repository.jpa.ClassesJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ClassesServiceImpl implements ClassesService {

	@Resource
	private ClassesJpaRepository classesJpaRepository;

	@Resource
	private ClassesCategoryJpaRepository classesCategoryJpaRepository;

	@Resource
	private ClassMetadataHelper classMetadataHelper;

	@Resource
	private ClassesServiceMapper classesServiceMapper;
	
	@Override
	public Classes findById(Long id) {
		ClassesEntity classesEntity = classesJpaRepository.findOne(id);
		if(Objects.isNull(classesEntity)) {
			throw new IllegalStateException("class.not.found");
		}
		return classesServiceMapper.mapClassesEntityToClasses(classesEntity);
	}

	@Override
	public List<Classes> findAll() {
		Iterable<ClassesEntity> entities = classesJpaRepository.findAll();
		List<Classes> beans = new ArrayList<Classes>();
		for(ClassesEntity classesEntity : entities) {
			beans.add(classesServiceMapper.mapClassesEntityToClasses(classesEntity));
		}
		return beans;
	}

	@Override
	public List<Classes> getAllClassesByCenter(Long centerId) {
		Iterable<ClassesEntity> entities = classesJpaRepository.findAllClassesByCenterId(centerId);
		List<Classes> beans = new ArrayList<Classes>();
		for(ClassesEntity classesEntity : entities) {
			beans.add(classesServiceMapper.mapClassesEntityToClasses(classesEntity));
		}
		return beans;
	}

	@Override
	public List<Classes> getAllClassesByTeacher(Long teacherId) {
		Iterable<ClassesEntity> entities = classesJpaRepository.findAllClassesByTeacherId(teacherId);
		List<Classes> beans = new ArrayList<Classes>();
		for(ClassesEntity classesEntity : entities) {
			beans.add(classesServiceMapper.mapClassesEntityToClasses(classesEntity));
		}
		return beans;
	}

	@Override
	public Classes save(Classes classes) {
		return update(classes) ;
	}

	@Override
	public Classes create(Classes classes) {
		ClassesEntity classesEntity = null;
		if(!Objects.isNull(classes.getId())) {
			classesEntity = classesJpaRepository.findOne(classes.getId());
		}
		if(!Objects.isNull(classesEntity)) {
			throw new IllegalStateException("class.already.exists");
		}
		classesEntity = new ClassesEntity();
		classesServiceMapper.mapClassesToClassesEntity(classes, classesEntity);
		ClassesEntity classesEntitySaved = classesJpaRepository.save(classesEntity);
		Classes classesSaved = classesServiceMapper.mapClassesEntityToClasses(classesEntitySaved);
		classMetadataHelper.parseClassesMetaData(classesSaved);
		return classesSaved;
	}

	@Override
	public Classes update(Classes classes) {
		ClassesEntity classesEntity = null;
		if(!Objects.isNull(classes.getId())) {
			classesEntity = classesJpaRepository.findOne(classes.getId());
		}
		if(Objects.isNull(classesEntity)) {
			throw new IllegalStateException("class.not.found");
		}
		classesServiceMapper.mapClassesToClassesEntity(classes, classesEntity);
		ClassesEntity classesEntitySaved = classesJpaRepository.save(classesEntity);
		return classesServiceMapper.mapClassesEntityToClasses(classesEntitySaved);
	}

	@Override
	public void delete(Long id) {
		ClassesEntity classesEntity = null;
		if(!Objects.isNull(id)) {
			classesEntity = classesJpaRepository.findOne(id);
		}
		if(Objects.isNull(classesEntity)) {
			throw new IllegalStateException("class.not.found");
		}
		classesJpaRepository.delete(id);
	}

	public ClassesJpaRepository getClassesJpaRepository() {
		return classesJpaRepository;
	}

	public void setClassesJpaRepository(ClassesJpaRepository classesJpaRepository) {
		this.classesJpaRepository = classesJpaRepository;
	}

	public ClassesServiceMapper getClassesServiceMapper() {
		return classesServiceMapper;
	}

	public void setClassesServiceMapper(ClassesServiceMapper classesServiceMapper) {
		this.classesServiceMapper = classesServiceMapper;
	}

	@Override
	public Iterable<ClassesCategoryEntity> findAllClassesCategory() {
		return classesCategoryJpaRepository.findAll();
	}

}
