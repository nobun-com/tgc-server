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
import com.go2.classes.models.TeacherClasses;
import com.go2.classes.business.service.TeacherClassesService;
import com.go2.classes.web.listitem.TeacherClassesListItem;

@Controller
public class TeacherClassesRestController {

	@Resource
	private TeacherClassesService teacherClassesService;
	
	@RequestMapping( value="/items/teacherClasses",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TeacherClassesListItem> findAllAsListItems() {
		List<TeacherClasses> list = teacherClassesService.findAll();
		List<TeacherClassesListItem> items = new LinkedList<TeacherClassesListItem>();
		for ( TeacherClasses teacherClasses : list ) {
			items.add(new TeacherClassesListItem( teacherClasses ) );
		}
		return items;
	}
	
	@RequestMapping( value="/teacherClasses",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TeacherClasses> findAll() {
		return teacherClassesService.findAll();
	}

	@RequestMapping( value="/teacherClasses/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TeacherClasses findOne(@PathVariable("id") Integer id) {
		return teacherClassesService.findById(id);
	}
	
	@RequestMapping( value="/teacherClasses",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TeacherClasses create(@RequestBody TeacherClasses teacherClasses) {
		return teacherClassesService.create(teacherClasses);
	}

	@RequestMapping( value="/teacherClasses/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TeacherClasses update(@PathVariable("id") Integer id, @RequestBody TeacherClasses teacherClasses) {
		return teacherClassesService.update(teacherClasses);
	}

	@RequestMapping( value="/teacherClasses/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		teacherClassesService.delete(id);
	}
	
}
