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
import com.go2.classes.models.Teacher;
import com.go2.classes.business.service.TeacherService;

@Controller
public class TeacherRestController {

	@Resource
	private TeacherService teacherService;
	
	@RequestMapping( value="/getAllTeachers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Teacher> findAll() {
		return teacherService.findAll();
	}

	@RequestMapping( value="/getTeacher/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teacher findOne(@PathVariable("id") Long id) {
		return teacherService.findById(id);
	}
	
	@RequestMapping( value="/createTeacher",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teacher create(@RequestBody Teacher teacher) {
		return teacherService.create(teacher);
	}

	@RequestMapping( value="/updateTeacher",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teacher update(@RequestBody Teacher teacher) {
		return teacherService.update(teacher);
	}

	@RequestMapping( value="/deleteTeacher/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		teacherService.delete(id);
	}
	
}
