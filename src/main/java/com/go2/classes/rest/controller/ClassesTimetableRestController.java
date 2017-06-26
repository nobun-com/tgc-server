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
import com.go2.classes.models.ClassesTimetable;
import com.go2.classes.business.service.ClassesTimetableService;
import com.go2.classes.web.listitem.ClassesTimetableListItem;

@Controller
public class ClassesTimetableRestController {

	@Resource
	private ClassesTimetableService classesTimetableService;
	
	@RequestMapping( value="/items/classesTimetable",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesTimetableListItem> findAllAsListItems() {
		List<ClassesTimetable> list = classesTimetableService.findAll();
		List<ClassesTimetableListItem> items = new LinkedList<ClassesTimetableListItem>();
		for ( ClassesTimetable classesTimetable : list ) {
			items.add(new ClassesTimetableListItem( classesTimetable ) );
		}
		return items;
	}
	
	@RequestMapping( value="/classesTimetable",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesTimetable> findAll() {
		return classesTimetableService.findAll();
	}

	@RequestMapping( value="/classesTimetable/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesTimetable findOne(@PathVariable("id") Integer id) {
		return classesTimetableService.findById(id);
	}
	
	@RequestMapping( value="/classesTimetable",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesTimetable create(@RequestBody ClassesTimetable classesTimetable) {
		return classesTimetableService.create(classesTimetable);
	}

	@RequestMapping( value="/classesTimetable/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesTimetable update(@PathVariable("id") Integer id, @RequestBody ClassesTimetable classesTimetable) {
		return classesTimetableService.update(classesTimetable);
	}

	@RequestMapping( value="/classesTimetable/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		classesTimetableService.delete(id);
	}
	
}
