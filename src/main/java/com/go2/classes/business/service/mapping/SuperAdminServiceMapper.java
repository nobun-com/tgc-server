package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.SuperAdmin;
import com.go2.classes.models.jpa.SuperAdminEntity;

@Component
public class SuperAdminServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public SuperAdminServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public SuperAdmin mapSuperAdminEntityToSuperAdmin(SuperAdminEntity superAdminEntity) {
		if(superAdminEntity == null) {
			return null;
		}

		//--- Generic mapping 
		SuperAdmin superAdmin = map(superAdminEntity, SuperAdmin.class);

		return superAdmin;
	}
	
	public void mapSuperAdminToSuperAdminEntity(SuperAdmin superAdmin, SuperAdminEntity superAdminEntity) {
		if(superAdmin == null) {
			return;
		}
		map(superAdmin, superAdminEntity);

	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}