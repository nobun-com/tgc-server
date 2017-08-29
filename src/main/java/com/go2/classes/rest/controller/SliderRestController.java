/*
 * Created on 28 Aug 2017 ( Time 15:03:18 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
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

import com.go2.classes.business.service.SliderService;
import com.go2.classes.models.Slider;

/**
 * Spring MVC controller for 'Slider' management.
 */
@Controller
public class SliderRestController {

	@Resource
	private SliderService sliderService;

	@RequestMapping(value = "/slider", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Slider> findAll() {
		return sliderService.findAll();
	}

	@RequestMapping(value = "/slider/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Slider findOne(@PathVariable("id") Long id) {
		return sliderService.findById(id);
	}

	@RequestMapping(value = "/slider", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Slider create(@RequestBody Slider slider) {
		return sliderService.create(slider);
	}

	@RequestMapping(value = "/slider", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Slider update(@RequestBody Slider slider) {
		return sliderService.update(slider);
	}

	@RequestMapping(value = "/slider/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		sliderService.delete(id);
	}

}
