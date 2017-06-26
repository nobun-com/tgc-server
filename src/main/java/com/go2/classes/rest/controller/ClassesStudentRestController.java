package com.go2.classes.rest.controller;

import java.util.LinkedList;
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
import com.go2.classes.models.ClassesStudent;
import com.go2.classes.business.service.ClassesStudentService;
import com.go2.classes.web.listitem.ClassesStudentListItem;

@Controller
public class ClassesStudentRestController {

	@Resource
	private ClassesStudentService classesStudentService;
	
	@RequestMapping( value="/items/classesStudent",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesStudentListItem> findAllAsListItems() {
		List<ClassesStudent> list = classesStudentService.findAll();
		List<ClassesStudentListItem> items = new LinkedList<ClassesStudentListItem>();
		for ( ClassesStudent classesStudent : list ) {
			items.add(new ClassesStudentListItem( classesStudent ) );
		}
		return items;
	}
	
	@RequestMapping( value="/classesStudent",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesStudent> findAll() {
		return classesStudentService.findAll();
	}

	@RequestMapping( value="/classesStudent/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesStudent findOne(@PathVariable("id") Integer id) {
		return classesStudentService.findById(id);
	}
	
	@RequestMapping( value="/classesStudent",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesStudent create(@RequestBody ClassesStudent classesStudent) {
		return classesStudentService.create(classesStudent);
	}

	@RequestMapping( value="/classesStudent/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesStudent update(@PathVariable("id") Integer id, @RequestBody ClassesStudent classesStudent) {
		return classesStudentService.update(classesStudent);
	}

	@RequestMapping( value="/classesStudent/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		classesStudentService.delete(id);
	}
	
}
