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
import com.go2.classes.models.StudentClasses;
import com.go2.classes.business.service.StudentClassesService;
import com.go2.classes.web.listitem.StudentClassesListItem;

@Controller
public class StudentClassesRestController {

	@Resource
	private StudentClassesService studentClassesService;
	
	@RequestMapping( value="/items/studentClasses",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<StudentClassesListItem> findAllAsListItems() {
		List<StudentClasses> list = studentClassesService.findAll();
		List<StudentClassesListItem> items = new LinkedList<StudentClassesListItem>();
		for ( StudentClasses studentClasses : list ) {
			items.add(new StudentClassesListItem( studentClasses ) );
		}
		return items;
	}
	
	@RequestMapping( value="/studentClasses",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<StudentClasses> findAll() {
		return studentClassesService.findAll();
	}

	@RequestMapping( value="/studentClasses/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public StudentClasses findOne(@PathVariable("id") Integer id) {
		return studentClassesService.findById(id);
	}
	
	@RequestMapping( value="/studentClasses",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public StudentClasses create(@RequestBody StudentClasses studentClasses) {
		return studentClassesService.create(studentClasses);
	}

	@RequestMapping( value="/studentClasses/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public StudentClasses update(@PathVariable("id") Integer id, @RequestBody StudentClasses studentClasses) {
		return studentClassesService.update(studentClasses);
	}

	@RequestMapping( value="/studentClasses/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		studentClassesService.delete(id);
	}
	
}
