package com.go2.classes.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.TimeTableEntity;

public interface TimeTableJpaRepository extends PagingAndSortingRepository<TimeTableEntity, Long> {

	@Query(value="select * from time_table tt where tt.classes_id IN (select id from classes cc where cc.center_id = :centerId) and tt.status != 'invalid'", nativeQuery=true)
	List<TimeTableEntity> findByCenterId(@Param("centerId") Long centerId);

	@Query(value="select * from time_table tt where tt.status != 'invalid' and tt.classes_id = :classId", nativeQuery=true)
	Iterable<TimeTableEntity> findAllByclassesId(@Param("classId") Long classId);

	@Query(value="select * from time_table tt where tt.status != 'invalid')", nativeQuery=true)
	Iterable<TimeTableEntity> findAll();
}
