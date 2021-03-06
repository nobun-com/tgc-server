package com.go2.classes.business.service.mapping;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.models.Child;
import com.go2.classes.models.jpa.ChildEntity;
import com.go2.classes.models.jpa.ChildInterestsEntity;
import com.go2.classes.models.jpa.UserEntity;

@Component
public class ChildServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;

	public ChildServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Child mapChildEntityToChild(ChildEntity childEntity) {
		if (childEntity == null) {
			return null;
		}

		// --- Generic mapping
		Child child = map(childEntity, Child.class);
		if (childEntity.getUser() != null) {
			child.setUserId(childEntity.getUser().getId());
		}

		if(childEntity.getListOfChildInterests() != null) {
			ArrayList<Integer> interest = new ArrayList<>();
			for (ChildInterestsEntity childInterestsEntity : childEntity.getListOfChildInterests()) {
				interest.add(childInterestsEntity.getClassesCategory().getId().intValue());
			}
			child.setInterest(interest.toArray(child.getInterest()));
		}
		
		return child;
	}

	public void mapChildToChildEntity(Child child, ChildEntity childEntity) {
		if (child == null) {
			return;
		}

		// --- Generic mapping
		map(child, childEntity);

		if (child.getUserId() != null) {
			UserEntity userEntity = new UserEntity();
			userEntity.setId(child.getUserId());
			childEntity.setUser(userEntity);
		} else {
			childEntity.setUser(null);
		}

	}

	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}