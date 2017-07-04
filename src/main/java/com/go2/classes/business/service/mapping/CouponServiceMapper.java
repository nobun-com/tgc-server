package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Coupon;
import com.go2.classes.models.jpa.CouponEntity;

@Component
public class CouponServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public CouponServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Coupon mapCouponEntityToCoupon(CouponEntity couponEntity) {
		if(couponEntity == null) {
			return null;
		}
		Coupon coupon = map(couponEntity, Coupon.class);
		return coupon;
	}
	
	public void mapCouponToCouponEntity(Coupon coupon, CouponEntity couponEntity) {
		if(coupon == null) {
			return;
		}
		map(coupon, couponEntity);
	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}