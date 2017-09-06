package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.models.Classes;
import com.go2.classes.models.jpa.CenterEntity;
import com.go2.classes.models.jpa.ClassesEntity;
import com.go2.classes.models.jpa.EducatorEntity;

@Component
public class ClassesServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ClassesServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	private boolean hasLinkToEducator(Classes classes) {
		if(classes.getEducatorId() != null) {
			return true;
		}
		return false;
	}

	public Classes mapClassesEntityToClasses(ClassesEntity classesEntity) {
		if(classesEntity == null) {
			return null;
		}
		Classes classes = map(classesEntity, Classes.class);

		if(classesEntity.getEducator() != null) {
			classes.setEducatorId(classesEntity.getEducator().getId());
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

		if(hasLinkToEducator(classes)) {
			EducatorEntity educator = new EducatorEntity();
			educator.setId(classes.getEducatorId());
			classesEntity.setEducator(educator);
		} else {
			classesEntity.setEducator(null);
		}

		if(hasLinkToCenter(classes)) {
			CenterEntity center = new CenterEntity();
			center.setId(classes.getCenterId());
			classesEntity.setCenter(center);
		} else {
			classesEntity.setEducator(null);
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