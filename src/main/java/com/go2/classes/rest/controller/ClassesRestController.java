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
import com.go2.classes.models.Classes;
import com.go2.classes.business.service.ClassesService;
import com.go2.classes.web.listitem.ClassesListItem;

@Controller
public class ClassesRestController {

	@Resource
	private ClassesService classesService;
	
	@RequestMapping( value="/items/classes",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesListItem> findAllAsListItems() {
		List<Classes> list = classesService.findAll();
		List<ClassesListItem> items = new LinkedList<ClassesListItem>();
		for ( Classes classes : list ) {
			items.add(new ClassesListItem( classes ) );
		}
		return items;
	}
	
	@RequestMapping( value="/getAllClasses",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Classes> findAll() {
		return classesService.findAll();
	}

	@RequestMapping( value="/getAllClassesByCenter/{centerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Classes> getAllClassesByCenter(@PathVariable("centerId") Long centerId) {
		return classesService.getAllClassesByCenter(centerId);
	}

	@RequestMapping( value="/getAllClassesByTeacher/{teacherId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Classes> getAllClassesByTeacher(@PathVariable("teacherId") Long teacherId) {
		return classesService.getAllClassesByTeacher(teacherId);
	}

	@RequestMapping( value="/getClass/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Classes findOne(@PathVariable("id") Long id) {
		return classesService.findById(id);
	}
	
	@RequestMapping( value="/createClass",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Classes create(@RequestBody Classes classes) {
		return classesService.create(classes);
	}

	@RequestMapping( value="/updateClass",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Classes update(@RequestBody Classes classes) {
		return classesService.update(classes);
	}

	@RequestMapping( value="/deleteClass/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		classesService.delete(id);
	}
	
}
