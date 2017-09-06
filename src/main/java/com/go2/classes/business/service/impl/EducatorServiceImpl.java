package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.Educator;
import com.go2.classes.models.jpa.EducatorEntity;
import com.go2.classes.business.service.EducatorService;
import com.go2.classes.business.service.mapping.EducatorServiceMapper;
import com.go2.classes.data.repository.jpa.EducatorJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EducatorServiceImpl implements EducatorService {

    @Resource
    private EducatorJpaRepository educatorJpaRepository;

    @Resource
    private EducatorServiceMapper educatorServiceMapper;

    @Override
    public Educator findById(Long id) {
	EducatorEntity educatorEntity = educatorJpaRepository.findOne(id);
	if (Objects.isNull(educatorEntity)) {
	    throw new IllegalStateException("educator.not.found");
	}
	return educatorServiceMapper.mapEducatorEntityToEducator(educatorEntity);
    }

    @Override
    public Educator findByEmail(String email) {
	EducatorEntity educatorEntity = educatorJpaRepository.findByEmail(email);
	if (Objects.isNull(educatorEntity)) {
	    return null;
	}
	return educatorServiceMapper.mapEducatorEntityToEducator(educatorEntity);
    }

    @Override
    public List<Educator> findAll() {
	Iterable<EducatorEntity> entities = educatorJpaRepository.findAll();
	List<Educator> beans = new ArrayList<Educator>();
	for (EducatorEntity educatorEntity : entities) {
	    beans.add(educatorServiceMapper.mapEducatorEntityToEducator(educatorEntity));
	}
	return beans;
    }

    @Override
    public Educator save(Educator educator) {
	return update(educator);
    }

    @Override
    public Educator create(Educator educator) {
	EducatorEntity educatorEntity = null;
	if (!Objects.isNull(educator.getId())) {
	    educatorEntity = educatorJpaRepository.findOne(educator.getId());
	}
	if (!Objects.isNull(educatorEntity)) {
	    throw new IllegalStateException("educator.already.exists");
	}
	educatorEntity = new EducatorEntity();
	educatorServiceMapper.mapEducatorToEducatorEntity(educator, educatorEntity);
	EducatorEntity educatorEntitySaved = educatorJpaRepository.save(educatorEntity);
	return educatorServiceMapper.mapEducatorEntityToEducator(educatorEntitySaved);
    }

    @Override
    public Educator update(Educator educator) {
	EducatorEntity educatorEntity = null;
	if (!Objects.isNull(educator.getId())) {
	    educatorEntity = educatorJpaRepository.findOne(educator.getId());
	}
	if (Objects.isNull(educatorEntity)) {
	    throw new IllegalStateException("educator.not.found");
	}
	educatorServiceMapper.mapEducatorToEducatorEntity(educator, educatorEntity);
	EducatorEntity educatorEntitySaved = educatorJpaRepository.save(educatorEntity);
	return educatorServiceMapper.mapEducatorEntityToEducator(educatorEntitySaved);
    }

    @Override
    public void delete(Long id) {
	if (Objects.isNull(id)) {
	    throw new IllegalStateException("educator.not.found");
	}
	educatorJpaRepository.delete(id);
    }

    public EducatorJpaRepository getEducatorJpaRepository() {
	return educatorJpaRepository;
    }

    public void setEducatorJpaRepository(EducatorJpaRepository educatorJpaRepository) {
	this.educatorJpaRepository = educatorJpaRepository;
    }

    public EducatorServiceMapper getEducatorServiceMapper() {
	return educatorServiceMapper;
    }

    public void setEducatorServiceMapper(EducatorServiceMapper educatorServiceMapper) {
	this.educatorServiceMapper = educatorServiceMapper;
    }

    @Override
    public Integer getEducatorsCount() {
	return educatorJpaRepository.getEducatorsCount();
    }

}
