package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Coupon;

public interface CouponService { 

	Coupon findById(String id) ;

	List<Coupon> findAll();

	Coupon save(Coupon entity);

	Coupon update(Coupon entity);

	Coupon create(Coupon entity);

	void delete(String id );
	
	void processCoupons();
}
