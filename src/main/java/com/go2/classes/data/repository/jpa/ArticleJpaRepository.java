package com.go2.classes.data.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.go2.classes.models.jpa.ArticleEntity;

public interface ArticleJpaRepository extends PagingAndSortingRepository<ArticleEntity, Long> {

    @Query(value="select * from article A where A.published = true order by A.post_date DESC", nativeQuery=true)
    Iterable<ArticleEntity> findAllPublished();

    @Query(value="select * from article A where A.featured = true order by A.created_date DESC limit 5", nativeQuery=true)
    Iterable<ArticleEntity> findFiveFeatured();

}
