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

import com.go2.classes.business.service.ChildService;
import com.go2.classes.models.Child;

@Controller
public class ChildRestController {

	@Resource
	private ChildService childService;
	
	@RequestMapping( value="/child",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Child> findAll() {
		return childService.findAll();
	}

	@RequestMapping( value="/child/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Child findOne(@PathVariable("id") Long id) {
		return childService.findById(id);
	}
	
	@RequestMapping( value="/child",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Child create(@RequestBody Child child) {
		return childService.create(child);
	}

	@RequestMapping( value="/child/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Child update(@PathVariable("id") Long id, @RequestBody Child child) {
		return childService.update(child);
	}

	@RequestMapping( value="/child/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		childService.delete(id);
	}
	
	@RequestMapping( value="/getAllChildsByUser/{userId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Child> getAllChildsByUser(@PathVariable("userId") Long userId) {
		return childService.getAllChildsByUser(userId);
	}
	
}
