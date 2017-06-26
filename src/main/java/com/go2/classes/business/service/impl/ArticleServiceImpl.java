package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.Article;
import com.go2.classes.models.jpa.ArticleEntity;
import com.go2.classes.business.service.ArticleService;
import com.go2.classes.business.service.mapping.ArticleServiceMapper;
import com.go2.classes.data.repository.jpa.ArticleCategoryJpaRepository;
import com.go2.classes.data.repository.jpa.ArticleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleJpaRepository articleJpaRepository;

	@Resource
	private ArticleCategoryJpaRepository articleCategoryJpaRepository;

	@Resource
	private ArticleServiceMapper articleServiceMapper;
	
	@Override
	public Article findById(Long id) {
		ArticleEntity articleEntity = articleJpaRepository.findOne(id);
		return articleServiceMapper.mapArticleEntityToArticle(articleEntity);
	}

	@Override
	public List<Article> findAll() {
		Iterable<ArticleEntity> entities = articleJpaRepository.findAll();
		List<Article> beans = new ArrayList<Article>();
		for(ArticleEntity articleEntity : entities) {
			beans.add(articleServiceMapper.mapArticleEntityToArticle(articleEntity));
		}
		return beans;
	}

	@Override
	public Article save(Article article) {
		return update(article) ;
	}

	@Override
	public Article create(Article article) {
		ArticleEntity articleEntity = null;
		if(article.getId() != null) {
			articleEntity = articleJpaRepository.findOne(article.getId());
		}
		if( articleEntity != null ) {
			throw new IllegalStateException("article.already.exists");
		}
		articleEntity = new ArticleEntity();
		articleServiceMapper.mapArticleToArticleEntity(article, articleEntity);
		ArticleEntity articleEntitySaved = articleJpaRepository.save(articleEntity);
		return articleServiceMapper.mapArticleEntityToArticle(articleEntitySaved);
	}

	@Override
	public Article update(Article article) {
		ArticleEntity articleEntity = null;
		if(!Objects.isNull(article.getId())) {
			articleEntity = articleJpaRepository.findOne(article.getId());
		}
		if(Objects.isNull(articleEntity)) {
			throw new IllegalStateException("article.not.found");
		}
		articleServiceMapper.mapArticleToArticleEntity(article, articleEntity);
		ArticleEntity articleEntitySaved = articleJpaRepository.save(articleEntity);
		return articleServiceMapper.mapArticleEntityToArticle(articleEntitySaved);
	}

	@Override
	public void delete(Long id) {
		articleJpaRepository.delete(id);
	}

	public ArticleJpaRepository getArticleJpaRepository() {
		return articleJpaRepository;
	}

	public void setArticleJpaRepository(ArticleJpaRepository articleJpaRepository) {
		this.articleJpaRepository = articleJpaRepository;
	}

	public ArticleServiceMapper getArticleServiceMapper() {
		return articleServiceMapper;
	}

	public void setArticleServiceMapper(ArticleServiceMapper articleServiceMapper) {
		this.articleServiceMapper = articleServiceMapper;
	}

	@Override
	public List<String> findAllArticleCategory() {
		return articleCategoryJpaRepository.findAllCategories();
	}

}
