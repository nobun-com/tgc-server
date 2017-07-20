package com.go2.classes.data.repository.jpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.ClassesEntity;

public interface ClassesJpaRepository extends PagingAndSortingRepository<ClassesEntity, Long> {

	@Query(value="select * from classes CC where CC.status = 'valid' and CC.center_id :centerId", nativeQuery = true)
	Iterable<ClassesEntity> findAllClassesByCenterId(@Param("centerId") Long centerId);

	@Query(value="select * from classes CC where CC.status = 'valid' and CC.teacher_id :teacherId", nativeQuery = true)
	Iterable<ClassesEntity> findAllClassesByTeacherId(@Param("teacherId") Long teacherId);

	@Query(value="select * from classes CC where CC.status = 'valid'", nativeQuery = true)
	Iterable<ClassesEntity> findAll();

	@Modifying
	@Query(value="update classes CC set CC.status = 'valid' where CC.id = :id", nativeQuery = true)
	void invalid(@Param("id") Long id);

}
