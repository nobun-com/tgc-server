package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Asset;

public interface AssetService { 

	Asset findById( Long id  ) ;

	List<Asset> findAll();

	Asset save(Asset entity);

	Asset update(Asset entity);

	Asset create(Asset entity);

	void delete( Long id );


}
