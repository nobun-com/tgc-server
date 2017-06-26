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
import com.go2.classes.models.CenterAssetEntity;
import com.go2.classes.business.service.CenterAssetEntityService;
import com.go2.classes.web.listitem.CenterAssetEntityListItem;

@Controller
public class CenterAssetEntityRestController {

	@Resource
	private CenterAssetEntityService centerAssetEntityService;
	
	@RequestMapping( value="/items/centerAssetEntity",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CenterAssetEntityListItem> findAllAsListItems() {
		List<CenterAssetEntity> list = centerAssetEntityService.findAll();
		List<CenterAssetEntityListItem> items = new LinkedList<CenterAssetEntityListItem>();
		for ( CenterAssetEntity centerAssetEntity : list ) {
			items.add(new CenterAssetEntityListItem( centerAssetEntity ) );
		}
		return items;
	}
	
	@RequestMapping( value="/centerAssetEntity",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CenterAssetEntity> findAll() {
		return centerAssetEntityService.findAll();
	}

	@RequestMapping( value="/centerAssetEntity/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CenterAssetEntity findOne(@PathVariable("id") Long id) {
		return centerAssetEntityService.findById(id);
	}
	
	@RequestMapping( value="/centerAssetEntity",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CenterAssetEntity create(@RequestBody CenterAssetEntity centerAssetEntity) {
		return centerAssetEntityService.create(centerAssetEntity);
	}

	@RequestMapping( value="/centerAssetEntity/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CenterAssetEntity update(@PathVariable("id") Long id, @RequestBody CenterAssetEntity centerAssetEntity) {
		return centerAssetEntityService.update(centerAssetEntity);
	}

	@RequestMapping( value="/centerAssetEntity/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		centerAssetEntityService.delete(id);
	}
	
}
