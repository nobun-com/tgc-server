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
import com.go2.classes.models.ClassesAssetEntity;
import com.go2.classes.business.service.ClassesAssetEntityService;
import com.go2.classes.web.listitem.ClassesAssetEntityListItem;

@Controller
public class ClassesAssetEntityRestController {

	@Resource
	private ClassesAssetEntityService classesAssetEntityService;
	
	@RequestMapping( value="/items/classesAssetEntity",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesAssetEntityListItem> findAllAsListItems() {
		List<ClassesAssetEntity> list = classesAssetEntityService.findAll();
		List<ClassesAssetEntityListItem> items = new LinkedList<ClassesAssetEntityListItem>();
		for ( ClassesAssetEntity classesAssetEntity : list ) {
			items.add(new ClassesAssetEntityListItem( classesAssetEntity ) );
		}
		return items;
	}
	
	@RequestMapping( value="/classesAssetEntity",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ClassesAssetEntity> findAll() {
		return classesAssetEntityService.findAll();
	}

	@RequestMapping( value="/classesAssetEntity/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesAssetEntity findOne(@PathVariable("id") Long id) {
		return classesAssetEntityService.findById(id);
	}
	
	@RequestMapping( value="/classesAssetEntity",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesAssetEntity create(@RequestBody ClassesAssetEntity classesAssetEntity) {
		return classesAssetEntityService.create(classesAssetEntity);
	}

	@RequestMapping( value="/classesAssetEntity/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ClassesAssetEntity update(@PathVariable("id") Long id, @RequestBody ClassesAssetEntity classesAssetEntity) {
		return classesAssetEntityService.update(classesAssetEntity);
	}

	@RequestMapping( value="/classesAssetEntity/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		classesAssetEntityService.delete(id);
	}
	
}
