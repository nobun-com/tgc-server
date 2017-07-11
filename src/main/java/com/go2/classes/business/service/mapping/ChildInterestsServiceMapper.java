package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.models.ChildInterests;
import com.go2.classes.models.jpa.ChildInterestsEntity;

@Component
public class ChildInterestsServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ChildInterestsServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public ChildInterests mapChildInterestsEntityToChildInterests(ChildInterestsEntity childInterestsEntity) {
		if(childInterestsEntity == null) {
			return null;
		}

		//--- Generic mapping 
		ChildInterests childInterests = map(childInterestsEntity, ChildInterests.class);

		return childInterests;
	}
	
	public void mapChildInterestsToChildInterestsEntity(ChildInterests childInterests, ChildInterestsEntity childInterestsEntity) {
		if(childInterests == null) {
			return;
		}

		//--- Generic mapping 
		map(childInterests, childInterestsEntity);

	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}