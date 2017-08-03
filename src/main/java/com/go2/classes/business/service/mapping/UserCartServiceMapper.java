package com.go2.classes.business.service.mapping;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.common.Utilities;
import com.go2.classes.models.UserCart;
import com.go2.classes.models.jpa.CouponEntity;
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
	if (userCartEntity == null) {
	    return null;
	}
	UserCart userCart = map(userCartEntity, UserCart.class);

	if (userCartEntity.getStudent() != null) {
	    userCart.setUserId(userCartEntity.getStudent().getId());
	}

	if (userCartEntity.getTimeTable() != null) {
	    userCart.setTimeTableId(userCartEntity.getTimeTable().getId());
	}

	return userCart;
    }

    public void mapUserCartToUserCartEntity(UserCart userCart, UserCartEntity userCartEntity) {
	if (userCart == null) {
	    return;
	}

	if (hasLinkToUser(userCart)) {
	    StudentEntity student = new StudentEntity();
	    student.setId(userCart.getUserId());
	    userCartEntity.setStudent(student);
	} else {
	    userCartEntity.setStudent(null);
	}

	if (hasLinkToCoupon(userCart)) {
	    CouponEntity coupon = new CouponEntity();
	    coupon.setCode(userCart.getCoupon());
	    userCartEntity.setCouponEntity(coupon);
	} else {
	    userCartEntity.setCouponEntity(null);
	}

	if (hasLinkToTimeTable(userCart)) {
	    TimeTableEntity timeTable = new TimeTableEntity();
	    timeTable.setId(userCart.getTimeTableId());
	    userCartEntity.setTimeTable(timeTable);
	    ;
	} else {
	    userCartEntity.setTimeTable(null);
	}

	map(userCart, userCartEntity);
    }

    private boolean hasLinkToCoupon(UserCart userCart) {
	if (userCart.getCoupon() != null) {
	    return true;
	}
	return false;
    }

    private boolean hasLinkToTimeTable(UserCart userCart) {
	if (userCart.getTimeTableId() != null) {
	    return true;
	}
	return false;
    }

    private boolean hasLinkToUser(UserCart userCart) {
	if (userCart.getUserId() != null) {
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

    public Map<String, Object> mapTimeTableEntityToJSONMap(UserCartEntity cart) {
	Map<String, Object> result = new HashMap<String, Object>();
	TimeTableEntity timeTableEntity = cart.getTimeTable();
	CouponEntity coupon = cart.getCouponEntity();
	result.put("coupon", coupon == null ? "N/A" : coupon.getCode());
	result.put("finalCost", "HKD" + cart.getFinalCost());
	result.put("id", cart.getId());
	result.put("cost", cart.getFees());
	result.put("calssName", timeTableEntity.getClasses().getClassName());
	result.put("day", Utilities.dayOnly.format(timeTableEntity.getStartTime()));
	result.put("time", Utilities.timeOnly.format(timeTableEntity.getStartTime()) + " to " + Utilities.timeOnly.format(timeTableEntity.getEndTime()));
	result.put("date", Utilities.dateWithoutTime.format(timeTableEntity.getStartTime()));
	result.put("fee", "HKD" + timeTableEntity.getClasses().getFee());
	result.put("slots", timeTableEntity.getClasses().getSlotsAvailable() + " SLOTS LEFT");
	return result;
    }

}