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
import com.go2.classes.models.Asset;
import com.go2.classes.business.service.AssetService;
import com.go2.classes.web.listitem.AssetListItem;

/**
 * Spring MVC controller for 'Asset' management.
 */
@Controller
public class AssetRestController {

	@Resource
	private AssetService assetService;
	
	@RequestMapping( value="/items/asset",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<AssetListItem> findAllAsListItems() {
		List<Asset> list = assetService.findAll();
		List<AssetListItem> items = new LinkedList<AssetListItem>();
		for ( Asset asset : list ) {
			items.add(new AssetListItem( asset ) );
		}
		return items;
	}
	
	@RequestMapping( value="/asset",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Asset> findAll() {
		return assetService.findAll();
	}

	@RequestMapping( value="/asset/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Asset findOne(@PathVariable("id") Long id) {
		return assetService.findById(id);
	}
	
	@RequestMapping( value="/asset",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Asset create(@RequestBody Asset asset) {
		return assetService.create(asset);
	}

	@RequestMapping( value="/asset/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Asset update(@PathVariable("id") Long id, @RequestBody Asset asset) {
		return assetService.update(asset);
	}

	@RequestMapping( value="/asset/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		assetService.delete(id);
	}
	
}
