package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.Coupon;
import com.go2.classes.models.jpa.CouponEntity;
import com.go2.classes.business.service.CouponService;
import com.go2.classes.business.service.mapping.CouponServiceMapper;
import com.go2.classes.data.repository.jpa.CouponJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CouponServiceImpl implements CouponService {

	@Resource
	private CouponJpaRepository couponJpaRepository;

	@Resource
	private CouponServiceMapper couponServiceMapper;
	
	@Override
	public Coupon findById(String code) {
		CouponEntity couponEntity = couponJpaRepository.findOne(code);
		return couponServiceMapper.mapCouponEntityToCoupon(couponEntity);
	}

	@Override
	public List<Coupon> findAll() {
		Iterable<CouponEntity> entities = couponJpaRepository.findAll();
		List<Coupon> beans = new ArrayList<Coupon>();
		for(CouponEntity couponEntity : entities) {
			beans.add(couponServiceMapper.mapCouponEntityToCoupon(couponEntity));
		}
		return beans;
	}

	@Override
	public Coupon save(Coupon coupon) {
		return update(coupon) ;
	}

	@Override
	public Coupon create(Coupon coupon) {
		CouponEntity couponEntity = null;
		if(coupon.getCode() != null) {
			couponEntity = couponJpaRepository.findOne(coupon.getCode());
		}
		if(couponEntity != null ) {
			throw new IllegalStateException("coupon.already.exists");
		}
		couponEntity = new CouponEntity();
		couponServiceMapper.mapCouponToCouponEntity(coupon, couponEntity);
		processCoupon(couponEntity);
		CouponEntity couponEntitySaved = couponJpaRepository.save(couponEntity);
		return couponServiceMapper.mapCouponEntityToCoupon(couponEntitySaved);
	}
	
	public void processCoupons() {
		Iterable<CouponEntity> entities = couponJpaRepository.findAllNonExpired();
		for(CouponEntity couponEntity : entities) {
			processCoupon(couponEntity);
			couponJpaRepository.save(couponEntity);
		}
	}

	private void processCoupon(CouponEntity coupon) {
		if(coupon.getExpiryDate().before(coupon.getStartDate())){
			throw new IllegalStateException("expiry date shuld gretter than start date");
		}
		Date now = new Date();
		if(now.before(coupon.getStartDate())) {
			coupon.setStatus("Pending");
		} else if(now.before(coupon.getExpiryDate())) {
			coupon.setStatus("Active");
		} else {
			coupon.setStatus("Expired");
		}
	}

	@Override
	public Coupon update(Coupon coupon) {
		CouponEntity couponEntity = null;
		if(!Objects.isNull(coupon.getCode())) {
			couponEntity = couponJpaRepository.findOne(coupon.getCode());
		}
		if(Objects.isNull(couponEntity)) {
			throw new IllegalStateException("coupon.not.found");
		}
		couponServiceMapper.mapCouponToCouponEntity(coupon, couponEntity);
		processCoupon(couponEntity);
		CouponEntity couponEntitySaved = couponJpaRepository.save(couponEntity);
		return couponServiceMapper.mapCouponEntityToCoupon(couponEntitySaved);
	}

	@Override
	public void delete(String code) {
		couponJpaRepository.delete(code);
	}

	public CouponJpaRepository getCouponJpaRepository() {
		return couponJpaRepository;
	}

	public void setCouponJpaRepository(CouponJpaRepository couponJpaRepository) {
		this.couponJpaRepository = couponJpaRepository;
	}

	public CouponServiceMapper getCouponServiceMapper() {
		return couponServiceMapper;
	}

	public void setCouponServiceMapper(CouponServiceMapper couponServiceMapper) {
		this.couponServiceMapper = couponServiceMapper;
	}

}
