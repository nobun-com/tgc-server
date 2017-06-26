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

import com.go2.classes.models.Center;
import com.go2.classes.business.service.CenterService;
import com.go2.classes.web.listitem.CenterListItem;

@Controller
public class CenterRestController {

	@Resource
	private CenterService centerService;
	
	@RequestMapping( value="/items/center",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CenterListItem> findAllAsListItems() {
		List<Center> list = centerService.findAll();
		List<CenterListItem> items = new LinkedList<CenterListItem>();
		for ( Center center : list ) {
			items.add(new CenterListItem( center ) );
		}
		return items;
	}
	
	@RequestMapping( value="/getAllCenters",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Center> findAll() {
		return centerService.findAll();
	}

	@RequestMapping( value="/getCenter/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Center findOne(@PathVariable("id") Long id) {
		return centerService.findById(id);
	}

	@RequestMapping( value="/createCenter",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Center create(@RequestBody Center center) {
		return centerService.create(center);
	}

	@RequestMapping( value="/updateCenter",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Center update(@RequestBody Center center) {
		return centerService.update(center);
	}

	@RequestMapping( value="/deleteCenter/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		centerService.delete(id);
	}
	
}
