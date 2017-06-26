package com.go2.classes.rest.controller;

import java.util.LinkedList;
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
import com.go2.classes.models.TimeTable;
import com.go2.classes.business.service.TimeTableService;
import com.go2.classes.web.listitem.TimeTableListItem;

@Controller
public class TimeTableRestController {

	@Resource
	private TimeTableService timeTableService;
	
	@RequestMapping( value="/items/timeTable",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TimeTableListItem> findAllAsListItems() {
		List<TimeTable> list = timeTableService.findAll();
		List<TimeTableListItem> items = new LinkedList<TimeTableListItem>();
		for ( TimeTable timeTable : list ) {
			items.add(new TimeTableListItem( timeTable ) );
		}
		return items;
	}
	
	@RequestMapping( value="/timeTable",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TimeTable> findAll() {
		return timeTableService.findAll();
	}

	@RequestMapping( value="/getAllClassInstances/{classId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Map<String, Object>> findAllClassInstancesByClass(@PathVariable("classId")Long classId) {
		return timeTableService.findAllClassInstancesByClass(classId);
	}

	@RequestMapping( value="/timeTable/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TimeTable findOne(@PathVariable("id") Long id) {
		return timeTableService.findById(id);
	}
	
	@RequestMapping( value="/timeTable",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TimeTable create(@RequestBody TimeTable timeTable) {
		return timeTableService.create(timeTable);
	}

	@RequestMapping( value="/timeTable/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TimeTable update(@PathVariable("id") Long id, @RequestBody TimeTable timeTable) {
		return timeTableService.update(timeTable);
	}

	@RequestMapping( value="/timeTable/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		timeTableService.delete(id);
	}
	
}
