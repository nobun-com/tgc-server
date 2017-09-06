package com.go2.classes.data.repository.jpa;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.ClassesEntity;

public interface ClassesJpaRepository extends PagingAndSortingRepository<ClassesEntity, Long> {

	@Query(value="select * from classes CC where CC.status = 'valid' and CC.center_id =:centerId", nativeQuery = true)
	Iterable<ClassesEntity> findAllClassesByCenterId(@Param("centerId") Long centerId);

	@Query(value="select * from classes CC where CC.status = 'valid' and CC.educator_id =:educatorId", nativeQuery = true)
	Iterable<ClassesEntity> findAllClassesByEducatorId(@Param("educatorId") Long educatorId);

	@Query(value="select * from classes CC where CC.status = 'valid'", nativeQuery = true)
	Iterable<ClassesEntity> findAll();

	@Modifying
	@Query(value="update classes CC set CC.status = 'invalid' where CC.id = :id", nativeQuery = true)
	void invalid(@Param("id") Long id);
	
	@Query(value="select count(CC.id) from classes CC where CC.status != 'invalid'", nativeQuery = true)
	Integer getActiveClassesCount();
	
	@Query(value="select count(CC.id) from classes CC where CC.status = 'valid' and CC.educator_id =:educatorId", nativeQuery = true)
	Integer getActiveClassesCountByEducator(@Param("educatorId") Long educatorId);
	
	@Query(value="select distinct UC.user_id from user_cart UC, time_table TT where UC.timetable_id = TT.id and TT.classes_id = :classesId", nativeQuery = true)
	List<BigInteger> getUsersOfClass(@Param("classesId") Long classesId);
}
