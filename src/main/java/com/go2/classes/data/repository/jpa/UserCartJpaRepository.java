package com.go2.classes.data.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.UserCartEntity;

public interface UserCartJpaRepository extends PagingAndSortingRepository<UserCartEntity, Long> {
	
	@Query(value="select UC.* from user_cart UC where UC.status = 'InCart' and UC.student_id = :studentId", nativeQuery = true)
	Iterable<UserCartEntity> findAllUserCartsByStudentId(@Param("studentId") Long studentId);
	
	@Query(value="select sum(CC.fee) from user_cart UC, time_table TT, classes CC where UC.status = 'InCart' and UC.timetable_id = TT.id and TT.classes_id = CC.id and UC.student_id = :studentId", nativeQuery = true)
	Double findFees(@Param("studentId") Long studentId);
	
	@Query(value="select count(UC.id) from user_cart UC where UC.status = 'InCart' and UC.student_id = :studentId", nativeQuery = true)
	Integer getUserCartSize(@Param("studentId") Long studentId);

}
