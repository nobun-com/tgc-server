package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Educator;
import com.go2.classes.models.jpa.EducatorEntity;

@Component
public class EducatorServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public EducatorServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Educator mapEducatorEntityToEducator(EducatorEntity educatorEntity) {
		if(educatorEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Educator educator = map(educatorEntity, Educator.class);

		return educator;
	}
	
	public void mapEducatorToEducatorEntity(Educator educator, EducatorEntity educatorEntity) {
		if(educator == null) {
			return;
		}

		//--- Generic mapping 
		map(educator, educatorEntity);

	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}