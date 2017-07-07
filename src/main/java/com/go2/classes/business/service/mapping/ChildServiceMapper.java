package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.models.Child;
import com.go2.classes.models.jpa.ChildEntity;

@Component
public class ChildServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ChildServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Child mapChildEntityToChild(ChildEntity childEntity) {
		if(childEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Child child = map(childEntity, Child.class);

		return child;
	}
	
	public void mapChildToChildEntity(Child child, ChildEntity childEntity) {
		if(child == null) {
			return;
		}

		//--- Generic mapping 
		map(child, childEntity);

	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}