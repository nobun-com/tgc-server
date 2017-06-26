package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.Asset;
import com.go2.classes.models.jpa.AssetEntity;
import com.go2.classes.business.service.AssetService;
import com.go2.classes.business.service.mapping.AssetServiceMapper;
import com.go2.classes.data.repository.jpa.AssetJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AssetService
 */
@Component
@Transactional
public class AssetServiceImpl implements AssetService {

	@Resource
	private AssetJpaRepository assetJpaRepository;

	@Resource
	private AssetServiceMapper assetServiceMapper;
	
	@Override
	public Asset findById(Long id) {
		AssetEntity assetEntity = assetJpaRepository.findOne(id);
		return assetServiceMapper.mapAssetEntityToAsset(assetEntity);
	}

	@Override
	public List<Asset> findAll() {
		Iterable<AssetEntity> entities = assetJpaRepository.findAll();
		List<Asset> beans = new ArrayList<Asset>();
		for(AssetEntity assetEntity : entities) {
			beans.add(assetServiceMapper.mapAssetEntityToAsset(assetEntity));
		}
		return beans;
	}

	@Override
	public Asset save(Asset asset) {
		return update(asset) ;
	}

	@Override
	public Asset create(Asset asset) {
		AssetEntity assetEntity = assetJpaRepository.findOne(asset.getId());
		if( assetEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		assetEntity = new AssetEntity();
		assetServiceMapper.mapAssetToAssetEntity(asset, assetEntity);
		AssetEntity assetEntitySaved = assetJpaRepository.save(assetEntity);
		return assetServiceMapper.mapAssetEntityToAsset(assetEntitySaved);
	}

	@Override
	public Asset update(Asset asset) {
		AssetEntity assetEntity = assetJpaRepository.findOne(asset.getId());
		assetServiceMapper.mapAssetToAssetEntity(asset, assetEntity);
		AssetEntity assetEntitySaved = assetJpaRepository.save(assetEntity);
		return assetServiceMapper.mapAssetEntityToAsset(assetEntitySaved);
	}

	@Override
	public void delete(Long id) {
		assetJpaRepository.delete(id);
	}

	public AssetJpaRepository getAssetJpaRepository() {
		return assetJpaRepository;
	}

	public void setAssetJpaRepository(AssetJpaRepository assetJpaRepository) {
		this.assetJpaRepository = assetJpaRepository;
	}

	public AssetServiceMapper getAssetServiceMapper() {
		return assetServiceMapper;
	}

	public void setAssetServiceMapper(AssetServiceMapper assetServiceMapper) {
		this.assetServiceMapper = assetServiceMapper;
	}

}
