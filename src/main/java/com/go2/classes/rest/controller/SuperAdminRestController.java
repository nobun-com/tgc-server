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
import com.go2.classes.models.SuperAdmin;
import com.go2.classes.business.service.SuperAdminService;

@Controller
public class SuperAdminRestController {

	@Resource
	private SuperAdminService superAdminService;
	
	@RequestMapping( value="/superAdmin",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SuperAdmin> findAll() {
		return superAdminService.findAll();
	}

	@RequestMapping( value="/superAdmin/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public SuperAdmin findOne(@PathVariable("id") Long id) {
		return superAdminService.findById(id);
	}
	
	@RequestMapping( value="/superAdmin",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public SuperAdmin create(@RequestBody SuperAdmin superAdmin) {
		return superAdminService.create(superAdmin);
	}

	@RequestMapping( value="/superAdmin/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public SuperAdmin update(@PathVariable("id") Long id, @RequestBody SuperAdmin superAdmin) {
		return superAdminService.update(superAdmin);
	}

	@RequestMapping( value="/superAdmin/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		superAdminService.delete(id);
	}
	
}
