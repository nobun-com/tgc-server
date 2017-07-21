package com.go2.classes.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.UserBookingOrderEntity;

public interface UserBookingOrderJpaRepository extends PagingAndSortingRepository<UserBookingOrderEntity, Long> {

	@Query(value = "select sum(UCO.classes_count) from user_booking_order UCO where UCO.date > (NOW() - INTERVAL 1 MONTH)", nativeQuery = true)
	Integer getBookingsCount();

	@Query(value = "select UC.`date`,CC.class_name,TT.start_time,TT.end_time,SD.name,UC.fees,UC.coupon_code,UC.final_cost from user_cart UC inner join student SD on UC.student_id = SD.id inner join time_table TT on UC.timetable_id = TT.id INNER JOIN classes CC on TT.classes_id = CC.id where UC.status='Booked' and UC.date between :fromDate and :toDate", nativeQuery = true)
	List<Object> getAllBookingsByMonth(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	
	@Query(value = "select UCO.date,UCO.classes_count from user_booking_order UCO where UCO.date > (NOW() - INTERVAL 1 MONTH)", nativeQuery = true)
	List<Object> getLastMonthBookings();

}
