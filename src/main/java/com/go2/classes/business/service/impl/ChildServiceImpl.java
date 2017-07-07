package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.go2.classes.business.service.ChildService;
import com.go2.classes.business.service.mapping.ChildServiceMapper;
import com.go2.classes.data.repository.jpa.ChildJpaRepository;
import com.go2.classes.models.Child;
import com.go2.classes.models.jpa.ChildEntity;

@Component
@Transactional
public class ChildServiceImpl implements ChildService {

	@Resource
	private ChildJpaRepository childJpaRepository;

	@Resource
	private ChildServiceMapper childServiceMapper;
	
	@Override
	public Child findById(Long id) {
		ChildEntity childEntity = childJpaRepository.findOne(id);
		return childServiceMapper.mapChildEntityToChild(childEntity);
	}

	@Override
	public List<Child> findAll() {
		Iterable<ChildEntity> entities = childJpaRepository.findAll();
		List<Child> beans = new ArrayList<Child>();
		for(ChildEntity childEntity : entities) {
			beans.add(childServiceMapper.mapChildEntityToChild(childEntity));
		}
		return beans;
	}

	@Override
	public Child save(Child child) {
		return update(child) ;
	}

	@Override
	public Child create(Child child) {
		ChildEntity childEntity = null;
		if ( !Objects.isNull(child.getId()) ) {
			childEntity = childJpaRepository.findOne(child.getId());		}
		if ( !Objects.isNull(childEntity) ) {
			throw new IllegalStateException("already.exists");
		}
		childEntity = new ChildEntity();
		childServiceMapper.mapChildToChildEntity(child, childEntity);
		ChildEntity childEntitySaved = childJpaRepository.save(childEntity);
		return childServiceMapper.mapChildEntityToChild(childEntitySaved);
	}

	@Override
	public Child update(Child child) {
		ChildEntity childEntity = childJpaRepository.findOne(child.getId());
		childServiceMapper.mapChildToChildEntity(child, childEntity);
		ChildEntity childEntitySaved = childJpaRepository.save(childEntity);
		return childServiceMapper.mapChildEntityToChild(childEntitySaved);
	}

	@Override
	public void delete(Long id) {
		childJpaRepository.delete(id);
	}

	public ChildJpaRepository getChildJpaRepository() {
		return childJpaRepository;
	}

	public void setChildJpaRepository(ChildJpaRepository childJpaRepository) {
		this.childJpaRepository = childJpaRepository;
	}

	public ChildServiceMapper getChildServiceMapper() {
		return childServiceMapper;
	}

	public void setChildServiceMapper(ChildServiceMapper childServiceMapper) {
		this.childServiceMapper = childServiceMapper;
	}

	
	@Override
	public List<Child> getAllChildsByStudent(Long studentId) {
		Iterable<ChildEntity> entities = childJpaRepository.findAllChildsByStudentId(studentId);
		List<Child> beans = new ArrayList<Child>();
		for(ChildEntity childEntity : entities) {
			beans.add(childServiceMapper.mapChildEntityToChild(childEntity));
		}
		return beans;
	}

}
