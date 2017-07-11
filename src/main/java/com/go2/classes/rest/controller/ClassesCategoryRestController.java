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

import com.go2.classes.business.service.ClassesCategoryService;
import com.go2.classes.models.ClassesCategory;

@Controller
public class ClassesCategoryRestController {

	@Resource
	private ClassesCategoryService classesCategoryService;
	
	@RequestMapping( value="/getAllClassesCategory",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesCategory> findAll() {
		return classesCategoryService.findAll();
	}

	@RequestMapping( value="/getClassesCategory/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesCategory findOne(@PathVariable("id") Long id) {
		return classesCategoryService.findById(id);
	}
	
	@RequestMapping( value="/createClassesCategory",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesCategory create(@RequestBody ClassesCategory classesCategory) {
		return classesCategoryService.create(classesCategory);
	}

	@RequestMapping( value="/updateClassesCategory",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesCategory update(@RequestBody ClassesCategory classesCategory) {
		return classesCategoryService.update(classesCategory);
	}

	@RequestMapping( value="/deleteClassesCategory/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		classesCategoryService.delete(id);
	}
	
}
