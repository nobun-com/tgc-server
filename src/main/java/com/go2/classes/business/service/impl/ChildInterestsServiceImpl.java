package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.go2.classes.business.service.ChildInterestsService;
import com.go2.classes.business.service.ChildService;
import com.go2.classes.business.service.mapping.ChildInterestsServiceMapper;
import com.go2.classes.data.repository.jpa.ChildInterestsJpaRepository;
import com.go2.classes.data.repository.jpa.ChildJpaRepository;
import com.go2.classes.models.Child;
import com.go2.classes.models.ChildInterests;
import com.go2.classes.models.jpa.ChildEntity;
import com.go2.classes.models.jpa.ChildInterestsEntity;

@Component
@Transactional
public class ChildInterestsServiceImpl implements ChildInterestsService {

	@Resource
	private ChildInterestsJpaRepository childInterestsJpaRepository;
	
	@Resource
	private ChildJpaRepository childJpaRepository;

	@Resource
	private ChildInterestsServiceMapper childInterestsServiceMapper;
	
	@Resource
    private ChildService childService; // Injected by Spring
	
	
	@Override
	public ChildInterests findById(Long id) {
		ChildInterestsEntity childInterestsEntity = childInterestsJpaRepository.findOne(id);
		return childInterestsServiceMapper.mapChildInterestsEntityToChildInterests(childInterestsEntity);
	}

	@Override
	public List<ChildInterests> findAll() {
		Iterable<ChildInterestsEntity> entities = childInterestsJpaRepository.findAll();
		List<ChildInterests> beans = new ArrayList<ChildInterests>();
		for(ChildInterestsEntity childInterestsEntity : entities) {
			beans.add(childInterestsServiceMapper.mapChildInterestsEntityToChildInterests(childInterestsEntity));
		}
		return beans;
	}

	@Override
	public ChildInterests save(ChildInterests childInterests) {
		return update(childInterests) ;
	}

	@Override
	public ChildInterests create(ChildInterests childInterests) {
		ChildInterestsEntity childInterestsEntity = null;
		if ( !Objects.isNull(childInterests.getId()) ) {
			childInterestsEntity = childInterestsJpaRepository.findOne(childInterests.getId());		}
		if ( !Objects.isNull(childInterestsEntity) ) {
			throw new IllegalStateException("already.exists");
		}
		childInterestsEntity = new ChildInterestsEntity();
		childInterestsServiceMapper.mapChildInterestsToChildInterestsEntity(childInterests, childInterestsEntity);
		ChildInterestsEntity childInterestsEntitySaved = childInterestsJpaRepository.save(childInterestsEntity);
		return childInterestsServiceMapper.mapChildInterestsEntityToChildInterests(childInterestsEntitySaved);
	}

	@Override
	public ChildInterests update(ChildInterests childInterests) {
		ChildInterestsEntity childInterestsEntity = childInterestsJpaRepository.findOne(childInterests.getId());
		childInterestsServiceMapper.mapChildInterestsToChildInterestsEntity(childInterests, childInterestsEntity);
		ChildInterestsEntity childInterestsEntitySaved = childInterestsJpaRepository.save(childInterestsEntity);
		return childInterestsServiceMapper.mapChildInterestsEntityToChildInterests(childInterestsEntitySaved);
	}

	@Override
	public void delete(Long id) {
		childInterestsJpaRepository.delete(id);
	}

	public ChildInterestsJpaRepository getChildInterestsJpaRepository() {
		return childInterestsJpaRepository;
	}

	public void setChildInterestsJpaRepository(ChildInterestsJpaRepository childInterestsJpaRepository) {
		this.childInterestsJpaRepository = childInterestsJpaRepository;
	}

	public ChildInterestsServiceMapper getChildInterestsServiceMapper() {
		return childInterestsServiceMapper;
	}

	public void setChildInterestsServiceMapper(ChildInterestsServiceMapper childInterestsServiceMapper) {
		this.childInterestsServiceMapper = childInterestsServiceMapper;
	}

	
	@Override
	public List<ChildInterests> getAllChildInterestsByChild(Long childId) {
		Iterable<ChildInterestsEntity> entities = childInterestsJpaRepository.findAllChildInterestsByChildId(childId);
		List<ChildInterests> beans = new ArrayList<ChildInterests>();
		for(ChildInterestsEntity childInterestsEntity : entities) {
			beans.add(childInterestsServiceMapper.mapChildInterestsEntityToChildInterests(childInterestsEntity));
		}
		return beans;
	}
	
	@Override
	public Map<String, List<String>> getAllChildInterestsByChild() {
		Iterable<ChildEntity> entities = childJpaRepository.findAll();
		Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();
		
		for(ChildEntity childEntity : entities) {
			result.put(childEntity.getId()+"", childInterestsJpaRepository.findAllChildInterestsByChild(childEntity.getId()));
		}
		return result;
	}

}
