package com.go2.classes.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.ChildEntity;

public interface ChildJpaRepository extends PagingAndSortingRepository<ChildEntity, Long> {
	
	@Query(value = "select * from child C where C.user_id = :userId", nativeQuery = true)
	Iterable<ChildEntity> findAllChildsByUserId(@Param("userId") Long userId);
	
	@Query(value = "select distinct id from child", nativeQuery = true)
	List<String> getAllChildIds();

}
