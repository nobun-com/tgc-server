package com.go2.classes.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.UserBookingOrderEntity;

public interface UserBookingOrderJpaRepository extends PagingAndSortingRepository<UserBookingOrderEntity, Long> {

	@Query(value = "select sum(UCO.classes_count) from user_booking_order UCO where UCO.date > (NOW() - INTERVAL 1 MONTH)", nativeQuery = true)
	Integer getBookingsCount();

	@Query(value = "select UCO.`date`,CC.class_name,TT.start_time,TT.end_time,SD.name,UC.fees,UC.coupon_code,UC.final_cost,C.center_name,UCO.id from user_cart UC inner join user SD on UC.user_id = SD.id inner join user_booking_order UCO on UCO.user_id=SD.id inner join time_table TT on UC.timetable_id = TT.id inner join classes CC on TT.classes_id = CC.id inner join center C on C.id=CC.center_id where UC.status='Booked' and UCO.date between :fromDate and :toDate", nativeQuery = true)
	List<Object> getAllBookingsByMonth(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "select date(UCO.date) as dateOnly,sum(UCO.classes_count) from user_booking_order UCO where UCO.date > (NOW() - INTERVAL 1 MONTH) group by dateOnly", nativeQuery = true)
	List<Object> getLastMonthBookings();

	@Query(value = "select date(ubo.date) as dateOnly,sum(ubo.classes_count) from user_booking_order ubo RIGHT join user_cart uc on uc.order_id = ubo.id RIGHT join time_table tt on tt.id = uc.timetable_id RIGHT join classes c on c.id = tt.classes_id  and c.educator_id= :educatorId where ubo.date > (NOW() - INTERVAL 1 MONTH) group by dateOnly;", nativeQuery = true)
	List<Object> getLastMonthBookingsByEducator(@Param("educatorId") Long educatorId);

	@Query(value = "select sum(ubo.classes_count) from user_booking_order ubo RIGHT join user_cart uc on uc.order_id = ubo.id RIGHT join time_table tt on tt.id = uc.timetable_id RIGHT join classes c on c.id = tt.classes_id  and c.educator_id= :educatorId where ubo.date > (NOW() - INTERVAL 1 MONTH);", nativeQuery = true)
	Integer getBookingsCountByEducator(@Param("educatorId") Long educatorId);

	@Query(value = "select UBO.* from user_booking_order UBO where UBO.status = 'Done' and UBO.user_id = :userId", nativeQuery = true)
	List<UserBookingOrderEntity> getAllByUserId(@Param("userId") Long userId);
	
	
	@Query(value = "select UCO.`date`,CC.class_name,TT.start_time,TT.end_time,SD.name,UC.fees,UC.coupon_code,UC.final_cost,C.center_name,UCO.id from user_cart UC inner join user SD on UC.user_id = SD.id inner join user_booking_order UCO on UCO.user_id=SD.id inner join time_table TT on UC.timetable_id = TT.id inner join classes CC on TT.classes_id = CC.id AND CC.educator_id = :educatorId inner join center C on C.id=CC.center_id where UC.status='Booked' and UCO.date between :fromDate and :toDate", nativeQuery = true)
	List<Object> getAllBookingsByEducator(@Param("educatorId") Long educatorId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);


}
