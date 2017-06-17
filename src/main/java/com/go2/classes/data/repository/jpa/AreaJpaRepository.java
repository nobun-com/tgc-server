package com.go2.classes.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.AreaEntity;

/**
 * Repository : Area.
 */
public interface AreaJpaRepository extends PagingAndSortingRepository<AreaEntity, Long> {

	@Query(value = "select distinct area from area", nativeQuery = true)
	List<String> getAllDistinctArea();

	@Query(value = "select district from area where area = :area", nativeQuery = true)
	List<String> getAllDistricts(@Param("area") String area);

}
