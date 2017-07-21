package com.go2.classes.business.service;

import java.util.List;
import java.util.Map;

import com.go2.classes.models.UserCart;

public interface UserCartService { 

	UserCart findById( Long id  ) ;

	List<UserCart> findAll();

	UserCart save(UserCart entity);

	UserCart update(UserCart entity);

	UserCart create(UserCart entity);

	void delete( Long id );

	List<UserCart> findAllClassInstancesByStudent(Long userId);

	Double getToatlFees(Long userId);

	Integer bookAllCarts(Long userId);
	
	Integer getBookingsCount();

	Map<String, Object> applyCoupon(Long userCartId, String couponCode);
	
	List<Object> getAllBookingsByMonth(String fromDate,String toDate);
	
	List<Object> getLastMonthBookings();

}
