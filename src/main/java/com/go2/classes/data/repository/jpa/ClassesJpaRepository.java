package com.go2.classes.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.go2.classes.models.jpa.ClassesEntity;

public interface ClassesJpaRepository extends PagingAndSortingRepository<ClassesEntity, Long> {

	Iterable<ClassesEntity> findAllClassesByCenterId(Long centerId);

}
