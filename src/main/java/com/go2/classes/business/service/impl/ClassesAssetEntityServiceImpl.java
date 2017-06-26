package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.ClassesAssetEntity;
import com.go2.classes.models.jpa.ClassesAssetEntityEntity;
import com.go2.classes.business.service.ClassesAssetEntityService;
import com.go2.classes.business.service.mapping.ClassesAssetEntityServiceMapper;
import com.go2.classes.data.repository.jpa.ClassesAssetEntityJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ClassesAssetEntityServiceImpl implements ClassesAssetEntityService {

	@Resource
	private ClassesAssetEntityJpaRepository classesAssetEntityJpaRepository;

	@Resource
	private ClassesAssetEntityServiceMapper classesAssetEntityServiceMapper;
	
	@Override
	public ClassesAssetEntity findById(Long id) {
		ClassesAssetEntityEntity classesAssetEntityEntity = classesAssetEntityJpaRepository.findOne(id);
		return classesAssetEntityServiceMapper.mapClassesAssetEntityEntityToClassesAssetEntity(classesAssetEntityEntity);
	}

	@Override
	public List<ClassesAssetEntity> findAll() {
		Iterable<ClassesAssetEntityEntity> entities = classesAssetEntityJpaRepository.findAll();
		List<ClassesAssetEntity> beans = new ArrayList<ClassesAssetEntity>();
		for(ClassesAssetEntityEntity classesAssetEntityEntity : entities) {
			beans.add(classesAssetEntityServiceMapper.mapClassesAssetEntityEntityToClassesAssetEntity(classesAssetEntityEntity));
		}
		return beans;
	}

	@Override
	public ClassesAssetEntity save(ClassesAssetEntity classesAssetEntity) {
		return update(classesAssetEntity) ;
	}

	@Override
	public ClassesAssetEntity create(ClassesAssetEntity classesAssetEntity) {
		ClassesAssetEntityEntity classesAssetEntityEntity = classesAssetEntityJpaRepository.findOne(classesAssetEntity.getId());
		if( classesAssetEntityEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		classesAssetEntityEntity = new ClassesAssetEntityEntity();
		classesAssetEntityServiceMapper.mapClassesAssetEntityToClassesAssetEntityEntity(classesAssetEntity, classesAssetEntityEntity);
		ClassesAssetEntityEntity classesAssetEntityEntitySaved = classesAssetEntityJpaRepository.save(classesAssetEntityEntity);
		return classesAssetEntityServiceMapper.mapClassesAssetEntityEntityToClassesAssetEntity(classesAssetEntityEntitySaved);
	}

	@Override
	public ClassesAssetEntity update(ClassesAssetEntity classesAssetEntity) {
		ClassesAssetEntityEntity classesAssetEntityEntity = classesAssetEntityJpaRepository.findOne(classesAssetEntity.getId());
		classesAssetEntityServiceMapper.mapClassesAssetEntityToClassesAssetEntityEntity(classesAssetEntity, classesAssetEntityEntity);
		ClassesAssetEntityEntity classesAssetEntityEntitySaved = classesAssetEntityJpaRepository.save(classesAssetEntityEntity);
		return classesAssetEntityServiceMapper.mapClassesAssetEntityEntityToClassesAssetEntity(classesAssetEntityEntitySaved);
	}

	@Override
	public void delete(Long id) {
		classesAssetEntityJpaRepository.delete(id);
	}

	public ClassesAssetEntityJpaRepository getClassesAssetEntityJpaRepository() {
		return classesAssetEntityJpaRepository;
	}

	public void setClassesAssetEntityJpaRepository(ClassesAssetEntityJpaRepository classesAssetEntityJpaRepository) {
		this.classesAssetEntityJpaRepository = classesAssetEntityJpaRepository;
	}

	public ClassesAssetEntityServiceMapper getClassesAssetEntityServiceMapper() {
		return classesAssetEntityServiceMapper;
	}

	public void setClassesAssetEntityServiceMapper(ClassesAssetEntityServiceMapper classesAssetEntityServiceMapper) {
		this.classesAssetEntityServiceMapper = classesAssetEntityServiceMapper;
	}

}
