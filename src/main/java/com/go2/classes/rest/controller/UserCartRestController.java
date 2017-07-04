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
import com.go2.classes.models.UserCart;
import com.go2.classes.business.service.UserCartService;

@Controller
public class UserCartRestController {

	@Resource
	private UserCartService userCartService;
		
	@RequestMapping( value="/getAllUserCarts",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<UserCart> findAll() {
		return userCartService.findAll();
	}

	@RequestMapping( value="/getUserCart/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserCart findOne(@PathVariable("id") Long id) {
		return userCartService.findById(id);
	}
	
	@RequestMapping( value="/createUserCart",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserCart create(@RequestBody UserCart userCart) {
		return userCartService.create(userCart);
	}

	@RequestMapping( value="/updateUserCart",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserCart update(@RequestBody UserCart userCart) {
		return userCartService.update(userCart);
	}

	@RequestMapping( value="/userCart/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		userCartService.delete(id);
	}
	
}
