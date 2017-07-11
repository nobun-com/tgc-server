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

import com.go2.classes.business.service.ChildInterestsService;
import com.go2.classes.models.ChildInterests;

@Controller
public class ChildInterestsRestController {

	@Resource
	private ChildInterestsService childInterestsService;
	
	@RequestMapping( value="/childInterests",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ChildInterests> findAll() {
		return childInterestsService.findAll();
	}

	@RequestMapping( value="/childInterests/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChildInterests findOne(@PathVariable("id") Long id) {
		return childInterestsService.findById(id);
	}
	
	@RequestMapping( value="/childInterests",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChildInterests create(@RequestBody ChildInterests childInterests) {
		return childInterestsService.create(childInterests);
	}

	@RequestMapping( value="/childInterests/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ChildInterests update(@PathVariable("id") Long id, @RequestBody ChildInterests childInterests) {
		return childInterestsService.update(childInterests);
	}

	@RequestMapping( value="/childInterests/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		childInterestsService.delete(id);
	}
	
	/*@RequestMapping( value="/getAllChildInterestssByStudent/{studentId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ChildInterests> getAllChildInterestssByStudent(@PathVariable("studentId") Long studentId) {
		return childInterestsService.getAllChildInterestsByStudent(studentId);
	}*/
	
}
