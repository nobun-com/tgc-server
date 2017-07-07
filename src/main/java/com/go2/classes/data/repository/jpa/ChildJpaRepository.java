package com.go2.classes.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.go2.classes.models.jpa.ChildEntity;

public interface ChildJpaRepository extends PagingAndSortingRepository<ChildEntity, Long> {
	
	Iterable<ChildEntity> findAllChildsByStudentId(Long studentId);

}
