package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Article;
import com.go2.classes.models.jpa.ArticleEntity;

@Component
public class ArticleServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ArticleServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Article mapArticleEntityToArticle(ArticleEntity articleEntity) {
		if(articleEntity == null) {
			return null;
		}
		Article article = map(articleEntity, Article.class);
		return article;
	}
	
	public void mapArticleToArticleEntity(Article article, ArticleEntity articleEntity) {
		if(article == null) {
			return;
		}
		map(article, articleEntity);
	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}