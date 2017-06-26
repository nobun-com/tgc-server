package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Area;
import com.go2.classes.models.jpa.AreaEntity;

@Component
public class AreaServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public AreaServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Area mapAddressEntityToAddress(AreaEntity areaEntity) {
		if(areaEntity == null) {
			return null;
		}
		Area area = map(areaEntity, Area.class);
		return area;
	}
	
	public void mapAddressToAddressEntity(Area area, AreaEntity areaEntity) {
		if(area == null) {
			return;
		}
		map(area, areaEntity);
	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}