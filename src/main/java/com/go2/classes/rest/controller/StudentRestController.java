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
import com.go2.classes.models.Student;
import com.go2.classes.business.service.StudentService;

@Controller
public class StudentRestController {

	@Resource
	private StudentService studentService;
	
	@RequestMapping( value="/student",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Student> findAll() {
		return studentService.findAll();
	}

	@RequestMapping( value="/student/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Student findOne(@PathVariable("id") Long id) {
		return studentService.findById(id);
	}
	
	@RequestMapping( value="/student",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Student create(@RequestBody Student student) {
		return studentService.create(student);
	}

	@RequestMapping( value="/student/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Student update(@PathVariable("id") Long id, @RequestBody Student student) {
		return studentService.update(student);
	}

	@RequestMapping( value="/student/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		studentService.delete(id);
	}
	
}
