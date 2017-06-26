package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.CenterAssetEntity;

public interface CenterAssetEntityService { 

	CenterAssetEntity findById( Long id  ) ;

	List<CenterAssetEntity> findAll();

	CenterAssetEntity save(CenterAssetEntity entity);

	CenterAssetEntity update(CenterAssetEntity entity);

	CenterAssetEntity create(CenterAssetEntity entity);

	void delete( Long id );


}
