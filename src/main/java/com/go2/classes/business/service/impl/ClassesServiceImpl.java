package com.go2.classes.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.UserService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.business.service.impl.helper.ClassMetadataHelper;
import com.go2.classes.business.service.mapping.ClassesServiceMapper;
import com.go2.classes.common.EmailService;
import com.go2.classes.data.repository.jpa.ClassesCategoryJpaRepository;
import com.go2.classes.data.repository.jpa.ClassesJpaRepository;
import com.go2.classes.models.Classes;
import com.go2.classes.models.User;
import com.go2.classes.models.jpa.ClassesCategoryEntity;
import com.go2.classes.models.jpa.ClassesEntity;

@Component
@Transactional
public class ClassesServiceImpl implements ClassesService {

    @Resource
    private ClassesJpaRepository classesJpaRepository;
    
    @Resource
    private UserService userService;
    
    @Resource
    private ClassesCategoryJpaRepository classesCategoryJpaRepository;

    @Resource
    private ClassMetadataHelper classMetadataHelper;

    @Resource
    private TimeTableService timeTableService;

    @Resource
    private ClassesServiceMapper classesServiceMapper;

    @Resource
    EmailService emailService = new EmailService();
    
    @Override
    public Classes findById(Long id) {
	ClassesEntity classesEntity = classesJpaRepository.findOne(id);
	if (Objects.isNull(classesEntity)) {
	    throw new IllegalStateException("class.not.found");
	}
	return classesServiceMapper.mapClassesEntityToClasses(classesEntity);
    }

    @Override
    public List<Classes> findAll() {
	Iterable<ClassesEntity> entities = classesJpaRepository.findAll();
	List<Classes> beans = new ArrayList<Classes>();
	for (ClassesEntity classesEntity : entities) {
	    beans.add(classesServiceMapper.mapClassesEntityToClasses(classesEntity));
	}
	return beans;
    }

    @Override
    public List<Classes> getAllClassesByCenter(Long centerId) {
	Iterable<ClassesEntity> entities = classesJpaRepository.findAllClassesByCenterId(centerId);
	List<Classes> beans = new ArrayList<Classes>();
	for (ClassesEntity classesEntity : entities) {
	    beans.add(classesServiceMapper.mapClassesEntityToClasses(classesEntity));
	}
	return beans;
    }

    @Override
    public List<Classes> getAllClassesByEducator(Long educatorId) {
	Iterable<ClassesEntity> entities = classesJpaRepository.findAllClassesByEducatorId(educatorId);
	List<Classes> beans = new ArrayList<Classes>();
	for (ClassesEntity classesEntity : entities) {
	    beans.add(classesServiceMapper.mapClassesEntityToClasses(classesEntity));
	}
	return beans;
    }

    @Override
    public Classes save(Classes classes) {
	return update(classes);
    }

    @Override
    public Classes create(Classes classes) {
	ClassesEntity classesEntity = null;
	if (!Objects.isNull(classes.getId())) {
	    classesEntity = classesJpaRepository.findOne(classes.getId());
	}
	if (!Objects.isNull(classesEntity)) {
	    throw new IllegalStateException("class.already.exists");
	}
	classesEntity = new ClassesEntity();
	classesServiceMapper.mapClassesToClassesEntity(classes, classesEntity);
	classesEntity.setSlotsAvailable(classesEntity.getMaxSlots());
	ClassesEntity classesEntitySaved = classesJpaRepository.save(classesEntity);
	Classes classesSaved = classesServiceMapper.mapClassesEntityToClasses(classesEntitySaved);
	classMetadataHelper.parseClassesMetaData(classesSaved);
	return classesSaved;
    }

    @Override
    public Classes update(Classes classes) {
	ClassesEntity classesEntity = null;
	if (!Objects.isNull(classes.getId())) {
	    classesEntity = classesJpaRepository.findOne(classes.getId());
	}
	if (Objects.isNull(classesEntity)) {
	    throw new IllegalStateException("class.not.found");
	}
	timeTableService.invalidByClass(classes.getId());
	for(BigInteger userId : classesJpaRepository.getUsersOfClass(classes.getId())){
	    User user = userService.findById(userId.longValue());
	    String email = user.getEmail();
	    String msg = "Dear " + user.getName() + " schedule of class " + classesEntity.getClassName() + " is changed please have a look";
	    emailService.sendEmail("Class schedule updated", msg, email);
	}
	classesServiceMapper.mapClassesToClassesEntity(classes, classesEntity);
	ClassesEntity classesEntitySaved = classesJpaRepository.save(classesEntity);
	classMetadataHelper.parseClassesMetaData(classes);
	
	return classesServiceMapper.mapClassesEntityToClasses(classesEntitySaved);
    }

    @Override
    public void delete(Long id) {
	ClassesEntity classesEntity = null;
	if (!Objects.isNull(id)) {
	    classesEntity = classesJpaRepository.findOne(id);
	}
	if (Objects.isNull(classesEntity)) {
	    throw new IllegalStateException("class.not.found");
	}
	timeTableService.invalidByClass(id);
	for(BigInteger userId : classesJpaRepository.getUsersOfClass(id)){
	    User user = userService.findById(userId.longValue());
	    String email = user.getEmail();
	    String msg = "Dear " + user.getName() + " class " + classesEntity.getClassName() + " is closed";
	    emailService.sendEmail("Class canceled", msg, email);
	}
	classesJpaRepository.invalid(id);
    }

    public ClassesJpaRepository getClassesJpaRepository() {
	return classesJpaRepository;
    }

    public void setClassesJpaRepository(ClassesJpaRepository classesJpaRepository) {
	this.classesJpaRepository = classesJpaRepository;
    }

    public ClassesServiceMapper getClassesServiceMapper() {
	return classesServiceMapper;
    }

    public void setClassesServiceMapper(ClassesServiceMapper classesServiceMapper) {
	this.classesServiceMapper = classesServiceMapper;
    }

    @Override
    public List<Map<String, Object>> findAllClassesCategory() {
	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	for (ClassesCategoryEntity classesCategory : classesCategoryJpaRepository.findAll()) {
	    Map<String, Object> record = new HashMap<String, Object>();
	    record.put("id", classesCategory.getId());
	    record.put("category", classesCategory.getCategory());
	    result.add(record);
	}
	return result;
    }

    @Override
    public void bookClass(ClassesEntity classes) {
	classes.setSlotsAvailable(classes.getSlotsAvailable() - 1);
	classesJpaRepository.save(classes);
    }
	@Override
	public Integer getActiveClassesCount() {
		return classesJpaRepository.getActiveClassesCount();
	}
	@Override
	public Integer getActiveClassesCountByEducator(Long educatorId) {
		return classesJpaRepository.getActiveClassesCountByEducator(educatorId);
	}

}
