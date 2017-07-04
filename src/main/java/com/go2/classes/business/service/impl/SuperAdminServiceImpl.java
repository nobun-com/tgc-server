package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.SuperAdmin;
import com.go2.classes.models.jpa.SuperAdminEntity;
import com.go2.classes.business.service.SuperAdminService;
import com.go2.classes.business.service.mapping.SuperAdminServiceMapper;
import com.go2.classes.data.repository.jpa.SuperAdminJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SuperAdminServiceImpl implements SuperAdminService {

	@Resource
	private SuperAdminJpaRepository superAdminJpaRepository;

	@Resource
	private SuperAdminServiceMapper superAdminServiceMapper;
	
	@Override
	public SuperAdmin findById(Long id) {
		SuperAdminEntity superAdminEntity = superAdminJpaRepository.findOne(id);
		return superAdminServiceMapper.mapSuperAdminEntityToSuperAdmin(superAdminEntity);
	}

	@Override
	public List<SuperAdmin> findAll() {
		Iterable<SuperAdminEntity> entities = superAdminJpaRepository.findAll();
		List<SuperAdmin> beans = new ArrayList<SuperAdmin>();
		for(SuperAdminEntity superAdminEntity : entities) {
			beans.add(superAdminServiceMapper.mapSuperAdminEntityToSuperAdmin(superAdminEntity));
		}
		return beans;
	}

	@Override
	public SuperAdmin save(SuperAdmin superAdmin) {
		return update(superAdmin) ;
	}

	@Override
	public SuperAdmin create(SuperAdmin superAdmin) {
		SuperAdminEntity superAdminEntity = superAdminJpaRepository.findOne(superAdmin.getId());
		if( superAdminEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		superAdminEntity = new SuperAdminEntity();
		superAdminServiceMapper.mapSuperAdminToSuperAdminEntity(superAdmin, superAdminEntity);
		SuperAdminEntity superAdminEntitySaved = superAdminJpaRepository.save(superAdminEntity);
		return superAdminServiceMapper.mapSuperAdminEntityToSuperAdmin(superAdminEntitySaved);
	}

	@Override
	public SuperAdmin update(SuperAdmin superAdmin) {
		SuperAdminEntity superAdminEntity = superAdminJpaRepository.findOne(superAdmin.getId());
		superAdminServiceMapper.mapSuperAdminToSuperAdminEntity(superAdmin, superAdminEntity);
		SuperAdminEntity superAdminEntitySaved = superAdminJpaRepository.save(superAdminEntity);
		return superAdminServiceMapper.mapSuperAdminEntityToSuperAdmin(superAdminEntitySaved);
	}

	@Override
	public void delete(Long id) {
		superAdminJpaRepository.delete(id);
	}

	public SuperAdminJpaRepository getSuperAdminJpaRepository() {
		return superAdminJpaRepository;
	}

	public void setSuperAdminJpaRepository(SuperAdminJpaRepository superAdminJpaRepository) {
		this.superAdminJpaRepository = superAdminJpaRepository;
	}

	public SuperAdminServiceMapper getSuperAdminServiceMapper() {
		return superAdminServiceMapper;
	}

	public void setSuperAdminServiceMapper(SuperAdminServiceMapper superAdminServiceMapper) {
		this.superAdminServiceMapper = superAdminServiceMapper;
	}

}
