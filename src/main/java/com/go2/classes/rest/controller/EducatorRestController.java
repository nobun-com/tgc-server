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
import com.go2.classes.models.Educator;
import com.go2.classes.business.service.EducatorService;

@Controller
public class EducatorRestController {

	@Resource
	private EducatorService educatorService;
	
	@RequestMapping( value="/getAllEducators",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Educator> findAll() {
		return educatorService.findAll();
	}

	@RequestMapping( value="/getEducator/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Educator findOne(@PathVariable("id") Long id) {
		return educatorService.findById(id);
	}
	
	@RequestMapping( value="/createEducator",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Educator create(@RequestBody Educator educator) {
		return educatorService.create(educator);
	}

	@RequestMapping( value="/updateEducator",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Educator update(@RequestBody Educator educator) {
		return educatorService.update(educator);
	}

	@RequestMapping( value="/deleteEducator/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		educatorService.delete(id);
	}
	
}
