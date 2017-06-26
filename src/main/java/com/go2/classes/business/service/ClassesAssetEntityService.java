package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.ClassesAssetEntity;

public interface ClassesAssetEntityService { 

	ClassesAssetEntity findById( Long id  ) ;

	List<ClassesAssetEntity> findAll();

	ClassesAssetEntity save(ClassesAssetEntity entity);

	ClassesAssetEntity update(ClassesAssetEntity entity);

	ClassesAssetEntity create(ClassesAssetEntity entity);

	void delete( Long id );
}
