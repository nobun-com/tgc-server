package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.CouponService;
import com.go2.classes.business.service.UserCartService;
import com.go2.classes.business.service.mapping.UserCartServiceMapper;
import com.go2.classes.data.repository.jpa.StudentJpaRepository;
import com.go2.classes.data.repository.jpa.UserBookingOrderJpaRepository;
import com.go2.classes.data.repository.jpa.UserCartJpaRepository;
import com.go2.classes.models.Coupon;
import com.go2.classes.models.UserCart;
import com.go2.classes.models.jpa.UserBookingOrderEntity;
import com.go2.classes.models.jpa.UserCartEntity;

@Component
@Transactional
public class UserCartServiceImpl implements UserCartService {

	@Resource
	private ClassesService classesService;

	@Resource
	private CouponService couponService; // Injected by Spring

	@Resource
	private UserCartJpaRepository userCartJpaRepository;

	@Resource
	private StudentJpaRepository studentJpaRepository;

	@Resource
	UserBookingOrderJpaRepository userBookingOrderJpaRepository;

	@Resource
	private UserCartServiceMapper userCartServiceMapper;

	@Override
	public UserCart findById(Long id) {
		UserCartEntity userCartEntity = userCartJpaRepository.findOne(id);
		if (Objects.isNull(userCartEntity)) {
			throw new IllegalStateException("UserCart.not.found");
		}
		return userCartServiceMapper.mapUserCartEntityToUserCart(userCartEntity);
	}

	@Override
	public List<UserCart> findAll() {
		Iterable<UserCartEntity> entities = userCartJpaRepository.findAll();
		List<UserCart> beans = new ArrayList<UserCart>();
		for (UserCartEntity UserCartEntity : entities) {
			beans.add(userCartServiceMapper.mapUserCartEntityToUserCart(UserCartEntity));
		}
		return beans;
	}

	@Override
	public UserCart save(UserCart UserCart) {
		return update(UserCart);
	}

	@Override
	public UserCart create(UserCart userCart) {
		UserCartEntity userCartEntity = new UserCartEntity();
		userCartServiceMapper.mapUserCartToUserCartEntity(userCart, userCartEntity);
		UserCartEntity userCartEntitySaved = userCartJpaRepository.save(userCartEntity);
		return userCartServiceMapper.mapUserCartEntityToUserCart(userCartEntitySaved);
	}

	@Override
	public UserCart update(UserCart userCart) {
		UserCartEntity userCartEntity = userCartJpaRepository.findOne(userCart.getId());
		userCartServiceMapper.mapUserCartToUserCartEntity(userCart, userCartEntity);
		UserCartEntity userCartEntitySaved = userCartJpaRepository.save(userCartEntity);
		return userCartServiceMapper.mapUserCartEntityToUserCart(userCartEntitySaved);
	}

	@Override
	public List<UserCart> findAllClassInstancesByStudent(Long userId) {
		Iterable<UserCartEntity> entities = userCartJpaRepository.findAllUserCartsByStudentId(userId);
		List<UserCart> beans = new ArrayList<UserCart>();
		for (UserCartEntity UserCartEntity : entities) {
			beans.add(userCartServiceMapper.mapUserCartEntityToUserCart(UserCartEntity));
		}
		return beans;
	}

	@Override
	public void delete(Long id) {
		if (Objects.isNull(id)) {
			throw new IllegalStateException("UserCart.not.found");
		}
		userCartJpaRepository.delete(id);
	}

	public UserCartJpaRepository getUserCartJpaRepository() {
		return userCartJpaRepository;
	}

	public void setUserCartJpaRepository(UserCartJpaRepository userCartJpaRepository) {
		this.userCartJpaRepository = userCartJpaRepository;
	}

	public UserCartServiceMapper getUserCartServiceMapper() {
		return userCartServiceMapper;
	}

	public void setUserCartServiceMapper(UserCartServiceMapper userCartServiceMapper) {
		this.userCartServiceMapper = userCartServiceMapper;
	}

	@Override
	public Double getToatlFees(Long userId) {
		return userCartJpaRepository.findFees(userId);
	}

	@Override
	public Integer bookAllCarts(Long userId) {
		UserBookingOrderEntity userBookingOrderEntity = new UserBookingOrderEntity();
		userBookingOrderEntity.setStudent(studentJpaRepository.findOne(userId));
		userBookingOrderEntity.setDate(new Date());
		userBookingOrderEntity = userBookingOrderJpaRepository.save(userBookingOrderEntity);

		Iterable<UserCartEntity> inCart = userCartJpaRepository.findAllUserCartsByStudentId(userId);
		Integer classesCount = 0;
		for (UserCartEntity userCartEntity : inCart) {
			userCartEntity.setStatus("Booked");
			userCartEntity.setUserBookingOrderEntity(userBookingOrderEntity);
			userCartJpaRepository.save(userCartEntity);
			classesService.bookClass(userCartEntity.getTimeTable().getClasses());
			classesCount++;

		}
		userBookingOrderEntity.setClassesCount(classesCount);
		userBookingOrderJpaRepository.save(userBookingOrderEntity);
		return classesCount;
	}

	@Override
	public Map<String, Object> applyCoupon(Long userCartId, String couponCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		Coupon coupon = couponService.findById(couponCode);
		if (Objects.isNull(coupon)) {
			result.put("message", "invalid coupon");
		} else if (!coupon.getStatus().equals("Active")) {
			result.put("message", "coupon is " + coupon.getStatus());
		} else {
			UserCart userCart = findById(userCartId);
			userCart.setCoupon(coupon.getCode());
			userCart.setFinalCost(getDiscount(userCart.getFees(), coupon));
			update(userCart);
			result.put("success", true);
			result.put("message", "coupon applied fees will be $" + userCart.getFinalCost().toString());
			result.put("cost", userCart.getFinalCost());
			result.put("totalCost", getToatlFees(userCart.getUserId()));
		}
		return result;
	}

	private Double getDiscount(Double cost, Coupon coupon) {
		String rate = coupon.getValue();
		Double value;
		if (rate.contains("%")) {
			rate = rate.replaceAll("%", "");
			value = Double.parseDouble(rate);
			value = (value * cost) / 100;
		} else {
			value = Double.parseDouble(rate);
		}
		return cost - value;
	}

	@Override
	public Integer getBookingsCount() {
		return userBookingOrderJpaRepository.getBookingsCount();
	}

	@Override
	public List<Object> getLastMonthBookings() {
		return userBookingOrderJpaRepository.getLastMonthBookings();
	}

	@Override
	public List<Object> getAllBookingsByMonth(String fromDate, String toDate) {
		return userBookingOrderJpaRepository.getAllBookingsByMonth(fromDate, toDate);
	}

}
