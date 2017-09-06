package com.go2.classes.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.go2.classes.business.service.CenterService;
import com.go2.classes.business.service.PromoService;
import com.go2.classes.business.service.SliderService;
import com.go2.classes.business.service.SocialLinksService;
import com.go2.classes.models.SocialLinks;

@Controller
@RequestMapping("/")
public class HomeController {

	@Resource
	private CenterService centerService; // Injected by Spring

	@Resource
	private PromoService promoService; // Injected by Spring

	@Resource
	private SliderService sliderService; // Injected by Spring

	@Resource
	private SocialLinksService socialLinksService; // Injected by Spring

	@RequestMapping(value = "/map")
	public String openMap(Model model, @RequestParam(name = "centerId") Long centerId) {
		model.addAttribute("address", centerService.findById(centerId).getAddress());
		return "map";
	}

	@RequestMapping()
	public String Home(Model model, HttpSession session) {
		model.addAttribute("promos", promoService.findById(1l));
		model.addAttribute("promos", sliderService.findById(1l));
		session.setAttribute("links", socialLinksService.findById(1l));
		return "index";
	}

}
