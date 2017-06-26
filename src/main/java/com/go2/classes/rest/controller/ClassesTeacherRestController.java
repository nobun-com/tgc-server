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
import com.go2.classes.models.ClassesTeacher;
import com.go2.classes.business.service.ClassesTeacherService;
import com.go2.classes.web.listitem.ClassesTeacherListItem;

@Controller
public class ClassesTeacherRestController {

	@Resource
	private ClassesTeacherService classesTeacherService;
	
	@RequestMapping( value="/items/classesTeacher",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesTeacherListItem> findAllAsListItems() {
		List<ClassesTeacher> list = classesTeacherService.findAll();
		List<ClassesTeacherListItem> items = new LinkedList<ClassesTeacherListItem>();
		for ( ClassesTeacher classesTeacher : list ) {
			items.add(new ClassesTeacherListItem( classesTeacher ) );
		}
		return items;
	}
	
	@RequestMapping( value="/classesTeacher",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesTeacher> findAll() {
		return classesTeacherService.findAll();
	}

	@RequestMapping( value="/classesTeacher/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesTeacher findOne(@PathVariable("id") Integer id) {
		return classesTeacherService.findById(id);
	}
	
	@RequestMapping( value="/classesTeacher",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesTeacher create(@RequestBody ClassesTeacher classesTeacher) {
		return classesTeacherService.create(classesTeacher);
	}

	@RequestMapping( value="/classesTeacher/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesTeacher update(@PathVariable("id") Integer id, @RequestBody ClassesTeacher classesTeacher) {
		return classesTeacherService.update(classesTeacher);
	}

	@RequestMapping( value="/classesTeacher/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		classesTeacherService.delete(id);
	}
	
}
