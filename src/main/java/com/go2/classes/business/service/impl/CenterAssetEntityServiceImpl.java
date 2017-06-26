package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.go2.classes.models.CenterAssetEntity;
import com.go2.classes.models.jpa.CenterAssetEntityEntity;
import com.go2.classes.business.service.CenterAssetEntityService;
import com.go2.classes.business.service.mapping.CenterAssetEntityServiceMapper;
import com.go2.classes.data.repository.jpa.CenterAssetEntityJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CenterAssetEntityServiceImpl implements CenterAssetEntityService {

	@Resource
	private CenterAssetEntityJpaRepository centerAssetEntityJpaRepository;

	@Resource
	private CenterAssetEntityServiceMapper centerAssetEntityServiceMapper;
	
	@Override
	public CenterAssetEntity findById(Long id) {
		CenterAssetEntityEntity centerAssetEntityEntity = centerAssetEntityJpaRepository.findOne(id);
		return centerAssetEntityServiceMapper.mapCenterAssetEntityEntityToCenterAssetEntity(centerAssetEntityEntity);
	}

	@Override
	public List<CenterAssetEntity> findAll() {
		Iterable<CenterAssetEntityEntity> entities = centerAssetEntityJpaRepository.findAll();
		List<CenterAssetEntity> beans = new ArrayList<CenterAssetEntity>();
		for(CenterAssetEntityEntity centerAssetEntityEntity : entities) {
			beans.add(centerAssetEntityServiceMapper.mapCenterAssetEntityEntityToCenterAssetEntity(centerAssetEntityEntity));
		}
		return beans;
	}

	@Override
	public CenterAssetEntity save(CenterAssetEntity centerAssetEntity) {
		return update(centerAssetEntity) ;
	}

	@Override
	public CenterAssetEntity create(CenterAssetEntity centerAssetEntity) {
		CenterAssetEntityEntity centerAssetEntityEntity = centerAssetEntityJpaRepository.findOne(centerAssetEntity.getId());
		if( centerAssetEntityEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		centerAssetEntityEntity = new CenterAssetEntityEntity();
		centerAssetEntityServiceMapper.mapCenterAssetEntityToCenterAssetEntityEntity(centerAssetEntity, centerAssetEntityEntity);
		CenterAssetEntityEntity centerAssetEntityEntitySaved = centerAssetEntityJpaRepository.save(centerAssetEntityEntity);
		return centerAssetEntityServiceMapper.mapCenterAssetEntityEntityToCenterAssetEntity(centerAssetEntityEntitySaved);
	}

	@Override
	public CenterAssetEntity update(CenterAssetEntity centerAssetEntity) {
		CenterAssetEntityEntity centerAssetEntityEntity = centerAssetEntityJpaRepository.findOne(centerAssetEntity.getId());
		centerAssetEntityServiceMapper.mapCenterAssetEntityToCenterAssetEntityEntity(centerAssetEntity, centerAssetEntityEntity);
		CenterAssetEntityEntity centerAssetEntityEntitySaved = centerAssetEntityJpaRepository.save(centerAssetEntityEntity);
		return centerAssetEntityServiceMapper.mapCenterAssetEntityEntityToCenterAssetEntity(centerAssetEntityEntitySaved);
	}

	@Override
	public void delete(Long id) {
		centerAssetEntityJpaRepository.delete(id);
	}

	public CenterAssetEntityJpaRepository getCenterAssetEntityJpaRepository() {
		return centerAssetEntityJpaRepository;
	}

	public void setCenterAssetEntityJpaRepository(CenterAssetEntityJpaRepository centerAssetEntityJpaRepository) {
		this.centerAssetEntityJpaRepository = centerAssetEntityJpaRepository;
	}

	public CenterAssetEntityServiceMapper getCenterAssetEntityServiceMapper() {
		return centerAssetEntityServiceMapper;
	}

	public void setCenterAssetEntityServiceMapper(CenterAssetEntityServiceMapper centerAssetEntityServiceMapper) {
		this.centerAssetEntityServiceMapper = centerAssetEntityServiceMapper;
	}

}
