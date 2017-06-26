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
import com.go2.classes.models.Admin;
import com.go2.classes.business.service.AdminService;
import com.go2.classes.web.listitem.AdminListItem;

@Controller
public class AdminRestController {

	@Resource
	private AdminService adminService;
	
	@RequestMapping( value="/items/admin",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<AdminListItem> findAllAsListItems() {
		List<Admin> list = adminService.findAll();
		List<AdminListItem> items = new LinkedList<AdminListItem>();
		for ( Admin admin : list ) {
			items.add(new AdminListItem( admin ) );
		}
		return items;
	}
	
	@RequestMapping( value="/admin",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Admin> findAll() {
		return adminService.findAll();
	}

	@RequestMapping( value="/admin/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Admin findOne(@PathVariable("id") Long id) {
		return adminService.findById(id);
	}
	
	@RequestMapping( value="/createAdmin",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Admin create(@RequestBody Admin admin) {
		return adminService.create(admin);
	}

	@RequestMapping( value="/admin/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Admin update(@PathVariable("id") Long id, @RequestBody Admin admin) {
		return adminService.update(admin);
	}

	@RequestMapping( value="/admin/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		adminService.delete(id);
	}
	
}
