package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.SuperAdmin;

public interface SuperAdminService { 

	SuperAdmin findById( Long id  ) ;

	List<SuperAdmin> findAll();

	SuperAdmin save(SuperAdmin entity);

	SuperAdmin update(SuperAdmin entity);

	SuperAdmin create(SuperAdmin entity);

	void delete( Long id );
}
