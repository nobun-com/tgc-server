package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.User;
import com.go2.classes.models.jpa.UserEntity;

@Component
public class UserServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public UserServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public User mapUserEntityToUser(UserEntity userEntity) {
		if(userEntity == null) {
			return null;
		}

		//--- Generic mapping 
		User user = map(userEntity, User.class);

		return user;
	}
	
	public void mapUserToUserEntity(User user, UserEntity userEntity) {
		if(user == null) {
			return;
		}

		//--- Generic mapping 
		map(user, userEntity);

	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}