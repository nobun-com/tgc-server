package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.go2.classes.business.service.ChildService;
import com.go2.classes.business.service.mapping.ChildServiceMapper;
import com.go2.classes.data.repository.jpa.ChildInterestsJpaRepository;
import com.go2.classes.data.repository.jpa.ChildJpaRepository;
import com.go2.classes.data.repository.jpa.ClassesCategoryJpaRepository;
import com.go2.classes.models.Child;
import com.go2.classes.models.jpa.ChildEntity;
import com.go2.classes.models.jpa.ChildInterestsEntity;
import com.go2.classes.models.jpa.ClassesCategoryEntity;

@Component
@Transactional
public class ChildServiceImpl implements ChildService {

	@Resource
	private ChildJpaRepository childJpaRepository;

	@Resource
	ChildInterestsJpaRepository childInterestsJpaRepository;

	@Resource
	ClassesCategoryJpaRepository classesCategoryJpaRepository;

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
		for (ChildEntity childEntity : entities) {
			beans.add(childServiceMapper.mapChildEntityToChild(childEntity));
		}
		return beans;
	}

	@Override
	public Child save(Child child) {
		return update(child);
	}

	@Override
	public Child create(Child child) {
		ChildEntity childEntity = new ChildEntity();
		childServiceMapper.mapChildToChildEntity(child, childEntity);
		ChildEntity childEntitySaved = childJpaRepository.save(childEntity);
		for (Integer interestId : child.getInterest()) {
			ClassesCategoryEntity classesCategoryEntity = classesCategoryJpaRepository.findOne(interestId.longValue());
			childInterestsJpaRepository.save(new ChildInterestsEntity(classesCategoryEntity, childEntitySaved));
		}
		return childServiceMapper.mapChildEntityToChild(childEntitySaved);
	}

	@Override
	public Child update(Child child) {
		ChildEntity childEntity = childJpaRepository.findOne(child.getId());
		childServiceMapper.mapChildToChildEntity(child, childEntity);
		ChildEntity childEntitySaved = childJpaRepository.save(childEntity);
		childInterestsJpaRepository.deleteAllByChildId(childEntitySaved.getId());
		for (Integer interestId : child.getInterest()) {
			ClassesCategoryEntity classesCategoryEntity = classesCategoryJpaRepository.findOne(interestId.longValue());
			childInterestsJpaRepository.save(new ChildInterestsEntity(classesCategoryEntity, childEntitySaved));
		}
		return childServiceMapper.mapChildEntityToChild(childEntitySaved);
	}

	@Override
	public void delete(Long id) {
		childInterestsJpaRepository.deleteAllByChildId(id);
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
	public List<Child> getAllChildsByUser(Long userId) {
		Iterable<ChildEntity> entities = childJpaRepository.findAllChildsByUserId(userId);
		List<Child> beans = new ArrayList<Child>();
		for (ChildEntity childEntity : entities) {
			beans.add(childServiceMapper.mapChildEntityToChild(childEntity));
		}
		return beans;
	}

}