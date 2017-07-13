package com.go2.classes.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.ChildEntity;

public interface ChildJpaRepository extends PagingAndSortingRepository<ChildEntity, Long> {
	
	@Query(value = "select * from child C where C.student_id = :studentId", nativeQuery = true)
	Iterable<ChildEntity> findAllChildsByStudentId(@Param("studentId") Long studentId);
	
	@Query(value = "select distinct id from child", nativeQuery = true)
	List<String> getAllChildIds();

}
