package com.go2.classes.rest.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.go2.classes.models.Coupon;

import com.go2.classes.business.service.CouponService;

@Controller
@EnableScheduling
public class CouponRestController {

	@Resource
	private CouponService couponService;
		
	@RequestMapping( value="/getAllCoupons",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Coupon> findAll() {
		return couponService.findAll();
	}

	@RequestMapping( value="/getCoupon/{code}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Coupon findOne(@PathVariable("code") String code) {
		return couponService.findById(code);
	}

	@RequestMapping( value="/createCoupon",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Coupon create(@RequestBody Coupon coupon) {
		return couponService.create(coupon);
	}

	@RequestMapping( value="/updateCoupon",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Coupon update(@RequestBody Coupon coupon) {
		return couponService.update(coupon);
	}

	@RequestMapping( value="/deleteCoupon/{code}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("code") String code) {
		couponService.delete(code);
	}

	@Scheduled(cron = "0 0 0 1/1 * ?")
	public void couponScheduler() {
		System.out.println("Scheduler started on : " + new Date());
		couponService.processCoupons();
	}

}
