package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.Admin;
import com.go2.classes.models.jpa.AdminEntity;
import com.go2.classes.business.service.AdminService;
import com.go2.classes.business.service.mapping.AdminServiceMapper;
import com.go2.classes.data.repository.jpa.AdminJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminJpaRepository adminJpaRepository;

	@Resource
	private AdminServiceMapper adminServiceMapper;
	
	@Override
	public Admin findById(Long id) {
		AdminEntity adminEntity = adminJpaRepository.findOne(id);
		return adminServiceMapper.mapAdminEntityToAdmin(adminEntity);
	}

	@Override
	public List<Admin> findAll() {
		Iterable<AdminEntity> entities = adminJpaRepository.findAll();
		List<Admin> beans = new ArrayList<Admin>();
		for(AdminEntity adminEntity : entities) {
			beans.add(adminServiceMapper.mapAdminEntityToAdmin(adminEntity));
		}
		return beans;
	}

	@Override
	public Admin save(Admin admin) {
		return update(admin) ;
	}

	@Override
	public Admin create(Admin admin) {
		AdminEntity adminEntity = adminJpaRepository.findOne(admin.getId());
		if( adminEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		adminEntity = new AdminEntity();
		adminServiceMapper.mapAdminToAdminEntity(admin, adminEntity);
		AdminEntity adminEntitySaved = adminJpaRepository.save(adminEntity);
		return adminServiceMapper.mapAdminEntityToAdmin(adminEntitySaved);
	}

	@Override
	public Admin update(Admin admin) {
		AdminEntity adminEntity = adminJpaRepository.findOne(admin.getId());
		adminServiceMapper.mapAdminToAdminEntity(admin, adminEntity);
		AdminEntity adminEntitySaved = adminJpaRepository.save(adminEntity);
		return adminServiceMapper.mapAdminEntityToAdmin(adminEntitySaved);
	}

	@Override
	public void delete(Long id) {
		adminJpaRepository.delete(id);
	}

	public AdminJpaRepository getAdminJpaRepository() {
		return adminJpaRepository;
	}

	public void setAdminJpaRepository(AdminJpaRepository adminJpaRepository) {
		this.adminJpaRepository = adminJpaRepository;
	}

	public AdminServiceMapper getAdminServiceMapper() {
		return adminServiceMapper;
	}

	public void setAdminServiceMapper(AdminServiceMapper adminServiceMapper) {
		this.adminServiceMapper = adminServiceMapper;
	}

	@Override
	public Admin findByEmail(String email) {
		AdminEntity adminEntity = adminJpaRepository.findByEmail(email);
		if(Objects.isNull(adminEntity)) {
			return null;
		}
		return adminServiceMapper.mapAdminEntityToAdmin(adminEntity);

	}

}
