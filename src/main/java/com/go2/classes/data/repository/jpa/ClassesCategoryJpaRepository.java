package com.go2.classes.data.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.go2.classes.models.jpa.ClassesCategoryEntity;

public interface ClassesCategoryJpaRepository extends PagingAndSortingRepository<ClassesCategoryEntity, Long> {

    @Query(value = "select * from classes_category where id = :id", nativeQuery = true)
    ClassesCategoryEntity findById(@Param("id") Long id);
}
