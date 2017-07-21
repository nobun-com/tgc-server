package com.go2.classes.data.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.go2.classes.models.jpa.TeacherEntity;

public interface TeacherJpaRepository extends PagingAndSortingRepository<TeacherEntity, Long> {

	TeacherEntity findByEmail(String email);

	@Query(value = "select count(id) from teacher", nativeQuery = true)
	Integer getTeachersCount();

}
