package com.go2.classes.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.go2.classes.models.jpa.ClassesCategoryEntity;

public interface ClassesCategoryJpaRepository extends PagingAndSortingRepository<ClassesCategoryEntity, Long> {

	@Query(value = "select distinct category from article_category ", nativeQuery = true)
	List<String> findAllCategories();
}
