package com.go2.classes.rest.controller;

import java.util.List;
import java.util.Map;

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

import com.go2.classes.business.service.ClassesService;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.models.Classes;

@Controller
public class ClassesRestController {

    @Resource
    private ClassesService classesService;

    @Resource
    private TimeTableService timeTableService;

    @RequestMapping(value = "/getAllClassesCategories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Map<String, Object>> findAllClassesCategory() {
	return classesService.findAllClassesCategory();
    }

    @RequestMapping(value = "/getAllClasses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Classes> findAll() {
	return classesService.findAll();
    }

    @RequestMapping(value = "/getAllClassesByCenter/{centerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Classes> getAllClassesByCenter(@PathVariable("centerId") Long centerId) {
	return classesService.getAllClassesByCenter(centerId);
    }

    @RequestMapping(value = "/getAllClassesByEducator/{educatorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Classes> getAllClassesByEducator(@PathVariable("educatorId") Long educatorId) {
	return classesService.getAllClassesByEducator(educatorId);
    }

    @RequestMapping(value = "/getClass/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Classes findOne(@PathVariable("id") Long id) {
	return classesService.findById(id);
    }

    @RequestMapping(value = "/createClass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Classes create(@RequestBody Classes classes) {
	return classesService.create(classes);
    }

    @RequestMapping(value = "/updateClass", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Classes update(@RequestBody Classes classes) {
	return classesService.update(classes);
    }

    @RequestMapping(value = "/deleteClass/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
	classesService.delete(id);
    }

}
