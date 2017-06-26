package com.go2.classes.rest.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.go2.classes.models.Article;
import com.go2.classes.business.service.ArticleService;

@Controller
public class ArticleRestController {

	@Resource
	private ArticleService articleService;
		
	@RequestMapping( value="/getAllArticles",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Article> findAll() {
		return articleService.findAll();
	}

	@RequestMapping( value="/getAllArticleCategories",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<String> findAllArticleCategory() {
		return articleService.findAllArticleCategory();
	}

	@RequestMapping( value="/getArticle/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Article findOne(@PathVariable("id") Long id) {
		return articleService.findById(id);
	}

	@RequestMapping( value="/createArticle",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Article create(@RequestBody Article article) {
		return articleService.create(article);
	}

	@RequestMapping( value="/updateArticle",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Article update(@RequestBody Article article) {
		return articleService.update(article);
	}

	@RequestMapping( value="/deleteArticle/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		articleService.delete(id);
	}
}
