package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.models.ClassesCategory;
import com.go2.classes.models.jpa.ClassesCategoryEntity;

@Component
public class ClassesCategoryServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ClassesCategoryServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public ClassesCategory mapClassesCategoryEntityToClassesCategory(ClassesCategoryEntity classesCategoryEntity) {
		if(classesCategoryEntity == null) {
			return null;
		}
		ClassesCategory classesCategory = map(classesCategoryEntity, ClassesCategory.class);

		return classesCategory;
	}
	
	public void mapClassesCategoryToClassesCategoryEntity(ClassesCategory classesCategory, ClassesCategoryEntity classesCategoryEntity) {
		if(classesCategory == null) {
			return;
		}

		map(classesCategory, classesCategoryEntity);
	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}