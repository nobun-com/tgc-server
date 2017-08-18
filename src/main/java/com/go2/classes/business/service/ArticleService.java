package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Article;

public interface ArticleService { 

	Article findById( Long id  ) ;

	List<Article> findAll();

	Article save(Article entity);

	Article update(Article entity);

	Article create(Article entity);

	void delete( Long id );

	List<String> findAllArticleCategory();

	List<Article> getAllPublished();

	List<Article> getFiveFeatured();
}
