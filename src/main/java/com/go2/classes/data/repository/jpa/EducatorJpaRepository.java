package com.go2.classes.data.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.go2.classes.models.jpa.EducatorEntity;

public interface EducatorJpaRepository extends PagingAndSortingRepository<EducatorEntity, Long> {

	EducatorEntity findByEmail(String email);

	@Query(value = "select count(id) from educator", nativeQuery = true)
	Integer getEducatorsCount();

}
