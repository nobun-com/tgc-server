package com.go2.classes.rest.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.go2.classes.business.service.AreaService;

@Controller
public class AreaRestController {

	@Resource
	private AreaService areaService;
	
	@RequestMapping( value="/items/area", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, List<String>> findAllAsListItems() {
		return areaService.getDistrictByArea();
	}
}
