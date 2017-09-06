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
import com.go2.classes.models.User;
import com.go2.classes.business.service.UserService;

@Controller
public class UserRestController {

	@Resource
	private UserService userService;
	
	@RequestMapping( value="/user",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<User> findAll() {
		return userService.findAll();
	}

	@RequestMapping( value="/user/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User findOne(@PathVariable("id") Long id) {
		return userService.findById(id);
	}
	
	@RequestMapping( value="/user",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	@RequestMapping( value="/user/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User update(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.update(user);
	}

	@RequestMapping( value="/user/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}
	
}
