package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Center;

public interface CenterService { 

	Center findById( Long id  ) ;

	List<Center> findAll();

	Center save(Center entity);

	Center update(Center entity);

	Center create(Center entity);

	void delete( Long id );
}
