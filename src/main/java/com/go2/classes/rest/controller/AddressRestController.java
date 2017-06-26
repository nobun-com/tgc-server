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
import com.go2.classes.models.Address;
import com.go2.classes.business.service.AddressService;
import com.go2.classes.web.listitem.AddressListItem;

@Controller
public class AddressRestController {

	@Resource
	private AddressService addressService;
	
	@RequestMapping( value="/items/address",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<AddressListItem> findAllAsListItems() {
		List<Address> list = addressService.findAll();
		List<AddressListItem> items = new LinkedList<AddressListItem>();
		for ( Address address : list ) {
			items.add(new AddressListItem( address ) );
		}
		return items;
	}
	
	@RequestMapping( value="/address",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Address> findAll() {
		return addressService.findAll();
	}

	@RequestMapping( value="/address/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Address findOne(@PathVariable("id") Long id) {
		return addressService.findById(id);
	}
	
	@RequestMapping( value="/address",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Address create(@RequestBody Address address) {
		return addressService.create(address);
	}

	@RequestMapping( value="/address/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Address update(@PathVariable("id") Long id, @RequestBody Address address) {
		return addressService.update(address);
	}

	@RequestMapping( value="/address/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		addressService.delete(id);
	}
	
}
