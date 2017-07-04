package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.models.Classes;
import com.go2.classes.models.jpa.CenterEntity;
import com.go2.classes.models.jpa.ClassesEntity;
import com.go2.classes.models.jpa.TeacherEntity;

@Component
public class ClassesServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ClassesServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	private boolean hasLinkToTeacher(Classes classes) {
		if(classes.getTeacherId() != null) {
			return true;
		}
		return false;
	}

	public Classes mapClassesEntityToClasses(ClassesEntity classesEntity) {
		if(classesEntity == null) {
			return null;
		}
		Classes classes = map(classesEntity, Classes.class);

		if(classesEntity.getTeacher() != null) {
			classes.setTeacherId(classesEntity.getTeacher().getId());
		}

		if(classesEntity.getCenter() != null) {
			classes.setCenterId(classesEntity.getCenter().getId());
		}

		return classes;
	}
	
	public void mapClassesToClassesEntity(Classes classes, ClassesEntity classesEntity) {
		if(classes == null) {
			return;
		}

		if(hasLinkToTeacher(classes)) {
			TeacherEntity teacher = new TeacherEntity();
			teacher.setId(classes.getTeacherId());
			classesEntity.setTeacher(teacher);
		} else {
			classesEntity.setTeacher(null);
		}

		if(hasLinkToCenter(classes)) {
			CenterEntity center = new CenterEntity();
			center.setId(classes.getCenterId());
			classesEntity.setCenter(center);
		} else {
			classesEntity.setTeacher(null);
		}
		//--- Generic mapping 
		map(classes, classesEntity);
	}
	
	private boolean hasLinkToCenter(Classes classes) {
		if(classes.getCenterId() != null) {
			return true;
		}
		return false;
	}

	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}