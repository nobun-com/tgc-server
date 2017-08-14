package com.go2.classes.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.go2.classes.business.service.UserCartService;
import com.go2.classes.common.Utilities;
import com.go2.classes.models.UserCart;

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
	
	@RequestMapping( value="/getAllBookings",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Object> getAllBookings() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date result = cal.getTime();
		String fromDate =Utilities.dateWithoutTime.format(result);
		String toDate =Utilities.dateWithoutTime.format(new Date());
		return userCartService.getAllBookingsByMonth(fromDate,toDate);
	}
	
	@RequestMapping( value="/getBookingsByEducator/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Object> getAllBookingsByEducator(@PathVariable("id") Long userId) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date result = cal.getTime();
		String fromDate =Utilities.dateWithoutTime.format(result);
		String toDate =Utilities.dateWithoutTime.format(new Date());
		return userCartService.getAllBookingsByEducator(userId,fromDate,toDate);
	}
	
	@RequestMapping( value="/getAllBookingsByDate",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Object> getAllBookingsByMonth(@RequestBody Map<String, String> data) throws ParseException {
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		
		Date date1 = format2.parse(data.get("fromDate"));
		String strFromDate=format1.format(date1);
		Date date2 = format2.parse(data.get("toDate"));
		String strToDate=format1.format(date2);
		String role=data.get("role");
		if(role.equals("admin")){
			return userCartService.getAllBookingsByMonth(strFromDate,strToDate);
		}else{
			Long userId=Long.valueOf(data.get("userId"));
			return userCartService.getAllBookingsByEducator(userId,strFromDate,strToDate);
		}
		
	}
	
}
