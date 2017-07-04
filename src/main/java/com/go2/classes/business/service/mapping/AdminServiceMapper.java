package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Admin;
import com.go2.classes.models.jpa.AdminEntity;

@Component
public class AdminServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public AdminServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Admin mapAdminEntityToAdmin(AdminEntity adminEntity) {
		if(adminEntity == null) {
			return null;
		}
		Admin admin = map(adminEntity, Admin.class);
		return admin;
	}
	
	public void mapAdminToAdminEntity(Admin admin, AdminEntity adminEntity) {
		if(admin == null) {
			return;
		}
		map(admin, adminEntity);
	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}