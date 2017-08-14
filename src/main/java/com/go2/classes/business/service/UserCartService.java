package com.go2.classes.business.service;

import java.util.List;
import java.util.Map;

import com.go2.classes.models.UserCart;
import com.go2.classes.models.jpa.UserBookingOrderEntity;

public interface UserCartService {

    UserCart findById(Long id);

    List<UserCart> findAll();

    UserCart save(UserCart entity);

    UserCart update(UserCart entity);

    UserCart create(UserCart entity);

    void delete(Long id);

    List<UserCart> findAllClassInstancesByStudent(Long userId);

    Double getToatlFees(Long userId);

    Integer getBookingsCount();

    Map<String, Object> applyCoupon(Long userCartId, String couponCode);

    List<Object> getAllBookingsByMonth(String fromDate, String toDate);
    
    List<Object> getAllBookingsByEducator(Long userId,String strFromDate,String strToDate);

    List<Object> getLastMonthBookings();

    Map<UserBookingOrderEntity, List<Map<String, Object>>> getAllUserBookings(Long userId);

    void bookAllCarts(Long userId, String transactionId);

    String getTransactionId(Long bookingId);

    void cancelBooking(Long bookingId);
    
    List<Object> getLastMonthBookingsByEducator(Long teacherId);
    
    Integer getBookingsCountByEducator(Long teacherId);

}
