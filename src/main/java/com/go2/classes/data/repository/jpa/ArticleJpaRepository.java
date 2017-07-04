package com.go2.classes.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.go2.classes.models.jpa.ArticleEntity;

public interface ArticleJpaRepository extends PagingAndSortingRepository<ArticleEntity, Long> {

}
