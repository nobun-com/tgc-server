package com.go2.classes.data.repository.jpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.UserCartEntity;

public interface UserCartJpaRepository extends PagingAndSortingRepository<UserCartEntity, Long> {
	
	@Query(value="select UC.* from user_cart UC where UC.status = 'InCart' and UC.user_id = :userId", nativeQuery = true)
	Iterable<UserCartEntity> findAllUserCartsByUserId(@Param("userId") Long userId);
	
	@Query(value="select sum(UC.final_cost) from user_cart UC where UC.status = 'InCart' and UC.user_id = :userId", nativeQuery = true)
	Double findFees(@Param("userId") Long userId);
	
	@Query(value="select count(UC.id) from user_cart UC where UC.status = 'InCart' and UC.user_id = :userId", nativeQuery = true)
	Integer getUserCartSize(@Param("userId") Long userId);

	@Modifying
	@Query(value="update user_cart UC set UC.status = 'invalid' where UC.timetable_id = :timeTableId", nativeQuery = true)
	void invalidByTimeTableId(@Param("timeTableId") Long timeTableId);

}
