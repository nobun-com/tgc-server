package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.models.UserCart;
import com.go2.classes.models.jpa.StudentEntity;
import com.go2.classes.models.jpa.TimeTableEntity;
import com.go2.classes.models.jpa.UserCartEntity;

@Component
public class UserCartServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public UserCartServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public UserCart mapUserCartEntityToUserCart(UserCartEntity userCartEntity) {
		if(userCartEntity == null) {
			return null;
		}
		UserCart userCart = map(userCartEntity, UserCart.class);
		
		if(userCartEntity.getStudent() != null) {
			userCart.setUserId(userCartEntity.getStudent().getId());
		}

		if(userCartEntity.getTimeTable() != null) {
			userCart.setTimeTableId(userCartEntity.getTimeTable().getId());
		}

		return userCart;
	}
	
	public void mapUserCartToUserCartEntity(UserCart userCart, UserCartEntity userCartEntity) {
		if(userCart == null) {
			return;
		}

		if(hasLinkToUser(userCart)) {
			StudentEntity student = new StudentEntity();
			student.setId(userCart.getUserId());
			userCartEntity.setStudent(student);
		} else {
			userCartEntity.setStudent(null);
		}

		if(hasLinkToTimeTable(userCart)) {
			TimeTableEntity timeTable = new TimeTableEntity();
			timeTable.setId(userCart.getTimeTableId());
			userCartEntity.setTimeTable(timeTable);;
		} else {
			userCartEntity.setTimeTable(null);
		}

		map(userCart, userCartEntity);
	}
	
	private boolean hasLinkToTimeTable(UserCart userCart) {
		if(userCart.getTimeTableId() != null) {
			return true;
		}
		return false;
	}
	private boolean hasLinkToUser(UserCart userCart) {
		if(userCart.getUserId() != null) {
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